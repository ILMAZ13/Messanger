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
    <link rel="stylesheet" href="<c:url value="/css/normalize.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/main_page.css"/>">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="/js/validation.js"></script>
</head>
<body>
<div class="w-nav navigation-bar" data-collapse="medium" data-animation="default" data-duration="400" data-contain="1">
    <div class="w-container">
        <a class="w-nav-brand w--current brand-link" href="/">
            <h1 class="brand-text">Ilcom-messages</h1>
        </a>
    </div>
</div>
<div class="w-section login-section centered wf-affected wf-selected">
    <div class="w-container">
        <h1 class="hero-heading">LogIn</h1>
        <form method="post" action="" class="register-form">
            <p>
                <span class="error">${wrong}</span>
            </p>
            <p>
                <label>Email:</label>
                <input type="email" name="email" class="form-control input-field" placeholder="Введите свой email">
            </p>
            <p>
                <label>Password:</label>
                <input type="password" name="password" class="form-control input-field" placeholder="Введите пароль">
            </p>
            <p>
                <button type="submit" class="button" value="sign in">sign in</button>
                <a class="hollow-button all-caps" href="/registration">sign up</a>
            </p>
        </form>
    </div>
</div>
<div class="w-section footer center">
    <div class="w-container">
        <div class="footer-text">© 2016, ILMAZ, Inc. All Rights Reserved.</div>
    </div>
</div>
</body>
</html>

