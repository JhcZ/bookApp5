package cdu.jhc.service.impl;

import cdu.jhc.dao.OrderDao;
import cdu.jhc.dao.impl.OrderDaoImpl;
import cdu.jhc.model.*;
import cdu.jhc.service.BookService;
import cdu.jhc.service.OrderService;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    OrderDao orderDao = new OrderDaoImpl();
    BookService bookService = new BookServiceImpl();

    @Override
    public Order get(String orderId) {
        return orderDao.findByOrderId(orderId);
    }

    @Override
    public List<Order> get(User customer) {
        return orderDao.findByCustomerId(customer.getId());
    }

    @Override
    public List<Order> get(Order condition, int page, int pageSize) {
        return orderDao.query(condition,(page - 1) * pageSize,pageSize);
    }

    @Override
    public int count(Order condition) {
        return orderDao.count(condition);
    }

    @Override
    public boolean save(Order order) {
        //保存订单
        orderDao.insert(order);
        //修改商品库存
        for(BookItem item : order.getBookItems()){
            Book book = item.getBook();
            int stock = book.getStock();
            book.setStock(stock - item.getNum());
            bookService.mod(book);
        }
        return true;
    }

    @Override
    public boolean cancel(String orderId) {
        Order order = get(orderId);
        switch (order.getStatus()){
            case UNPAID:
                //取消已付款订单：修改订单为取消，恢复库存，处理退款
            case PAID:
                //取消未付款订单：修改订单为已取消，回复库存
                order.setStatus(OrderStatus.CANCEL);
                orderDao.update(order);
                for(BookItem item : order.getBookItems()){
                    int num = item.getNum();
                    Book book = item.getBook();
                    book.setStock(book.getStock() + num);
                    bookService.mod(book);
                }
                return true;
            //TODO 处理退款
            case SHIPPED:
                System.out.println("订单已发货，无法取消：orderId=" + orderId);
                return false;
            case FINISHED:
                System.out.println("订单已完成，无法取消：orderId=" + orderId);
                return false;
            default:
                return false;
        }
    }

    @Override
    public boolean modStatus(String orderId, OrderStatus status) {
        Order condition = new Order();
        condition.setOrderId(orderId);
        condition.setStatus(status);
        return orderDao.update(condition) == 1;
    }

    @Override
    public boolean pay(String orderId) {
        Order condition = new Order();
        condition.setOrderId(orderId);
        condition.setStatus(OrderStatus.PAID);
        return orderDao.update(condition) == 1;
    }

    @Override
    public boolean shipped(String orderId, String expressNumber) {
        Order condition = new Order();
        condition.setOrderId(orderId);
        condition.setExpressNumber(expressNumber);
        condition.setStatus(OrderStatus.SHIPPED);
        return orderDao.update(condition) == 1;
    }

    @Override
    public boolean finish(String orderId) {
        Order condition = new Order();
        condition.setOrderId(orderId);
        condition.setStatus(OrderStatus.FINISHED);
        return orderDao.update(condition) == 1;
    }

    @Override
    public boolean del(String orderId) {
        Order order = orderDao.findByOrderId(orderId);
        if(order != null){
            if(order.getStatus().equals(OrderStatus.CANCEL))
                return orderDao.delete(orderId) == 1;
        }
        return false;
    }
}
