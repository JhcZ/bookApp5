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
<h1>修改图书</h1>

<form action="book/mod" method="post" enctype="multipart/form-data">
    id:<input type="text" name="id" readonly value="${book.id }"><br>
    书名:<input type="text" name="title" value="${book.title }"><br>
    作者:<input type="text" name="author" value="${book.author }"><br>
    出版社:<input type="text" name="press" value="${book.press }"><br>
    价格:<input type="text" name="price" value="${book.price }"><br>
    折扣:<input type="text" name="sale" value="${book.sale }"><br>
    库存:<input type="text" name="stock" value="${book.stock }"><br>
    出版日期:<input type="text" name="publishDate" value="${book.publishDate }"><br>
    上架日期:<input type="text" name="marketDate" value="${book.marketDate }"><br>
    封面:<input type="text" name="coverUrl" value="${book.coverUrl }"><input type="file" name="coverUrl"><br>
    简介:<textarea rows="10" name="info">${book.info }</textarea><br>
    <button type="submit">提 交</button>
</form>
<img src="${book.coverUrl }" alt="图书封面" width="300">

</body>
</html>