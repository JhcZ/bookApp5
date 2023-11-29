package cdu.jhc.dao;

import cdu.jhc.model.Order;

import java.util.List;

public interface OrderDao extends SimpleDao<Order>{
    //根据订单编号查找订单
    Order findByOrderId(String orderId);

    //根据顾客id查找订单
    List<Order> findByCustomerId(int customerId);

    //根据订单编号删除订单
    int delete(String orderId);
}
