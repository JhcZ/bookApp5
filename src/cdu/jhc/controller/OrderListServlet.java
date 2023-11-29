package cdu.jhc.controller;

import cdu.jhc.model.Order;
import cdu.jhc.service.OrderService;
import cdu.jhc.service.impl.OrderServiceImpl;
import com.oracle.wls.shaded.org.apache.xpath.operations.Or;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

//前台：顾客查看自己的历史订单
//后台：管理员模糊查询、管理订单列表
@WebServlet(urlPatterns = {"/admin/order/list","/admin/order/query","/customer/order/list"})
public class OrderListServlet extends HttpServlet {
    OrderService orderService = new OrderServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //从客户端获取模糊查询条件
        req.setCharacterEncoding("utf-8");
        Order condition = new Order();
        condition.setOrderId(req.getParameter("orderId"));
        condition.setCustomerId(req.getParameter("customerId"));
        condition.setStatus(req.getParameter("statusName"));

        //从客户端获取分页信息
        int page = 1;
        String sPage = req.getParameter("p");
        if(sPage != null && !sPage.isEmpty())
            page = Integer.parseInt(sPage);
        int pageSize = 10;
        int ordersCount = orderService.count(condition);
        int pageCount = ordersCount % pageSize == 0 ? ordersCount / pageSize : ordersCount / pageSize + 1;

        //从服务层获取分页查询结果
        List<Order> orderList = orderService.get(condition,page,pageSize);

        //在请求范围内保存图书列表
        req.setAttribute("orders",orderList);
        req.setAttribute("p",page);
        req.setAttribute("pCount",pageCount);
        //页面跳转：请求转发至订单列表页面
        req.getRequestDispatcher("list.do").forward(req,resp);
    }
}
