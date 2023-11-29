package cdu.jhc.controller;

import cdu.jhc.model.Customer;
import cdu.jhc.service.OrderService;
import cdu.jhc.service.impl.OrderServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

//前台：顾客确认订单完成
@WebServlet("/customer/order/finish")
public class OrderFinishServlet extends HttpServlet {
    OrderService orderService = new OrderServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("orderId");
        //调用服务层方法修改订单状态为已完成
        orderService.finish(orderId);

        HttpSession session = req.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        //重定向到订单列表界面
        resp.sendRedirect("list?customerId=" + customer.getId());
    }
}
