<%--
  Created by IntelliJ IDEA.
  User: 蒋洪成
  Date: 2023/11/22 0022
  Time: 10:57
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

<h1>咨询列表</h1>
<form action="consult/query" method="post">
    图书id:<input type="text" name="bookId">
    问题 :<input type="text" name="question">
    咨询用户 :<input type="text" name="consultingUser">
    管理员Id:<input type="text" name="adminUserId">
    回复状态 :
    <select name="reStatus">
        <option value="" selected>全部</option>
        <option value="未回复">未回复</option>
        <option value="已回复">已回复</option>
    </select>
    <input type="submit" value="查询">
</form>
<table>
    <tr>
        <th>序号</th>
        <th>咨询id</th>
        <th>图书id</th>
        <th>问题</th>
        <th>咨询用户</th>
        <th>咨询时间</th>
        <th>回复</th>
        <th>操作</th>
    </tr>
    <c:forEach items="${consultList }" var="consult" varStatus="s">
        <tr>
            <td>${s.count } </td>
            <td>${consult.id } </td>
            <td>${consult.bookId } </td>
            <td>${consult.question } </td>
            <td>${consult.consultingUser } </td>
            <td>${myfn:fmtDateTime(consult.askTime) } </td>
            <td>
                <c:if test="${consult.reStatus==ConsultStatus.NOREPLY }">
                    <strong>${consult.reStatus.name }</strong>
                </c:if>
                <c:if test="${consult.reStatus==ConsultStatus.DONE }">
                    ${consult.adminUser.name } 于 ${myfn:fmtDateTime(consult.reTime) } 回复：<br>
                    ${consult.reply }
                </c:if>
            </td>
            <td>
                <a href="consult/info?id=${consult.id }">回复</a>
                <a href="consult/del?id=${consult.id }">删除</a>
            </td>
        </tr>
    </c:forEach>
</table>

<%-- 分页导航--%>
<c:if test="${p>1 }">
    <a href="consult/list?p=${p-1 }">上一页</a>
</c:if>
<c:if test="${p<pCount }">
    <a href="consult/list?p=${p+1 }">下一页</a>
</c:if>
</body>
</html>
