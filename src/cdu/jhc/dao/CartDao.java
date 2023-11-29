package cdu.jhc.dao;

import cdu.jhc.model.Book;
import cdu.jhc.model.BookItem;
import java.util.List;

public interface CartDao {
    //获取某顾客的购物车信息
    List<BookItem> findByCustomer(int customerId);

    //查找某顾客的购物车中是否已有某图书，返回其购买数量
    int getNum(int customerId,int bookId);

    //保存某顾客购买的某图书
    int insert(BookItem item);

    //修改某顾客购买某图书的数量
    int update(BookItem item);

    //删除某顾客购物车中的某图书
    int delete(int customerId,int bookId);

    //清空某顾客的购物车
    int delete(int customerId);
}
