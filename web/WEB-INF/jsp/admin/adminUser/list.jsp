<%--
  Created by IntelliJ IDEA.
  User: 蒋洪成
  Date: 2023/11/1 0001
  Time: 20:26
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="myfn" uri="http://jhc.cn/functions" %>
<%@ page import="cdu.jhc.model.UserStatus" %>
<%@ page import="cdu.jhc.model.AdminUser" %>
<%@ page import="cdu.jhc.model.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="utf-8">
    <base href="http://${header.host}${pageContext.request.contextPath}/admin/">
    <title>管理员用户管理</title>
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

<h2>管理员用户列表</h2>
<form action="adminUser/query" method="post">
    管理员用户名：<input type="text" name="name">
    状态：<select name="statusName">
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
        <th>管理员用户名</th>
        <th>管理员密码</th>
        <th>创建时间</th>
        <th>最后一次访问时间</th>
        <th>操作</th>
        <th>状态</th>
    </tr>
    <c:forEach items="${adminUsers }" var="user" varStatus="s">
    <tr>
        <td>${s.count}</td>
        <td>${user.id }</td>
        <td>${user.name }</td>
        <td>${myfn:fmtDateTime(user.createTime) } </td>
        <td>${myfn:fmtDateTime(user.lastAccessTime) } </td>
        <td>
            <a href="adminUser/reset?id=${user.id }">重置密码</a>
            <a href="adminUser/modPre?id=${user.id }">修改</a>
            <a href="adminUser/del?id=${user.id }">删除</a></td>
        <td>
                ${user.status.name }
            <c:if test="${user.status==UserStatus.NORMAL }">
                <a href="adminUser/freeze?id=${user.id }">冻结</a>
            </c:if>
            <c:if test="${user.status!=UserStatus.NORMAL }">
                <a href="adminUser/active?id=${user.id }">解冻</a>
            </c:if>
        </td>
    </tr>
    </c:forEach>
</table>

<%-- 分页导航--%>
<c:if test="${p>1}">
    <a href="adminUser/list?p=${p-1 }">上一页</a>
</c:if>
<c:if test="${p<pCount}">
    <a href="adminUser/list?p=${p+1 }">下一页</a>
</c:if>

</body>
</html>