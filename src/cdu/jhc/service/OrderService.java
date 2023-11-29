package cdu.jhc.service;

import cdu.jhc.model.Order;
import cdu.jhc.model.OrderStatus;
import cdu.jhc.model.User;
import com.oracle.wls.shaded.org.apache.xpath.operations.Or;

import java.util.List;

public interface OrderService {
    //根绝订单编号获取订单
    Order get(String orderId);

    //根据顾客id查询订单列表
    List<Order> get(User customer);

    //分页查询订单列表
    List<Order> get(Order condition,int page,int pageSize);

    //根据查询条件，获取订单数量
    int count(Order condition);

    //保存订单
    boolean save(Order order);

    //取消订单：仅限未付款订单
    boolean cancel(String orderId);

    //修改订单：仅支持修改订单状态
    boolean modStatus(String orderId, OrderStatus status);

    //订单支付
    boolean pay(String orderId);

    //订单发货
    boolean shipped(String orderId,String expressNumber);

    //订单完成
    boolean finish(String orderId);

    //订单删除，只能由顾客删除已取消的订单
    boolean del(String orderId);

}
