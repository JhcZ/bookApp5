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

//前台：顾客清空购物车
@WebServlet("/customer/cart/clear")
public class CartClearServlet extends HttpServlet {
    CartService cartService = new CartServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        if(customer == null){
            //未登录用户。清空session中的购物车
            session.setAttribute("cart",new Cart());
        }else{
            //已登录用户，购物车信息保存在数据库中，调用服务层方法清空该顾客的购物车数据
            cartService.clear(customer);
        }

        //重定向到购物车界面
        resp.sendRedirect("info");
    }
}
