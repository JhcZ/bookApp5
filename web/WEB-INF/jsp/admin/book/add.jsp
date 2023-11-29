<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="utf-8">
    <base href="http://${header.host}${pageContext.request.contextPath}/admin/">
    <title>图书管理</title>
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
<h1>添加图书</h1>

<form action="book/add" method="post" enctype="multipart/form-data">
    书名:<input type="text" name="title"><br>
    作者:<input type="text" name="author"><br>
    出版社:<input type="text" name="press"><br>
    价格:<input type="text" name="price" value="0.0"><br>
    折扣:<input type="text" name="sale" value="100"><br>
    库存:<input type="text" name="stock" value="0"><br>
    出版日期:<input type="text" name="publishDate" value="2023-01-01"><br>
    封面:<input type="file" name="coverUrl"><br>
    简介:<textarea rows="5" name="info"></textarea><br>
    <div>
        <button type="submit">提 交</button>
        <button type="reset">重 置</button>
    </div>
</form>

</body>
</html>