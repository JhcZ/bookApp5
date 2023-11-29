package cdu.jhc.filter;

import cdu.jhc.model.AdminUser;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebFilter(filterName = "adminFilter", urlPatterns = "/admin/*", initParams = {@WebInitParam(name = "permitUrls",
        value = "login.do,login.jsp,login,validCode")})
public class AdminFilter implements Filter {
    List<String> permitUrls = new ArrayList<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //System.out.println("init()方法初始化");
        //从过滤器初始参数获取允许放行的urls
        String[] urls = filterConfig.getInitParameter("permitUrls").split(",");
        for (String url : urls) {
            permitUrls.add(url);
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        // 检查请求URI是否允许放行
        boolean flag = false;
        for (String url : permitUrls) {
            if (req.getRequestURI().endsWith(url)) {
                flag = true;
                break;
            }
        }
        if (flag) {
            // 允许放行
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            // 登录后才允许放行
            HttpSession session = req.getSession();
            AdminUser adminUser = (AdminUser) session.getAttribute("admin");
            if (adminUser != null) {
                // 管理员已登录，可以放行
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                // 管理员未登录，重定向到登录页面
                resp.sendRedirect(req.getContextPath() + "/admin/login.do");
            }
        }
    }
}
