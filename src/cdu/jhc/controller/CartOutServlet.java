package cdu.jhc.controller;

import cdu.jhc.model.Cart;
import cdu.jhc.service.CartService;
import cdu.jhc.service.impl.CartServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

//前台：顾客从购物车中删除图书
@WebServlet("/customer/cart/out")
public class CartOutServlet extends HttpServlet {
    CartService cartService = new CartServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sid = req.getParameter("id");
        int bookId = 0;
        if(sid != null && !sid.equals("")){
            //从客户端获取要加入购物车的图书
            bookId = Integer.parseInt(sid);
        }
        HttpSession session = req.getSession();
        //获取当前访问者的购物车
        Cart cart = (Cart) session.getAttribute("cart");
        //调用服务层方法处理从购物车中删除图书的逻辑
        cartService.putOut(cart,bookId);
        //更新当前访问者的购物车数据
        session.setAttribute("cart",cart);
        //删除成功或失败，都重定向到购物车界面
        resp.sendRedirect("info");
    }
}
