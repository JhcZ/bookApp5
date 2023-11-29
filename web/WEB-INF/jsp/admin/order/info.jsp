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
    <title>购书网站</title>
</head>
<div>

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

    <h1>订单详情</h1>
    <table>
        <tr>
            <th>图书封面</th>
            <th>书名</th>
            <th>原价</th>
            <th>折扣价</th>
            <th>购买数量</th>
        </tr>
        <c:forEach items="${order.bookItems}" var="item" varStatus="s">
            <tr>
            <td>
                <a href="../customer/book/info?id=${item.book.id}">
                    <img src="${item.book.coverUrl}" height="50px" alt="图书封面">
                </a>
            </td>
            <td>
            <a href="../customer/book/info?id=${item.book.id}">${item.book.title }</a>
            </td>
                <td>${myfn:fmtMoney(item.book.price) } </td>
                <td>${myfn:fmtMoney(item.book.salePrice) } </td>
                <td>${item.num } </td>
            </tr>
        </c:forEach>
    </table>

    <div>
        <p>订单编号：${order.orderId } </p>
        <p>订单创建时间：${myfn:fmtDateTime(order.createTime) } </p>
        <p>订单状态：${order.status.name } </p>
        <c:if test="${order.status==OrderStatus.SHIPPED }">
            <p>物流单号：${order.expressNumber } </p>
        </c:if>
        <c:if test="${order.status==OrderStatus.PAID }">
            <form action="order/shipped?orderId=${order.orderId}" method="post">
            物流单号:<input type="text" name="expressNumber">
            <button type="submit">发货</button>
            </form>
        </c:if>
        <p>状态更新时间：${myfn:fmtDateTime(order.updateTime) } </p>
        <p>收货人：${order.receiverName } </p>
        <p>收货人电话：${order.receiverTel } </p>
        <p>收货地址：${order.receiverAddress } </p>
        <p>订单金额：${myfn:fmtMoney(order.money) } </p>
    </div>
</div>
</body>
</html>
