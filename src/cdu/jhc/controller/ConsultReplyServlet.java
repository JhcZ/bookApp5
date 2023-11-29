package cdu.jhc.controller;

import cdu.jhc.model.AdminUser;
import cdu.jhc.model.Consult;
import cdu.jhc.model.ConsultStatus;
import cdu.jhc.service.ConsultService;
import cdu.jhc.service.impl.ConsultServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Date;

@WebServlet("/admin/consult/reply")
public class ConsultReplyServlet extends HttpServlet {
    ConsultService consultService = new ConsultServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id == null || id.isEmpty()) {
            // 未获取到咨询id，无法回复，重定向到咨询列表
            resp.sendRedirect("list");
            return;
        }
        // 根据客户端传递的表单内容构造咨询对象
        // req.setCharacterEncoding("utf-8"); 已配置字符编码过滤器进行处理 // 处理中文乱码
        Consult Consult = new Consult();
        Consult.setId(Integer.parseInt(id));
        Consult.setReply(req.getParameter("reply"));
        Consult.setReStatus(ConsultStatus.DONE);
        Consult.setReTime(new Date().getTime());

        HttpSession session = req.getSession();
        Consult.setAdminUser((AdminUser) session.getAttribute("admin"));
        // 调用服务层方法为咨询回复
        consultService.reply(Consult);

        // 在会话范围内保存action属性，记录当前管理员的管理行为
        // 监听器将监听到action属性的值发生变化这一事件，从而将管理员的管理行为记入日志文件中
        req.getSession().setAttribute("action", "回复图书咨询，" + Consult);

        // 重定向至咨询管理列表页面
        resp.sendRedirect("list");
    }
}
