package cdu.jhc.controller;

import cdu.jhc.service.AdminUserService;
import cdu.jhc.service.impl.AdminUserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

//后台：删除管理员用户
@WebServlet("/admin/adminUser/del")
public class AdminUserDelServlet extends HttpServlet{
    AdminUserService adminUserService = new AdminUserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sid = req.getParameter("id");
        if (sid != null && !sid.equals("")) {
            //调用服务层方法，使用管理员id处理删除用户的业务逻辑
            adminUserService.del(Integer.parseInt(sid));
            // 在会话范围内保存action属性，记录当前管理员的管理行为
            // 监听器将监听到action属性的值发生变化这一事件，从而将管理员的管理行为记入日志文件中
            req.getSession().setAttribute("action", "删除管理员用户id=" + sid);
        }
        //删除成功或失败，都重定向到列管理员用户表界面
        resp.sendRedirect("list");
    }
}
