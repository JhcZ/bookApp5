<%--
  Created by IntelliJ IDEA.
  User: 蒋洪成
  Date: 2023/11/22 0022
  Time: 10:56
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="myfn" uri="http://jhc.cn/functions" %>
<%@ page import="cdu.jhc.model.ConsultStatus" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="utf-8">
    <base href="http://${header.host}${pageContext.request.contextPath}/admin/">
    <title>咨询管理</title>
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
<h1>咨询回复</h1>

<div>
    <p>咨询id: ${consult.id } </p>
    <p>咨询图书 ：${book.title }(id:${book.id }) </p>
    <p>咨询问题 ：${consult.question } </p>
    <p>咨询用户 ：${consult.consultingUser } </p>
    <p>咨询时间 ：${myfn:fmtDateTime(consult.askTime) } </p>
    <p>回复状态 ：${consult.reStatus.name } </p>
    <c:if test="${consult.reStatus==ConsultStatus.DONE }">
        <p>回复时间 ：${myfn:fmtDateTime(consult.reTime) } </p>
        <p>回复人 ：${consult.adminUser.name } </p>
    </c:if>
</div>
<form action="consult/reply" method="post">
    <input type="text" name="id" value="${consult.id }" hidden="hidden">
    <textarea name="reply" rows="5" cols="50">${consult.reply }</textarea><br>
    <input type="submit" value="回复" />
</form>

</body>
</html>
