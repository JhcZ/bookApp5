package cdu.jhc.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

@WebFilter(filterName = "encodingFilter",urlPatterns = "/*")
public class EncodingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        if(req.getMethod().equals("POST")) {
            // req.setCharacterEncoding("utf-8"); 已配置字符编码过滤器进行处理
        } else{
            req = new EncodingReqWrapper(req);
        }
        filterChain.doFilter(req,servletResponse);
    }
}
