<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2021/12/8
  Time: 18:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>首页</h1>
    当前登录者： ${sessionScope.username}
    <a href="${pageContext.request.contextPath}/user/loginout">注销</a>
</body>
</html>
