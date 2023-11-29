package cdu.jhc.controller;

import cdu.jhc.model.Consult;
import cdu.jhc.model.ConsultStatus;
import cdu.jhc.model.Customer;
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

@WebServlet("/customer/consult/add")
public class ConsultAddServlet extends HttpServlet {
    ConsultService consultService = new ConsultServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取要咨询的图书id
        String bookId = req.getParameter("id");
        if (bookId == null || bookId.isEmpty()) {
            // 重定向到图书首页
            resp.sendRedirect(req.getContextPath() + "/customer/book/list");
            return;
        }
        // 避免填加空咨询
        String question = req.getParameter("question");
        if (question == null || question.isEmpty()) {
            // 重定向到图书详情页面
            resp.sendRedirect(req.getContextPath() + "/customer/book/info?id=" + bookId);
            return;
        }

        // 根据客户端传递的表单内容构造咨询对象
        Consult consult = new Consult();
        consult.setBookId(Integer.parseInt(bookId));
        consult.setQuestion(question);
        consult.setAskTime(new Date().getTime());
        consult.setReStatus(ConsultStatus.NOREPLY);
        HttpSession session = req.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer == null) {
            consult.setConsultingUser("访客");
        } else {
            consult.setConsultingUser(customer.getName());
        }
        // 调用服务层方法添加咨询
        consultService.add(consult);
        // 重定向到图书详情页面
        resp.sendRedirect(req.getContextPath() + "/customer/book/info?id=" + bookId);
    }
}
