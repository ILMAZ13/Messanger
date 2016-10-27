<%--
  Created by IntelliJ IDEA.
  User: ilmaz
  Date: 25.10.16
  Time: 23:18
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Users</title>
    <link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>">
</head>
<body>
<span>${test}</span>
<c:forEach var="user" items="${usersList}">
    <p>
    <form method="post" action="">
    <span>${user.getName()}</span>
    <span>${user.isMale() ? "male" : "female"}</span>
    <button type="submit" value="${user.getEmail()}" name="email">Send Message</button>
</form>
    </p>
</c:forEach>
</body>
</html>
