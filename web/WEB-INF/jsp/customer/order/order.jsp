<%--
  Created by IntelliJ IDEA.
  User: 蒋洪成
  Date: 2023/11/6 0006
  Time: 17:38
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="myfn" uri="http://jhc.cn/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <base href="http://${header.host}${pageContext.request.contextPath}/customer/">
    <title>购书网站</title>
</head>
<body>

<%--前台 头部导航区域--%>
<%-- 顾客已登录 --%>
<div>
    <a href="book/list">首页</a>
    <a href="cart/info">购物车</a>
    <a href="order/list?customerId=${customer.id}">订单历史</a>
</div>
<div>
    你好，${customer.name }
    <a href="reset?id=${customer.id }">重置密码</a>
    <a href="logout">退出</a>
</div>
<hr>

<%-- 主体内容 --%>
<div>
    <%-- 路径导航--%>
    <div>
        <a href="book/list">首页</a> -
        <a href="cart/info">购物车</a> -
        确认下单
    </div>
<%-- 确认生成订单 --%>
    <table class="table table-striped table-bordered">
    <tr>
        <th>图书封面</th>
        <th>书名</th>
        <th>原价</th>
        <th>折扣价</th>
        <th>购买数量</th>
    </tr>
    <c:forEach items="${cart.bookItems }" var="item" varStatus="s">
        <tr>
            <td>
                <a href="book/info?id=${item.book.id}">
                    <img src="${item.book.coverUrl}" height="50px" alt="图书封面">
                </a>
            </td>
            <td>
                <a href="book/info?id=${item.book.id}">${item.book.title}</a>
            </td>
            <td>${myfn:fmtMoney(item.book.price)} </td>
            <td>${myfn:fmtMoney(item.book.salePrice)} </td>
            <td>${item.num} </td>
        </tr>
        </c:forEach>
        <tr>
            <th colspan="5" class="text-right">
                总价：${myfn:fmtMoney(cart.totalPrice) }
            </th>
        </tr>
    </table>

    <form action="order/submit" method="post">
        收货人：<input type="text" name="receiverName"><br>
        电话：<input type="text" name="receiverTel"><br>
        收货地址：<input type="text" name="receiverAddress"><br>
        <button class="btn btn-primary" type="submit">确认下单</button>
    </form>

    <div>
        <a href="book/list">取消下单 继续购物</a>
    </div>
</div>
</body>
</html>
