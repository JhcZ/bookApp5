<%--
  Created by IntelliJ IDEA.
  User: 蒋洪成
  Date: 2023/11/6 0006
  Time: 17:37
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="myfn" uri="http://jhc.cn/functions" %>
<%@ page import="cdu.jhc.model.OrderStatus" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <base href="http://${header.host}${pageContext.request.contextPath}/admin/">
    <title>订单管理</title>
</head>
<body>

<%--头部导航区域--%>
<div>
    <h1>购书网站后台管理平台</h1>
    <a href="../customer/book/list">前台首页</a>
    <a href="book/list">图书列表</a>
    <a href="book/add.do">添加图书</a>
    <a href="customer/list">顾客列表</a>
    <a href="adminUser/list">管理员列表</a>
    <a href="adminUser/add.do">添加管理员</a>
    <a href="order/list">订单列表</a>

    <%-- 管理员已登录 --%>
    <a href="reset?id=${admin.id }">重置密码</a>
    <a href="logout">退出</a>
</div>
<hr>

<h1>订单列表</h1>
<form action="order/query" method="post">
    订单编号:<input type="text" name="orderId">
    顾客id:<input type="text" name="customerId">
    订单状态:
    <select name="statusName">
        <option value="" selected>全部</option>
        <option value="未付款">未付款</option>
        <option value="已付款">已付款</option>
        <option value="已发货">已发货</option>
        <option value="已完成">已完成</option>
        <option value="已取消">已取消</option>
        <option value="异常">异常</option>
    </select>
    <button type="submit">查 询</button>
</form>

<table>
    <tr>
        <th>序号</th>
        <th>订单编号</th>
        <th>顾客</th>
        <th>创建时间</th>
        <th>订单金额</th>
        <th>收货人</th>
        <th>订单状态</th>
        <th>详情</th>
        <th>物流单号</th>
    </tr>
    <c:forEach items="${orders}" var="order" varStatus="s">
        <tr>
            <td>${s.count}</td>
            <td>${order.orderId }</td>
            <td>${order.customer.name }</td>
            <td>${myfn:fmtDateTime(order.createTime) }</td>
            <td>${myfn:fmtMoney(order.money) }</td>
            <td>${order.receiverName }</td>
            <td>${order.status.name }</td>
            <td>
                <a href="order/info?orderId=${order.orderId }">订单详情</a>
            </td>
            <td>
                <c:if test="${order.status==OrderStatus.SHIPPED }">
                    ${order.expressNumber }
                </c:if>
                <c:if test="${order.status==OrderStatus.PAID }">
                    <form action="order/shipped" method="post">
                        <input type="text" name="orderId" value="${order.orderId }" hidden>
                        物流单号:<input type="text" name="expressNumber">
                        <button type="submit">发货</button>
                    </form>
                </c:if>
            </td>
        </tr>
    </c:forEach>
</table>

<%-- 分页导航--%>
<c:if test="${p>1}">
    <a href="order/list?p=${p-1 }">上一页</a>
</c:if>
<c:if test="${p<pCount}">
    <a href="order/list?p=${p+1 }">下一页</a>
</c:if>
</body>
</html>
