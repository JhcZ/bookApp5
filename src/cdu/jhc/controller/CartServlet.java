package cdu.jhc.controller;

import cdu.jhc.model.Cart;
import cdu.jhc.model.Customer;
import cdu.jhc.service.CartService;
import cdu.jhc.service.impl.CartServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

//前台：顾客获取购物车
@WebServlet("/customer/cart/info")
public class CartServlet extends HttpServlet {
    CartService cartService = new CartServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        //购物车信息都通过session中的cart来在不同程序中共享
        Cart cart = (Cart) session.getAttribute("cart");
        //如果用户已登录，则通过服务层方法获取数据库中的购物车数据
        if(customer != null){
            cart = cartService.get(customer);
        }
        //若cart为空，则情况有二：1.用户未登录；2.用户已登录，但无购物车数据
        //构造一个新的空购物车
        if(cart == null){
            cart = new Cart();
        }
        //更新session中购物车数据
        session.setAttribute("cart",cart);
        //请求转发到购物车界面
        req.getRequestDispatcher("info.do").forward(req,resp);
    }
}
