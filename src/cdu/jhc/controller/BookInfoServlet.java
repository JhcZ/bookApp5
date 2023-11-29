package cdu.jhc.controller;

import cdu.jhc.model.Book;
import cdu.jhc.model.Consult;
import cdu.jhc.model.ConsultStatus;
import cdu.jhc.service.BookService;
import cdu.jhc.service.ConsultService;
import cdu.jhc.service.impl.BookServiceImpl;
import cdu.jhc.service.impl.ConsultServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

//前台：获取图书详细信息
@WebServlet("/customer/book/info")
public class BookInfoServlet extends HttpServlet {
    BookService bookService = new BookServiceImpl();
    ConsultService consultService = new ConsultServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sid = req.getParameter("id");
        if (sid != null && !sid.equals("")) {
            int bookId = Integer.parseInt(sid);
            //调用服务层方法使用客户端传递的id值处理查询图书的业务逻辑
            Book book = bookService.get(bookId);
            //根据bookId获取咨询列表
            List<Consult> consultList = consultService.getByBookId(bookId, ConsultStatus.DONE);

            //在请求范围内保存查询到的图书对象
            req.setAttribute("book", book);
            req.setAttribute("consultList",consultList);
            //请求转发到图书详情页面
            req.getRequestDispatcher("info.do").forward(req, resp);
        } else {
            //未获取到id参数，则无法获取图书信息，重定向到图书列表界面
            resp.sendRedirect("list");
        }
    }
}
