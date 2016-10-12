<%--
  Created by IntelliJ IDEA.
  User: ilmaz
  Date: 23.09.16
  Time: 13:10
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Registration</title>
    <link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>">
</head>
<body>
<h1>Registration</h1>
<form method="post" action="">
    <p>
        <span>Email:</span>
        <input type="email" name="email" value="${email}" placeholder="Введите свой email">
        <span style="color:red">${erremail}</span>
    </p>
    <p>
        <span>First name:</span><input type="text" name="fname" value="${fname}" placeholder="Имя">
        <span style="color:red">${errfname}</span>
    </p>
    <p>
        <span>Second name:</span>
        <input type="text" name="sname" value="${sname}" placeholder="Фамилия">
        <span style="color:red">${errsname}</span>
    </p>
    <p>
        <span>Password:</span><input type="password" name="password" placeholder="Больше 6 символов">
        <span style="color:red">${errpassword}</span>
    </p>
    <p>
        <span>Repeat password</span><input type="password" name="repassword" placeholder="Повторите ваш пароль">
        <span style="color:red">${errrepassword}</span></p>
    <p>
        <span>Gender:</span>
        <input type="radio" name="gender" value="M"
               <c:if test="${gender eq 'M'}">checked</c:if> >
        <span>M   </span>
        <input type="radio" name="gender" value="F"
               <c:if test="${gender eq 'F'}">checked</c:if> >
        <span>F</span>
        <span style="color:red">${errgender}</span>
    </p>
    <p>
        <span>Country:</span>
        <select name="country">
            <option value="null">Choose you country</option>
            <c:forEach var="k" items="${countries}">
                <option value=${k}      <c:if test="${k eq country}">selected</c:if>        >${k}</option>
            </c:forEach>
        </select>
        <span style="color:red">${errcountry}</span>
    </p>
    <p>
        <input type="submit" value="Submit">
    </p>
</form>
</body>
</html>
