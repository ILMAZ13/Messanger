<%--
  Created by IntelliJ IDEA.
  User: ilmaz
  Date: 26.10.16
  Time: 23:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Welcome</title>
    <link rel="stylesheet" href="<c:url value="/css/main_page.css"/>">
</head>
<body>
<div class="navigation-bar w-nav" data-animation="default" data-collapse="medium" data-contain="1" data-duration="400">
    <div class="w-container"><a class="brand-link w-nav-brand"><h1 class="brand-text">ILcom-messages</h1></a>
        <div class="hamburger-button w-nav-button">
            <div class="w-icon-nav-menu"></div>
        </div>
    </div>
</div>
<div class="centered hero-section">
    <div class="w-container" data-ix="new-interaction">
        <h1 class="hero-heading" data-ix="fade-in-bottom-page-loads">Welcome to the most popular messenger</h1>
        <div class="hero-subheading" data-ix="fade-in-bottom-page-loads">communicate so easily and free</div>
        <div data-ix="fade-in-bottom-page-loads"><a class="button" href="/login">sign in</a><a class="all-caps hollow-button" href="/registration">sign up</a>
        </div>
    </div>
</div>
<div class="center footer">
    <div class="w-container">
        <div class="footer-text">© 2016, ILMAZ, Inc. All Rights Reserved.</div>
    </div>
</div>
</body>
</html>
