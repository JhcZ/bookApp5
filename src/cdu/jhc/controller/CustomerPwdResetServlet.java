package cdu.jhc.controller;

import cdu.jhc.service.CustomerService;
import cdu.jhc.service.impl.CustomerServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
//后台管理普通用户（顾客）：重置顾客登录密码
//前台：顾客自行重置登录密码
@WebServlet(urlPatterns = {"/admin/customer/reset", "/customer/reset"})
public class CustomerPwdResetServlet extends HttpServlet {
    CustomerService customerService = new CustomerServiceImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sid = req.getParameter("id");
        if (sid != null && !sid.equals("")) {
            //使用客户端传递的id值在数据库中重置密码
            customerService.resetPwd(Integer.parseInt(sid));
        }
        if (req.getRequestURI().contains("admin")) {
            // 在会话范围内保存action属性，记录当前管理员的管理行为
            // 监听器将监听到action属性的值发生变化这一事件，从而将管理员的管理行为记入日志文件中
            req.getSession().setAttribute("action", "重置顾客密码，顾客id=" + sid);

            //管理员重置顾客密码后，重定向到顾客列表管理界面
            resp.sendRedirect("list");
        }else {
            //顾客自己重置密码后，重定向到登录界面
            resp.sendRedirect("login.do");
        }
    }
}
