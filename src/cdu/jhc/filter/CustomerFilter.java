package cdu.jhc.filter;

import cdu.jhc.model.Customer;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(filterName = "customerFilter",urlPatterns = "/customer/order/*")
public class CustomerFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        //顾客登录后才能查看订单信息
        HttpSession session = req.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        if(customer != null)
            //顾客已登录，可以进行订单相关操作
            filterChain.doFilter(servletRequest,servletResponse);
        else{
            //顾客未登录，重定向到登录界面
            resp.sendRedirect(req.getContextPath() + "/customer/login.do");
        }
    }
}