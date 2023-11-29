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
    订单历史
</div>
<%-- 订单历史主体--%>
    <table>
        <tr>
            <th>订单编号</th>
            <th>创建时间</th>
            <th>订单金额</th>
            <th>收货人</th>
            <th>详情</th>
            <th>订单状态</th>
        </tr>
        <c:forEach items="${orders}" var="order" varStatus="s">
            <tr>
                <td>${order.orderId }</td>
                <td>${myfn:fmtDateTime(order.createTime) }</td>
                <td>${myfn:fmtMoney(order.money) }</td>
                <td>${order.receiverName }</td>
                <td>
                    <a href="order/info?orderId=${order.orderId }">订单详情</a>
                </td>
                <td>
                <span class="text-danger">${order.status.name }</span>
                <c:if test="${order.status==OrderStatus.UNPAID}">
                    <a href="order/pay?orderId=${order.orderId}">付款</a>
                    <a href="order/cancel?orderId=${order.orderId}">取消订单</a>
                </c:if>
                <c:if test="${order.status==OrderStatus.PAID }">
                    <a href="order/cancel?orderId=${order.orderId}">取消订单</a>
                </c:if>
                <c:if test="${order.status==OrderStatus.SHIPPED}">
                    物流单号：${order.expressNumber }
                    <a href="order/finish?orderId=${order.orderId}">确认收货</a>
                </c:if>
                    <c:if test="${order.status==OrderStatus.CANCEL}">
                        <a href="order/del?orderId=${order.orderId}">删除订单</a>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
