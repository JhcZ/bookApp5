<%--
  Created by IntelliJ IDEA.
  User: 蒋洪成
  Date: 2023/11/1 0001
  Time: 20:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="utf-8">
    <base href="http://${header.host}${pageContext.request.contextPath}/customer/">
    <title>用户注册</title>
</head>
<body>

<div class="row">
    <h1>用户注册</h1>
</div>

<form action="reg" method="post">
    用户名：<input type="text" name="name"><br>
    密码：<input type="password" name="password"><br>
    <button type="submit">注 册</button>
    <button type="submit">重 置</button>
</form>

<div>
    <a href="login.do">用户登录</a>
    <a href="../admin/login.do">管理员登录</a>
</div>
</body>
</html>
