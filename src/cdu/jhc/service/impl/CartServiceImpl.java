package cdu.jhc.service.impl;

import cdu.jhc.dao.CartDao;
import cdu.jhc.dao.impl.CartDaoImpl;
import cdu.jhc.model.Book;
import cdu.jhc.model.BookItem;
import cdu.jhc.model.Cart;
import cdu.jhc.model.Customer;
import cdu.jhc.service.BookService;
import cdu.jhc.service.CartService;
import java.util.List;

public class CartServiceImpl implements CartService{
    CartDao cartDao = new CartDaoImpl();
    BookService bookService = new BookServiceImpl();

    @Override
    public Cart get(Customer customer) {
        Cart cart = new Cart();
        cart.setCustomer(customer);
        cart.setBookItems(cartDao.findByCustomer(customer.getId()));
        return cart;
    }

    @Override
    public int find(List<BookItem> bookItems, int bookId) {
        int index = -1;
        if(bookItems != null){
            for(int i = 0;i < bookItems.size();i++){
                if(bookItems.get(i).getBook().getId() == bookId){
                    index = i;
                    break;
                }
            }
        }
        return index;
    }

    @Override
    public Cart putIn(Cart cart, int bookId) {
        if(cart == null){
            cart = new Cart();
        }
        //检查购物车中是否存在要购买的图书项
        List<BookItem> bookItems = cart.getBookItems();
        int index = find(bookItems,bookId);
        BookItem bookItem;
        if(index == -1){
            //购物车中没有要购买的图书项，则构造并添加新图书项
            bookItem = new BookItem();
            bookItem.setCustomer(cart.getCustomer());
            bookItem.setBook(bookService.get(bookId));
            bookItem.setNum(1);
            bookItems.add(bookItem);
        }else{
            //购物车中已有要购买的图书项，更新图书购买数量
            bookItem = bookItems.get(bookId);
            bookItem.setNum(bookItem.getNum() + 1);
            bookItems.set(index,bookItem);
        }

        //如果购物车属于已登录用户，则在数据库中保存此购物项
        if(cart.getCustomer() != null){
            int numInDb = cartDao.getNum(cart.getCustomer().getId(),bookId);
            if(numInDb == 0){
                //购物车数据库中没有要购买的图书，向数据库中添加记录
                cartDao.insert(bookItem);
            }
            else{
                //购物车数据库中已有要购买的图书，修改数据库中的记录
                //bootItem中的购买数量为最新购买数量
                cartDao.update(bookItem);
            }
        }
        //更新购物车
        cart.setBookItems(bookItems);
        return cart;
    }

    @Override
    public Cart putOut(Cart cart, int bookId) {
        if(cart == null){
            return new Cart();
        }
        //检查购物车中是否存在要购买的图书项
        List<BookItem> bookItemList = cart.getBookItems();
        int pos = find(bookItemList,bookId);
        if(pos != -1){
            //从图书列表中删除图书
            bookItemList.remove(pos);
            //更新购物车
            cart.setBookItems(bookItemList);
            //如果购物车属于已登录用户，则在数据库中删除图书项
            if(cart.getCustomer() != null){
                del(cart.getCustomer(),bookId);
            }
        }
        return cart;
    }

    //将数据库中的信息合并到购物车中
    @Override
    public Cart merge(Cart cart, Customer customer) {
        if(cart == null){
            cart = new Cart();
            cart.setCustomer(customer);
            return cart;
        }

        List<BookItem> bookItems = cart.getBookItems();
        if(bookItems != null){
            for(BookItem bookItem : bookItems){
                bookItem.setCustomer(customer);
                int numInDb = cartDao.getNum(customer.getId(),bookItem.getBook().getId());
                if(numInDb == 0){
                    //购物车中没有要买的图书，向数据库中添加图书记录
                    cartDao.insert(bookItem);
                } else{
                    //购物车数据库中有要购买的图书，修改数据库中的记录
                    // bookItem中的购买数量为购物车中的数量,需要与数据库中的数量合并
                    bookItem.setNum(numInDb + bookItem.getNum());
                    cartDao.update(bookItem);
                }
            }
        }
        //合并更新购物车
        cart.setCustomer(customer);
        cart.setBookItems(bookItems);
        return cart;
    }

    //在数据库中删除已登录用户的购物车信息
    @Override
    public void del(Customer customer, int bookId) {
        cartDao.delete(customer.getId(),bookId);
    }

    //清空数据库中的购物车
    @Override
    public void clear(Customer customer) {
        cartDao.delete(customer.getId());
    }
}
