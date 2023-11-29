package cdu.jhc.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("*.do")
public class MyJspServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*String path = req.getRequestURI();
        path = path.replace(req.getContextPath(),"WEB-INF/jsp");
        path = path.replace(".do",".do");
        req.getRequestDispatcher(path).forward(req,resp);*/

        String path = req.getRequestURI();
        path = path.replace(req.getContextPath(), "/WEB-INF/jsp");
        path = path.replace(".do", ".jsp");
        req.getRequestDispatcher(path).forward(req, resp);
    }
}
