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
    <link rel="stylesheet" href="<c:url value="/css/register.css"/>">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="/js/validation.js"></script>
</head>
<body>
<div class="register-form">
    <h1>Registration</h1>
    <form class="register-form" method="post" action="">
        <p>
            <span>Email:</span>
            <input class="form-control input-field" type="email" name="email" value="${email}"
                   placeholder="Введите свой email">
            <span style="color:red">${erremail}</span>
        </p>
        <p>
            <span>First name:</span>
            <input type="text" name="fname" value="${fname}" class="form-control input-field" placeholder="Имя">
            <span style="color:red">${errfname}</span>
        </p>
        <p>
            <span>Second name:</span>
            <input type="text" name="sname" value="${sname}" class="form-control input-field" placeholder="Фамилия">
            <span style="color:red">${errsname}</span>
        </p>
        <p>
            <span>Password:</span><input type="password" name="password" class="form-control input-field"
                                         placeholder="Больше 6 символов">
            <span style="color:red">${errpassword}</span>
        </p>
        <p>
            <span>Repeat password</span><input type="password" name="repassword" class="form-control input-field"
                                               placeholder="Повторите ваш пароль">
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
        </p>
        <p>
            <select name="country" class="form-control">
                <option value="null">Choose you country</option>
                <c:forEach var="k" items="${countries}">
                    <option value=${k}      <c:if test="${k eq country}">selected</c:if>        >${k}</option>
                </c:forEach>
            </select>
            <span style="color:red">${errcountry}</span>
        </p>
        <p>
            <input type="submit" value="Submit" onclick="validate(this.form)">
        </p>
    </form>
</div>
<script>
    function showError(container, errorMessage) {
        container.className = '';
        var msgElem = document.createElement('span');
        msgElem.className = "error";
        msgElem.innerHTML = errorMessage;
        container.appendChild(msgElem);
    }

    function resetError(container) {
        container.className = '';
        if (container.lastChild.className == "error") {
            container.removeChild(container.lastChild);
        }
    }

    function validate(form) {
        var elems = form.elements;
        var regName = new RegExp("^[A-Za-zА-Яа-я]{2,20}");
        var regPassword = new RegExp("^[A-Za-z0-9]{6,60}");
        var regEmail = new RegExp("^[-a-z0-9!#$%&'*+/=?^_`{|}~]+" +
                "(\\.[-a-z0-9!#$%&'*+/=?^_`{|}~]+)*@([a-z0-9]([-a-z0-9]{0,61}[a-z0-9])?\\.)*" +
                "([a-z]+)$");
        var f = new Boolean(true);

        resetError(elems.email.parentNode);
        if (!regEmail.test(elems.email.value)) {
            showError(elems.email.parentNode, ' Некорректный адрес почты');
            f = false;
        }

        resetError(elems.fname.parentNode);
        if (!regName.test(elems.fname.value)) {
            showError(elems.fname.parentNode, ' Некорректное имя');
            f = false;
        }

        resetError(elems.sname.parentNode);
        if (!regName.test(elems.sname.value)) {
            showError(elems.sname.parentNode, ' Некорректная фамилия');
            f = false;
        }

        resetError(elems.password.parentNode);
        if (!regPassword.test(elems.password.value)) {
            showError(elems.password.parentNode, ' Некорректный пароль.');
            f = false;
        } else if (elems.password.value != elems.repassword.value) {
            showError(elems.password.parentNode, ' Пароли не совпадают.');
            f = false;
        }

        resetError(elems.country.parentNode);
        if (!elems.country.value || elems.country.value === "null") {
            showError(elems.country.parentNode, ' Укажите ваш город');
            f = false;
        }
        if (!elems.gender.value) {
            f = false;
        }
        if (f) {
            form.submit();
        }
    }
</script>
</body>
</html>
