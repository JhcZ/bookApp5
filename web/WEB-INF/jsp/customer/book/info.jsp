<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="myfn" uri="http://jhc.cn/functions" %>
<%@ page import="cdu.jhc.model.ConsultStatus" %>
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
    <div>
        <a href="login.do">登录</a>
        <a href="register.do">注册</a>
    </div>
</c:if>

<c:if test="${!empty customer}">
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
</c:if>
<hr>

<%-- 主体内容 --%>
<div>
    <%-- 路径导航--%>
    <div>
        <a href="book/list">首页</a> -
        <a href="book/list">图书列表</a> -
        图书详情
    </div>

    <%-- 图书详情 --%>
    <img src="${book.coverUrl }" alt="图书封面" height="300px">
    <h3>书名：${book.title }</h3>
    <h4>作者：${book.author }</h4>
    <p>出版社：${book.press }</p>
    <p>出版日期：${myfn:fmtDateShort(book.publishDate) }</p>
    <p>上架日期：${myfn:fmtDateLong(book.marketDate) }</p>
    <p>折扣：${book.sale }</p>
    <p>价格：${myfn:fmtMoney(book.price) }
        <c:if test="${book.stock>0}">
            <a href="cart/in?id=${book.id}">加入购物车</a>
        </c:if></p>
    <div>
        ${book.info }
    </div>

    <br>
    <br>

    <%-- 咨询列表及回复 --%>
    <c:forEach items="${consultList }" var="consult">
        <div style="border: 1px solid #ccc">
            <p>${consult.consultingUser}: ${consult.question }</p>
            <c:if test="${consult.reStatus==ConsultStatus.DONE }">
                <p style="text-indent: 2em">回复 ：${consult.reply}</p>
            </c:if>
        </div>
    </c:forEach>

    <%-- 添加咨询 --%>
    <%-- <form action="consult/add?bookId=${book.id}" method="post"> --%>
    <form action="consult/add" method="post">
        <input type="text" name="id" value="${book.id }" hidden="hidden">
        请输入咨询问题 ：<br>
        <textarea name="question" cols="30" rows="3"></textarea>
        <button type="submit">提交</button>
    </form>

</div>
</body>
</html>