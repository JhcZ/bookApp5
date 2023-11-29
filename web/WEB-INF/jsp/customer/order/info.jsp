<%--
  Created by IntelliJ IDEA.
  User: 蒋洪成
  Date: 2023/11/6 0006
  Time: 17:38
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="myfn" uri="http://jhc.cn/functions" %>
<%@ page import="cdu.jhc.model.OrderStatus" %>
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
        <a href="order/list?customerId=${customer.id }">订单历史</a> -
        订单详情
    </div>
    <%-- 订单详情 --%>
    <c:if test="${empty order}">
        <h2>未获取到订单详情</h2>
    </c:if>
    <c:if test="${!empty order}">
        <div>
            <p>订单编号：${order.orderId} </p>
            <p>订单创建时间：${myfn:fmtDateTime(order.createTime)} </p>
            <p>状态更新时间：${myfn:fmtDateTime(order.updateTime)} </p>
            <p>订单状态：<span class="text-danger">${order.status.name}</span></p>
            <p>
                <c:if test="${order.status==OrderStatus.UNPAID}">
                    <a href="order/pay?orderId=${order.orderId}">付款</a>
                    <a href="order/cancel?orderId=${order.orderId}">取消订单</a>
                </c:if>
                <c:if test="${order.status==OrderStatus.PAID}">
                    <a href="order/cancel?orderId=${order.orderId}">取消订单</a>
                </c:if>
                <c:if test="${order.status==OrderStatus.SHIPPED}">
                    物流单号：${order.expressNumber}
                    <a href="order/finish?orderId=${order.orderId}">确认收货</a>
                </c:if>
                <c:if test="${order.status==OrderStatus.CANCEL}">
                    <a href="order/del?orderId=${order.orderId}">删除订单</a>
                </c:if>
            </p>
            <address>
            <strong>收货人：${order.receiverName} </strong><br>
            收货人电话：${order.receiverTel} <br>
            收货地址：${order.receiverAddress}
            </address>
        </div>

        <table>
        <tr>
        <th>图书封面</th>
        <th>书名</th>
        <th>购买数量</th>
        <th>原价</th>
        <th>折扣价</th>
        </tr>
        <c:forEach items="${order.bookItems}" var="item"
                   varStatus="s">
            <tr>
            <td><a href="book/info?id=${item.book.id}">
            <img src="${item.book.coverUrl}" height="50px"
            alt="图书封面"></a>
            </td>
                <td>
                    <a href="book/info?id=${item.book.id}">${item.book.title} </a>
                </td>
                <td>${item.num} </td>
                <td>${myfn:fmtMoney(item.book.price)} </td>
                <td>${myfn:fmtMoney(item.book.salePrice)} </td>
            </tr>
        </c:forEach>
            <tr>
                <th colspan="5" class="text-right">订单金额 ：${myfn:fmtMoney(order.money)}</th>
            </tr>
        </table>
    </c:if>
</div>
</body>
</html>
