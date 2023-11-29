<%--
  Created by IntelliJ IDEA.
  User: 蒋洪成
  Date: 2023/11/1 0001
  Time: 22:45
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="myfn" uri="http://jhc.cn/functions" %>
<%@ page import="cdu.jhc.model.UserStatus" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="utf-8">
    <base href="http://${header.host}${pageContext.request.contextPath}/admin/">
    <title>普通用户管理</title>
</head>
<body>

<%--头部导航区域--%>
<div>
    <h1>购书网站后台管理平台</h1>
    <a href="../customer/book/list">前台首页</a>
    <a href="book/list">图书列表</a>
    <a href="book/add.do">添加图书</a>
    <a href="consult/list">咨询列表</a>
    <a href="customer/list">顾客列表</a>
    <a href="adminUser/list">管理员列表</a>
    <a href="adminUser/add.do">添加管理员</a>
    <a href="order/list">订单列表</a>

    <%-- 管理员已登录 --%>
    <a href="reset?id=${admin.id }">重置密码</a>
    <a href="logout">退出</a>
</div>
<hr>

<h1>顾客列表</h1>
<form action="customer/query" method="post">
    顾客用户名 :<input type="text" name="name">
    状态 :<select name="statusName">
    <option value="" selected>全部</option>
    <option value="冻结">冻结</option>
    <option value="正常">正常</option>
    <option value="异常">异常</option>
    </select>
    <button type="submit">查 询</button>
</form>

<table>
    <tr>
        <th>序号</th>
        <th>id</th>
        <th>用户名</th>
        <th>创建时间</th>
        <th>最后一次访问时间</th>
        <th>密码重置</th>
        <th>帐户状态</th>
    </tr>

    <c:forEach items="${customers}" var="customer" varStatus="s">
        <tr>
            <td>${s.count } </td>
            <td>${customer.id } </td>
            <td>${customer.name } </td>
            <td>${myfn:fmtDateTime(customer.createTime) } </td>
            <td>${myfn:fmtDateTime(customer.lastAccessTime) } </td>
            <td>
                <a href="customer/reset?id=${customer.id }">密码重置</a>
            </td>
            <td>${customer.status.name }
                <c:if test="${customer.status==UserStatus.NORMAL}">
                    <a href="customer/freeze?id=${customer.id }">冻结</a>
                </c:if>
                <c:if test="${customer.status!=UserStatus.NORMAL}">
                    <a href="customer/active?id=${customer.id }">解冻</a>
                </c:if>
            </td>
        </tr>
    </c:forEach>
</table>
<%-- 分页导航--%>
<c:if test="${p>1}">
    <a href="customer/list?p=${p-1 }">上一页</a>
</c:if>
<c:if test="${p<pCount}">
    <a href="customer/list?p=${p+1 }">下一页</a>
</c:if>

</body>
</html>
