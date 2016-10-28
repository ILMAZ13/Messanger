<%--
  Created by IntelliJ IDEA.
  User: ilmaz
  Date: 28.10.16
  Time: 8:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Messages with ${opponentName}</title>
    <link rel="stylesheet" href="<c:url value="/css/normalize.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/main_page.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/user_page.css"/>">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script type="text/javascript">
        setInterval(function () {
            $.ajax({
                url: "test.html",
                type: "GET"
            }).done(function(data) {
                $( this ).addClass( "done" );
            });
        }, 100)
    </script>
</head>
<body>
<div class="w-nav navigation-bar" data-collapse="medium" data-animation="default" data-duration="400" data-contain="1">
    <div class="w-container">
        <a class="w-nav-brand w--current brand-link" href="/">
            <h1 class="brand-text">Ilcom-messages</h1>
        </a>
        <c:if test="${not empty user}">
            <nav class="w-nav-menu navigation-menu" role="navigation">
                <a class="w-nav-link w--current navigation-link" style="max-width: 940px;"
                   href="/user">${user.getName()}</a>
            </nav>
        </c:if>
    </div>
</div>
<div class="w-section chat-section centered">
    <div class="w-container">
        <h1 class="hero-heading">chat with ${opponent.getName()}</h1>
        <div class="w-form">
            <form method="post">
                <textarea class="w-input wf-selected" id="field-2" name="text" placeholder="Write message"
                          maxlength="5000"></textarea>
                <input class="w-button" type="submit" value="Send" data-wait="Please wait..."></form>
        </div>
    </div>
</div>
</div>
<c:forEach var="msg" items="${messages}">
    <div class="w-container">
        <div class="w-clearfix">
            <a class="w-inline-block message" href="#">
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
</body>
</html>
