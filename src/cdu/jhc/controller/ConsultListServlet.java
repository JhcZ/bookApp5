package cdu.jhc.controller;

import cdu.jhc.model.AdminUser;
import cdu.jhc.model.Consult;
import cdu.jhc.service.ConsultService;
import cdu.jhc.service.impl.ConsultServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/admin/consult/list","/admin/consult/query"})
public class ConsultListServlet extends HttpServlet {
    ConsultService consultService = new ConsultServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // req.setCharacterEncoding("utf-8"); 已配置字符编码过滤器进行处理 // 处理中文乱码
        // 根据客户端传递的表单内容构造咨询对象
        Consult condition = new Consult();
        condition.setQuestion(req.getParameter("qusetion"));
        condition.setConsultingUser(req.getParameter("consultingUser"));
        condition.setReStatus(req.getParameter("reStatus"));

        String bookId = req.getParameter("bookId");
        if(bookId != null && !bookId.isEmpty()){
            condition.setBookId(Integer.parseInt(bookId));
        }
        String adminUserId = req.getParameter("adminUserId");
        if (adminUserId != null && !adminUserId.isEmpty()) {
            AdminUser adminUser = new AdminUser();
            adminUser.setId(Integer.parseInt(adminUserId));
            condition.setAdminUser(adminUser);
        }

        // 获取分页信息
        int page = 1; // 页码默认值
        int pageSize = 10; // 每页显示数量
        // 获取客户端请求的页码
        String sPage = req.getParameter("p");
        if (sPage != null && !"".equals(sPage)) {
            page = Integer.parseInt(sPage);
        }
        // 获取当前查询条件下的咨询数量
        int count = consultService.count(condition);
        // 计算总页数
        int pageCount = count % pageSize == 0 ? count / pageSize : count / pageSize + 1;

        // 调用服务层方法获取咨询的模糊分页查询结果
        List<Consult> consultList = consultService.get(condition,page,pageSize);

        // 在请求范围内保存咨询数据
        req.setAttribute("consultList",consultList);
        req.setAttribute("p",page);
        req.setAttribute("pCount",pageCount);

        // 页面跳转：请求转发至咨询管理列表页面
        req.getRequestDispatcher("list.do").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}

