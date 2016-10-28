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
    <link rel="stylesheet" href="<c:url value="/css/normalize.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/main_page.css"/>">
</head>
<body>
<div class="w-nav navigation-bar" data-collapse="medium" data-animation="default" data-duration="400" data-contain="1">
    <div class="w-container">
        <a class="w-nav-brand w--current brand-link" href="/">
            <h1 class="brand-text">Ilcom-messages</h1>
        </a>
        <c:if test="${not empty user}">
            <nav class="w-nav-menu navigation-menu" role="navigation">
                <a class="w-nav-link w--current navigation-link" style="max-width: 940px;" href="/user">${user.getName()}</a>
            </nav>
        </c:if>
    </div>
</div>
<div class="w-section hero-section centered wf-affected wf-selected">
    <div class="w-container">
        <h1 class="hero-heading" >Welcome to the most popular messenger</h1>
        <div class="hero-subheading">communicate so easily and free</div>
        <div >
            <a
                class="button" href="/login">sign in</a>
            <a
                class="hollow-button all-caps" href="/registration">sign up</a>
        </div>
    </div>
</div>
<div class="w-section footer center">
    <div class="w-container">
        <div class="footer-text">© 2016, ILMAZ, Inc. All Rights Reserved.</div>
    </div>
</div>
</body>
</html>
</html>
