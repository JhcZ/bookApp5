<%--
  Created by IntelliJ IDEA.
  User: 蒋洪成
  Date: 2023/11/6 0006
  Time: 17:38
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="myfn" uri="http://jhc.cn/functions"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <base href="http://${header.host}${pageContext.request.contextPath}/customer/">
    <title>购物网站</title>
</head>
<body>
<%--前台 头部导航区域--%>
<c:if test="${empty customer}">
    <div>
    <a href="book/list">首页</a>
    <a href="cart/info">购物车</a><br>
    </div>
    <div>
        <a href="login.jsp">登录</a>
        <a href="register.jsp">注册</a>
    </div>
</c:if>
<c:if test="${!empty customer}">
    <div>
        <a href="book/list">首页</a>
        <a href="cart/info">购物车</a>
        <a href="order/list?customerId=${customer.id }">订单历史</a>
    </div>
    <div>
        你好，${customer.name }
        <a href="reset?id=${customer.id }">重置密码</a>
        <a href="logout">退出</a>
    </div>
</c:if>
<hr>

<%-- 购物车主体内容 --%>
<div>
    <c:if test="${!empty cart.bookItems}">
        <table>
        <tr>
        <th>封面</th>
        <th>书名</th>
        <th>作者</th>
        <th>原价</th>
        <th>折扣价</th>
        <th>购买数量</th>
        <th>删除</th>
        </tr>
        <c:forEach items="${cart.bookItems}" var="item" varStatus="s">
            <tr>
            <td>
            <a href="book/info?id=${item.book.id}">
            <img src="${item.book.coverUrl}" height="50px"
            alt="图书封面"></a>
            </td>
                <td>
                    <a href="book/info?id=${item.book.id}">${item.book.title}< /a>
                </td>
                <td>${item.book.author} </td>
                <td>${myfn:fmtMoney(item.book.price) } </td>
                <td>${myfn:fmtMoney(item.book.salePrice) } </td>
                <td>${item.num} </td>
                <td><a href="cart/out?id=${item.book.id}">删除</a></td>
            </tr>
        </c:forEach>
            <tr>
                <th colspan="7">
                    <span>总价：${myfn:fmtMoney(cart.totalPrice)}</span>
                    <a href="order/order.jsp">确认下单</a></th>
            </tr>
        </table>
    </c:if>
    <div>
        <a href="book/list">继续购物</a>
        <a href="cart/clear">清空购物车</a>
    </div>
</div>
</body>
</html>
