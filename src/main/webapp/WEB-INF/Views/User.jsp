<%--
  Created by IntelliJ IDEA.
  User: ilmaz
  Date: 28.09.16
  Time: 1:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Sheet</title>
</head>
<body>
<h1>${name}</h1>
<p>
    <span>${city}   </span>
    <span>${weather}</span>
</p>
<p>
    <span>${singer}</span>
    <span>  ${listeners}</span>
</p>
<form method="post" action="">
    <span>Change/set favourite singer</span>
    <input type="text" name="ch_singer">
    <input type="submit" name="button" value="set/change">
</form>
<span>${gender}</span>
<form method="post" action="">
    <input type="submit" name="button" value="logout">
</form>
<form method="post" action="">
    <input type="submit" name="button" value="delete me">
</form>
</body>
</html>
