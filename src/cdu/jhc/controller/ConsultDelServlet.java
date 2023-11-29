package cdu.jhc.controller;

import cdu.jhc.model.ConsultStatus;
import cdu.jhc.service.ConsultService;
import cdu.jhc.service.impl.ConsultServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin/consult/del")
public class ConsultDelServlet extends HttpServlet {
    ConsultService consultService = new ConsultServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sid = req.getParameter("id");
        if (sid != null && !sid.isEmpty()) {
            // 调用服务层方法删除咨询
            consultService.del(Integer.parseInt(sid));

            // 在会话范围内保存action属性，记录当前管理员的管理行为
            // 监听器将监听到action属性的值发生变化这一事件，从而将管理员的管理行为记入日志文件中
            req.getSession().setAttribute("action", "删除图书咨询,id=" + sid);
        }
        // 重定向到咨询列表界面
        resp.sendRedirect("list");
    }
}
