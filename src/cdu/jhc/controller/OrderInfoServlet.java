package cdu.jhc.controller;

import cdu.jhc.model.Order;
import cdu.jhc.model.User;
import cdu.jhc.service.OrderService;
import cdu.jhc.service.impl.OrderServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

//前台：顾客查看订单详情
//后台：管理员查看订单详情
@WebServlet(urlPatterns = {"/customer/order/info","/admin/order/info"})
public class OrderInfoServlet extends HttpServlet {
    OrderService orderService = new OrderServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //检查顾客和管理员登录状态，之后会用过滤器处理
        HttpSession session = req.getSession();
        //检查顾客是否登录，否则跳转登录界面
        if(req.getServletPath().contains("customer")){
            User customer = (User) session.getAttribute("customer");
            if(customer == null){
                //未登录用户无法查询订单详情
                resp.sendRedirect("login.do");
                return;
            }
        }
        //检查管理员是否登录，否则跳转登录界面
        if(req.getServletPath().contains("admin")){
            User admin = (User) session.getAttribute("admin");
            if(admin == null){
                //未登录管理员无法查询订单详情
                resp.sendRedirect("login.do");
                return;
            }
        }

        String orderId = req.getParameter("orderId");
        //调用服务层方法获取订单
        Order order = orderService.get(orderId);
        //将订单保存至请求范围
        req.setAttribute("order",order);
        //请求转发至订单详情页面
        req.getRequestDispatcher("info.do").forward(req,resp);
    }
}
