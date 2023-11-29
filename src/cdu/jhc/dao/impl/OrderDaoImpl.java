package cdu.jhc.dao.impl;

import cdu.jhc.dao.BaseDao;
import cdu.jhc.dao.BookDao;
import cdu.jhc.dao.CustomerDao;
import cdu.jhc.dao.OrderDao;
import cdu.jhc.model.BookItem;
import cdu.jhc.model.Order;
import cdu.jhc.model.OrderStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class OrderDaoImpl extends BaseDao implements OrderDao {
    CustomerDao customerDao = new CustomerDaoImpl();
    BookDao bookDao = new BookDaoImpl();

    @Override
    public Order findByOrderId(String orderId) {
        Order order = null;
        String sql = "SELECT * FROM order_table WHERE orderId=?";
        System.out.println("DAO查询订单：orderId=" + orderId + "," + sql);

        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,orderId);
            rs = pstmt.executeQuery();
            while(rs.next()){
                order = new Order();
                order.setId(rs.getInt("id"));
                order.setOrderId(rs.getString("orderId"));
                order.setCustomer(customerDao.findById(rs.getInt("customerId")));
                order.setBookItems(getBookItems(rs.getString("books")));
                order.setMoney(rs.getBigDecimal("money"));
                order.setStatus(rs.getString("status"));
                order.setCreateTime(rs.getLong("createTime"));
                order.setUpdateTime(rs.getLong("updateTime"));
                order.setReceiverName(rs.getString("receiverName"));
                order.setReceiverTel(rs.getString("receiverTel"));
                order.setReceiverAddress(rs.getString("receiverAddress"));
                order.setExpressNumber(rs.getString("expressNumber"));
            }
        }catch(SQLException e){
            System.out.println("DAO查询订单出错：" + e.getMessage());
        }
        return order;
    }

    @Override
    public Order findById(int id) {
        Order order = null;
        String sql = "SELECT * FROM order_table WHERE id=?";
        System.out.println("DAO查询订单：id=" + id + "," + sql);

        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,id);
            rs = pstmt.executeQuery();
            while(rs.next()){
                order = new Order();
                order.setId(rs.getInt("id"));
                order.setOrderId(rs.getString("orderId"));
                order.setCustomer(customerDao.findById(rs.getInt("customerId")));
                order.setBookItems(getBookItems(rs.getString("books")));
                order.setMoney(rs.getBigDecimal("money"));
                order.setStatus(rs.getString("status"));
                order.setCreateTime(rs.getLong("createTime"));
                order.setUpdateTime(rs.getLong("updateTime"));
                order.setReceiverName(rs.getString("receiverName"));
                order.setReceiverTel(rs.getString("receiverTel"));
                order.setReceiverAddress(rs.getString("receiverAddress"));
                order.setExpressNumber(rs.getString("expressNumber"));
            }
        }catch(SQLException e){
            System.out.println("DAO查询订单出错：" + e.getMessage());
        }
        return order;
    }

    @Override
    public List<Order> findByCustomerId(int customerId) {
        Order condition = new Order();
        condition.setCustomerId(customerId);
        return query(condition);
    }

    @Override
    public List<Order> query(Order condition) {
        String sql = "SELECT * FROM order_table";
        if(condition != null){
            sql += " WHERE 1=1";
            if(condition.getId() != 0)
                sql += " AND id='" + condition.getId() + "'";
            if(condition.getOrderId() != null)
                sql += " AND orderId='" + condition.getOrderId() + "'";
            if(condition.getCustomerId() != null)
                sql += " AND customerId='" + condition.getCustomerId() + "'";
            if(condition.getStatus() != null && condition.getStatus() != OrderStatus.ALL)
                sql += " AND status='" + condition.getStatus().getName() + "'";
        }
        sql += " ORDER BY createTime DESC";
        System.out.println("DAO查询订单：" + sql);

        List<Order> orderList = new ArrayList<>();
        try{
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()){
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setOrderId(rs.getString("orderId"));
                order.setCustomer(customerDao.findById(rs.getInt("customerId")));
                order.setBookItems(getBookItems(rs.getString("books")));
                order.setMoney(rs.getBigDecimal("money"));
                order.setStatus(rs.getString("status"));
                order.setCreateTime(rs.getLong("createTime"));
                order.setUpdateTime(rs.getLong("updateTime"));
                order.setReceiverName(rs.getString("receiverName"));
                order.setReceiverTel(rs.getString("receiverTel"));
                order.setReceiverAddress(rs.getString("receiverAddress"));
                order.setExpressNumber(rs.getString("expressNumber"));
                orderList.add(order);
            }
        }catch(SQLException e){
            System.out.println("DAO查询订单出错：" + sql + "," + e.getMessage());
        }
        return orderList;
    }

    @Override
    public List<Order> query(Order condition, int start, int num) {
        String sql = "SELECT * FROM order_table";
        if(condition != null){
            sql += " WHERE 1=1";
            if(condition.getId() != 0)
                sql += " AND id='" + condition.getId() + "'";
            if(condition.getOrderId() != null)
                sql += " AND orderId='" + condition.getOrderId() + "'";
            if(condition.getCustomerId() != null)
                sql += " AND customerId='" + condition.getCustomerId() + "'";
            if(condition.getStatus() != null && condition.getStatus() != OrderStatus.ALL)
                sql += " AND status='" + condition.getStatus().getName() + "'";
        }
        sql += " ORDER BY createTime DESC LIMIT ?,?";
        System.out.println("DAO查询订单：" + sql);

        List<Order> orderList = new ArrayList<>();
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,start);
            pstmt.setInt(2,num);
            rs = pstmt.executeQuery();
            while(rs.next()){
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setOrderId(rs.getString("orderId"));
                order.setCustomer(customerDao.findById(rs.getInt("customerId")));
                order.setBookItems(getBookItems(rs.getString("books")));
                order.setMoney(rs.getBigDecimal("money"));
                order.setStatus(rs.getString("status"));
                order.setCreateTime(rs.getLong("createTime"));
                order.setUpdateTime(rs.getLong("updateTime"));
                order.setReceiverName(rs.getString("receiverName"));
                order.setReceiverTel(rs.getString("receiverTel"));
                order.setReceiverAddress(rs.getString("receiverAddress"));
                order.setExpressNumber(rs.getString("expressNumber"));
                orderList.add(order);
            }
        }catch (SQLException e){
            System.out.println("DAO查询出错：" + sql + "," + e.getMessage());
        }
        return orderList;
    }

    @Override
    public int insert(Order order) {
        int rows = 0;
        String sql = "INSERT INTO order_table(orderId,customerId,books,money," +
                "status,createTime,receiverName,receiverTel,receiverAddress)" +
                " VALUES (?,?,?,?,?,?,?,?,?) ";
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, order.getOrderId());
            pstmt.setInt(2, order.getCustomer().getId());
            pstmt.setString(3, order.getBooks());
            pstmt.setBigDecimal(4, order.getMoney());
            pstmt.setString(5, order.getStatus().getName());
            pstmt.setLong(6, order.getCreateTime());
            pstmt.setString(7, order.getReceiverName());
            pstmt.setString(8, order.getReceiverTel());
            pstmt.setString(9, order.getReceiverAddress());
            rows = pstmt.executeUpdate();
        }catch(SQLException e){
            System.out.println("DAO添加订单出错：" + sql + "," + e.getMessage());
        }
        return rows;
    }

    @Override
    public int update(Order order) {
        int rows = 0;
        String sql = "UPDATE order_table SET status=?,expressNumber=?,updateTime=? WHERE orderId=?";

        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, order.getStatus().getName());
            pstmt.setString(2, order.getExpressNumber());
            pstmt.setLong(3, new Date().getTime());
            pstmt.setString(4, order.getOrderId());
            rows = pstmt.executeUpdate();
        }catch(SQLException e){
            System.out.println("DAO修改订单出错：" + sql + "," + e.getMessage());
        }
        return rows;
    }

    @Override
    public int delete(String orderId) {
        int rows = 0;
        String sql = "DELETE FROM order_table WHERE orderId=?";

        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,orderId);
            rows = pstmt.executeUpdate();
        }catch(SQLException e){
            System.out.println("DAO删除订单出错：" + sql + "," + e.getMessage());
        }
        return rows;
    }

    @Override
    public int delete(int id) {
        int rows = 0;
        String sql = "DELETE FROM order-table WHERE orderId=?";

        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,id);
            rows = pstmt.executeUpdate();
        }catch(SQLException e){
            System.out.println("DAO删除订单出错：" + sql + "," + e.getMessage());
        }
        return rows;
    }

    @Override
    public int count() {
        return count(null);
    }

    @Override
    public int count(Order condition) {
        int num = 0;
        String sql = "SELECT count(*) FROM order_table";
        if (condition != null) {
            sql += " WHERE 1=1";
            if (condition.getOrderId() != null && !condition.getOrderId().isEmpty())
                sql += " AND orderId='" + condition.getOrderId() + "'";
            if (condition.getCustomerId() != null && !condition.getCustomerId().isEmpty())
                sql += " AND customerId='" + condition.getCustomerId() + "'";
            if (condition.getStatus() != null && condition.getStatus() != OrderStatus.ALL)
                sql += " AND status='" + condition.getStatus().getName() + "'";
        }
        System.out.println("DAO查询订单数量：" + sql);

        try{
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next())
                num = rs.getInt(1);
        }catch(SQLException e){
            System.out.println("DAO查询订单数量出错：" + sql + "," + e.getMessage());
        }
        return num;
    }

    private List<BookItem> getBookItems(String books){
        List<BookItem> bookItemList = new ArrayList<>();
        if(books == null && books.isEmpty()){
            return bookItemList;
        }
        String[] array = books.split(",");
        for(int i = 0;i < array.length;i++){
            String[] s = array[i].split(":");
            BookItem item = new BookItem();
            item.setBook(bookDao.findById(Integer.parseInt(s[0])));
            item.setNum(Integer.parseInt(s[1]));
            bookItemList.add(item);
        }
        return bookItemList;
    }
}
