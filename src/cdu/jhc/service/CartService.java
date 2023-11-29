package cdu.jhc.service;

import cdu.jhc.model.Cart;
import cdu.jhc.model.BookItem;
import cdu.jhc.model.Customer;
import java.util.List;

public interface CartService {
    //获取顾客数据库中已有的购物车信息
    Cart get(Customer customer);

    //查找购买图书项列表中是否存在某本图书，并返回该图书的购买数量
    int find(List<BookItem> bookItems,int bookId);

    //向购物车中添加图书
    Cart putIn(Cart cart,int bookId);

    //从购物车中删除图书
    Cart putOut(Cart cart,int bookId);

    //将数据库中的信息合并到购物车中
    Cart merge(Cart cart,Customer customer);

    //在数据库中删除已登录用户的购物车信息
    void del(Customer customer,int bookId);

    //清空数据库中的购物车
    void clear(Customer customer);

}
