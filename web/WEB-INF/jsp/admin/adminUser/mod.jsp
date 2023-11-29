<%--
  Created by IntelliJ IDEA.
  User: 蒋洪成
  Date: 2023/11/1 0001
  Time: 20:33
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<h1>修改管理员</h1>

<form action="adminUser/mod" method="post">
    管理员id: <input type="text" name="id" readonly value="${adminUser.id}">
    管理员用户名:<input type="text" name="name" value="${adminUser.name}"><br>
    <input type="text" name="status" value="${adminUser.status }" hidden="hidden">
    <input type="text" name="createTime" value="${adminUser.createTime }" hidden="hidden">
    <input type="text" name="lastAccessTime" value="${adminUser.lastAccessTime }" hidden="hidden">
    <button type="submit">提 交</button>
</form>

</body>
</html>
