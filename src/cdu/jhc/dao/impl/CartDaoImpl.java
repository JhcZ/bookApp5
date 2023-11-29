package cdu.jhc.dao.impl;

import cdu.jhc.dao.BaseDao;
import cdu.jhc.dao.BookDao;
import cdu.jhc.dao.CartDao;
import cdu.jhc.dao.CustomerDao;
import cdu.jhc.model.BookItem;
import cdu.jhc.model.Customer;
import cdu.jhc.service.CartService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartDaoImpl extends BaseDao implements CartDao{
    BookDao bookDao = new BookDaoImpl();
    CustomerDao customerDao = new CustomerDaoImpl();
    @Override
    public List<BookItem> findByCustomer(int customerId) {
        List<BookItem> bookItems = new ArrayList<>();
        String sql = "SELECT * FROM cart_table WHERE customerId=?";

        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,customerId);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                BookItem bookItem = new BookItem();
                bookItem.setCustomer(customerDao.findById(rs.getInt("customerId")));
                bookItem.setBook(bookDao.findById(rs.getInt("bookId")));
                bookItem.setNum(rs.getInt("num"));
                bookItems.add(bookItem);
            }
        }catch(SQLException e){
            System.out.println("DAO查询购物车出错：customerId=" + customerId + "," + e.getMessage());
        }
        return bookItems;
    }

    @Override
    public int getNum(int customerId, int bookId) {
        int num = 0;
        String sql = "SELECT num FROM cart_table WHERE customerId=? and bookId=?";

        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,customerId);
            pstmt.setInt(2,bookId);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                num = rs.getInt("num");
            }
        }catch(SQLException e){
            System.out.println("DAO查询购物车中图书：customerId=" + customerId +
                    ",bookId=" + bookId + "," + e.getMessage());
        }
        return num;
    }

    @Override
    public int insert(BookItem bookItem) {
        int rows = 0;
        String sql = "INSERT INTO cart_table(customerId,bookId,num) VALUES (?,?,?)";

        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,bookItem.getCustomer().getId());
            pstmt.setInt(2,bookItem.getBook().getId());
            pstmt.setInt(3,bookItem.getNum());
            rows = pstmt.executeUpdate();
        }catch(SQLException e){
            System.out.println("DAO向购物车中添加图书：" + bookItem + "," + e.getMessage());
        }
        return rows;
    }

    @Override
    public int update(BookItem bookItem) {
        int rows = 0;
        String sql = "UPDATE cart_table SET num=? WHERE customerId=? AND bookId=?";

        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,bookItem.getNum());
            pstmt.setInt(2,bookItem.getCustomer().getId());
            pstmt.setInt(3,bookItem.getBook().getId());
            rows = pstmt.executeUpdate();
        }catch(SQLException e){
            System.out.println("DAO修改购物车中图书数量：" + bookItem + "," + e.getMessage());
        }
        return rows;
    }

    @Override
    public int delete(int customerId, int bookId) {
        int rows = 0;
        String sql = "DELETE FROM cart_table WHERE customerId=? AND bookId=?";

        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,customerId);
            pstmt.setInt(2,bookId);
            rows = pstmt.executeUpdate();
        }catch(SQLException e){
            System.out.println("DAO删除购物车中图书：customerId=" + customerId +
                    ",bookId=" + bookId + "," + e.getMessage());
        }
        return rows;
    }

    @Override
    public int delete(int customerId) {
        int rows = 0;
        String sql = "DELETE FROM cart_table WHERE customerId=?";

        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,customerId);
            rows = pstmt.executeUpdate();
        }catch(SQLException e){
            System.out.println("DAO清空购物车：customerId=" + customerId + "," + e.getMessage());
        }
        return rows;
    }
}
