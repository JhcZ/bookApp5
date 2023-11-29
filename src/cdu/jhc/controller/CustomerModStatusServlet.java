package cdu.jhc.controller;

import cdu.jhc.model.UserStatus;
import cdu.jhc.service.CustomerService;
import cdu.jhc.service.impl.CustomerServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

//后台管理普通用户（顾客）：修改顾客帐户状态，解冻或冻结
@WebServlet(urlPatterns = {"/admin/customer/freeze", "/admin/customer/active"})
public class CustomerModStatusServlet extends HttpServlet{

    CustomerService customerService = new CustomerServiceImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sid = req.getParameter("id");
        if (sid != null && !sid.equals("")) {
            int id = Integer.parseInt(sid);
            if (req.getServletPath().contains("freeze")) {
                customerService.modStatus(id, UserStatus.FREEZE);
                // 在会话范围内保存action属性，记录当前管理员的管理行为
                // 监听器将监听到action属性的值发生变化这一事件，从而将管理员的管理行为记入日志文件中
                req.getSession().setAttribute("action", "冻结顾客账号，id=" + sid);
            } else if (req.getServletPath().contains("active")) {
                customerService.modStatus(id, UserStatus.NORMAL);

                // 在会话范围内保存action属性，记录当前管理员的管理行为
                // 监听器将监听到action属性的值发生变化这一事件，从而将管理员的管理行为记入日志文件中
                req.getSession().setAttribute("action", "解冻顾客账号，id=" + sid);
            } else {
                customerService.modStatus(id, UserStatus.UNKNOWN);
            }
        }
        //重定向到顾客列表管理界面
        resp.sendRedirect("list");
    }
}
