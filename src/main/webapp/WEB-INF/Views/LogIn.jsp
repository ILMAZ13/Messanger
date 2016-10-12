<%--
  Created by IntelliJ IDEA.
  User: ilmaz
  Date: 07.10.16
  Time: 10:16
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>LogIn</title>
    <link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>">
</head>
<body>
<h1>LogIn</h1>
<form method="post" action="">
    <p>
        <span style="color:red">${wrong}</span>
    </p>
    <p>
        <span>Email:</span>
        <input type="email" name="email" placeholder="Введите свой email">
    </p>
    <p>
        <span>Password:</span>
        <input type="password" name="password" placeholder="Введите пароль">
    </p>
    <p>
        <input type="submit" value="LogIn">
    </p>
</form>
<p>
    <a href="/registration">Register</a>
</p>
</body>
</html>

