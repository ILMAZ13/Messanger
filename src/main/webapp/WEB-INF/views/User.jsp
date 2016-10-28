<%--
  Created by IntelliJ IDEA.
  User: ilmaz
  Date: 28.09.16
  Time: 1:10
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User Sheet</title>
    <link rel="stylesheet" href="<c:url value="/css/normalize.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/main_page.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/user_page.css"/>">
</head>
<body>
<div class="w-nav navigation-bar" data-collapse="medium" data-animation="default" data-duration="400" data-contain="1">
    <div class="w-nav navigation-bar" data-collapse="medium" data-animation="default" data-duration="400"
         data-contain="1">
        <div class="w-container">
            <a class="w-nav-brand w--current brand-link" href="/">
                <h1 class="brand-text">Ilcom-messages</h1>
            </a>
        </div>
    </div>
</div>
<div class="w-section hero-section centered">
    <div class="w-container" data-ix="new-interaction"><h1 class="hero-heading">${name}</h1>
        <div class="hero-subheading">${city}: ${weather}</div>
        <div data-ix="fade-in-bottom-page-loads">
            <form method="post">
                <a class="button" href="/users">show users</a>
                <button type="submit" style="background: transparent" value="logout" class="hollow-button all-caps"
                        name="button">log out
                </button>
                <button type="submit" style="background: transparent" value="delete me" class="hollow-button all-caps"
                        name="button">delete me
                </button>
            </form>
        </div>
    </div>
</div>
<div class="w-section section">
    <div class="w-container">
        <div class="section-title-group">
            <h2 class="section-heading centered">your last messages:</h2>
        </div>
    </div>
    <c:forEach var="msg" items="${messages}">
        <div class="w-container">
            <div class="w-clearfix">
                <a class="w-inline-block message" href="/messages?email=${msg.getUser().getEmail()}">
                    <div class="w-clearfix">
                        <div class="opponent-name">${msg.getUser().getName()}</div>
                    </div>
                    <div class="w-clearfix">
                        <div class="message-text">${msg.getText()}</div>
                        <div class="message-time">${msg.getTime()}</div>
                    </div>
                </a>
            </div>
        </div>
    </c:forEach>
</div>
<div class="w-section footer center">
    <div class="w-container">
        <div class="footer-text">© 2016, ILMAZ, Inc. All Rights Reserved.</div>
    </div>
</div>
</body>
</html>
