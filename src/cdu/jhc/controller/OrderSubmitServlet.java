package cdu.jhc.controller;

import cdu.jhc.model.Cart;
import cdu.jhc.model.Customer;
import cdu.jhc.model.Order;
import cdu.jhc.model.OrderStatus;
import cdu.jhc.service.CartService;
import cdu.jhc.service.OrderService;
import cdu.jhc.service.impl.CartServiceImpl;
import cdu.jhc.service.impl.OrderServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

//前台：顾客提交订单
@WebServlet("/customer/order/submit")
public class OrderSubmitServlet extends HttpServlet {
    OrderService orderService = new OrderServiceImpl();
    CartService cartService = new CartServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        if(customer == null){
            //用户未登录无法下单
            resp.sendRedirect(req.getContextPath() + "/customer/login.do");
            return;
        }
        Cart cart = (Cart) session.getAttribute("cart");
        if(cart == null){
            //购物车为空，无法下单
            resp.sendRedirect(req.getContextPath() + "/customer/cart/info");
            return;
        }

        req.setCharacterEncoding("utf-8");
        String receiverName = req.getParameter("receiverName");
        String receiverTel = req.getParameter("receiverTel");
        String receiverAddress = req.getParameter("receiverAddress");
        if(receiverName == null || receiverName.isEmpty() || receiverTel == null ||
        receiverTel.isEmpty() || receiverAddress == null || receiverAddress.isEmpty()){
            //收货人信息不全，无法下单
            resp.sendRedirect("order.do");
            return;
        }

        //构造订单对象
        Order order = new Order();
        order.setCustomer(customer);
        order.setOrderId(new Date().getTime() + "" + customer.getId());
        order.setBookItems(cart.getBookItems());
        order.setMoney(cart.getTotalPrice());
        order.setCreateTime(new Date().getTime());
        order.setStatus(OrderStatus.UNPAID);
        order.setReceiverName(receiverName);
        order.setReceiverTel(receiverTel);
        order.setReceiverAddress(receiverAddress);

        //保存订单
        if(orderService.save(order)){
            //清空购物车
            cartService.clear(customer);
            session.setAttribute("cart",new Cart());
            //转发至订单支付界面
            req.setAttribute("order",order);
            req.getRequestDispatcher("pay.do").forward(req,resp);
        }else{
            //失败，重定向至订单确认界面
            resp.sendRedirect("order.do");
        }
    }
}
