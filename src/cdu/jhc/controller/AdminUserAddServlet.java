package cdu.jhc.controller;

import cdu.jhc.model.AdminUser;
import cdu.jhc.service.AdminUserService;
import cdu.jhc.service.impl.AdminUserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

//后台：管理员添加用户
@WebServlet(urlPatterns = {"/admin/adminUser/add"})
public class AdminUserAddServlet extends HttpServlet{
    AdminUserService adminUserService = new AdminUserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //从客户端获取新用户信息
        //req.setCharacterEncoding("utf-8");
        AdminUser adminUser = new AdminUser();
        adminUser.setName(req.getParameter("name"));
        adminUser.setPassword(req.getParameter("password"));

        //从服务层调用方法处理添加用户的业务逻辑
        adminUserService.add(adminUser);
        //在会话范围内保存action属性，记录当前管理员的管理行为
        // 监听器将监听到action属性的值发生变化这一事件，从而将管理员的管理行为记入日志文件中
        req.getSession().setAttribute("action","添加管理员用户" + adminUser.getName());

        //管理员添加用户成功，返回管理员用户管理页面
        resp.sendRedirect("list");
    }
}
