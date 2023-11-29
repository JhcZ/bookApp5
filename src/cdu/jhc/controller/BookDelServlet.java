package cdu.jhc.controller;

import cdu.jhc.service.BookService;
import cdu.jhc.service.impl.BookServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin/book/del")
public class BookDelServlet extends HttpServlet {
    BookService bookService = new BookServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sid = req.getParameter("id");
        if(sid != null && !sid.equals("")){
            //调用服务层方法根据客户端传递的id值处理删除图书的业务逻辑
            bookService.del(Integer.parseInt(sid));

            // 在会话范围内保存action属性，记录当前管理员的管理行为
            // 监听器将监听到action属性的值发生变化这一事件，从而将管理员的管理行为记入日志文件中
            req.getSession().setAttribute("action", "删除图书及其咨询内容，id=" + sid);
        }
        //未获取到id参数，则无法获取图书信息，重定向到图书列表界面
        resp.sendRedirect("list");
    }
}
