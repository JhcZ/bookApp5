<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="myfn" uri="http://jhc.cn/functions" %>
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

<h1>图书列表</h1>
<form action="book/query" method="post">
    书名:<input type="text" name="title">
    作者:<input type="text" name="author">
    出版社:<input type="text" name="press">
    <button type="submit">查 询</button>
</form>

<table>
    <tr>
        <th>序号</th>
        <th>封面</th>
        <th>id</th>
        <th>书名</th>
        <th>作者</th>
        <th>出版社</th>
        <th>价格</th>
        <th>折扣</th>
        <th>库存</th>
        <th>出版日期</th>
        <th>上架日期</th>
        <th>操作</th>
    </tr>
    <c:forEach items="${books }" var="book" varStatus="s">
        <tr>
            <td>${s.count }</td>
            <td><img width="64px" src="${book.coverUrl }"></td>
            <td>${book.id } </td>
            <td><a href="book/modPre?id=${book.id }">${book.title }
            </a></td>
            <td>${book.author } </td>
            <td>${book.press } </td>
            <td>${myfn:fmtMoney(book.price) } </td>
            <td>${book.sale } </td>
            <td>${book.stock } </td>
            <td>${myfn:fmtDateShort(book.publishDate) } </td>
            <td>${myfn:fmtDateShort(book.marketDate) } </td>
            <td><a href="book/modPre?id=${book.id }">修改</a>
                <a href="book/del?id=${book.id }">删除</a>
            </td>
        </tr>
    </c:forEach>
</table>

<%-- 分页导航--%>
<c:if test="${p>1}">
    <a href="book/list?p=${p-1 }">上一页</a>
</c:if>
<c:if test="${p<pCount}">
    <a href="book/list?p=${p+1 }">下一页</a>
</c:if>

</body>
</html>