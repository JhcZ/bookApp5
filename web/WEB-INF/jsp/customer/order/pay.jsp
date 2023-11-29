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
    <a href="order/list?customerId=${customer.id }">订单历史</a>
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
        已下单 - 支付中
    </div>
    <%-- 支付界面 --%>
    <div>
        <h2>已下单，请支付</h2>
        <p>订单编号：${order.orderId}</p>
        <p>订单创建时间：${myfn:fmtDateTime(order.createTime)}</p>
        <p>订单状态：<span>${order.status.name}</span></p>
        <p>订单金额：${myfn:fmtMoney(order.money)}</p>
        <address>
            <strong>收货人：${order.receiverName} </strong><br>
            收货人电话：${order.receiverTel} <br>
            收货地址：${order.receiverAddress}
        </address>
    </div>
    <div>
        <a href="order/pay?orderId=${order.orderId}">
            <img src="../images/alipay.png" alt="支付宝" height="100px">
        </a>
        <a href="order/pay?orderId=${order.orderId}">
            <img src="../images/wechatpay.png" alt="微信" height="100px">
        </a>
    </div>
    <div>
        <a href="order/cancel?orderId=${order.orderId}">取消订单</a>
    </div>
</div>
</body>
</html>
