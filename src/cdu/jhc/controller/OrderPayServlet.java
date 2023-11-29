package cdu.jhc.controller;

import cdu.jhc.service.OrderService;
import cdu.jhc.service.impl.OrderServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

//前台：顾客查看支付订单
@WebServlet("/customer/order/pay")
public class OrderPayServlet extends HttpServlet {
    OrderService orderService = new OrderServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //TODO 此处应调用支付宝、微信支付来完成实际功能
        String orderId = req.getParameter("orderId");
        //调用服务层方法模拟订单支付
        orderService.pay(orderId);
        //支付成功或失败，都重定向到该订单的详情页面
        resp.sendRedirect("info?orderId=" + orderId);
    }
}
