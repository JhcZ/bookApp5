<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="myfn" uri="http://jhc.cn/functions" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="utf-8">
    <base href="http://${header.host}${pageContext.request.contextPath}/customer/">
    <title>购书网站</title>
</head>
<body>
<%--前台 头部导航区域--%>
<c:if test="${empty customer}">
    <div>
        <a href="book/list">首页</a>
        <a href="cart/info">购物车</a><br>
    </div>
    <div><a href="login.do">登录</a>
        <a href="register.do">注册</a>
    </div>
</c:if>
<c:if test="${!empty customer}">
    <div>
        <a href="book/list">首页</a>
        <a href="cart/info">购物车</a>
        <a href="order/list?customerId=${customer.id}">订单历史</a>
    </div>
    <div>
        你好，${customer.name }
        <a href="reset?id=${customer.id }">重置密码</a>
        <a href="logout">退出</a>
    </div>
</c:if>
<hr>

<%-- 主体内容 --%>
<div>
    <c:forEach items="${books }" var="book" varStatus="s">
        <div>
            <div>
                <a href="book/info?id=${book.id}">
                    <img width="256px" src="${book.coverUrl}" alt="图书封面">
                </a>
                <div>
                    <h4><a href="book/info?id=${book.id}">${book.title}</a></h4>
                    <p>作者：${book.author} </p>
                    <p>出版社：${book.press} </p>
                    <p>折扣：${book.sale} </p>
                    <p>库存：${book.stock} </p>
                    <p>价格：${myfn:fmtMoney(book.price)}
                        <c:if test="${book.stock>0}">
                            <a href="cart/in?id=${book.id}">加入购物车</a>
                        </c:if></p>
                </div>
            </div>
        </div>
    </c:forEach>
</div>
</body>
</html>