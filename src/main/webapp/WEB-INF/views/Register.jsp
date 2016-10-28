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
<div class="w-section register-section centered wf-affected wf-selected">
    <div class="w-container">
        <h1 class="hero-heading">Registration</h1>
        <form class="register-form" method="post" action="">
            <p>
                <label>Email:</label>
                <input class="form-control input-field" type="email" name="email" value="${email}"
                       placeholder="Введите свой email">
                <span class="error">${erremail}</span>
            </p>
            <p>
                <label>First name:</label>
                <input type="text" name="fname" value="${fname}" class="form-control input-field" placeholder="Имя">
                <span class="error">${errfname}</span>
            </p>
            <p>
                <label>Second name:</label>
                <input type="text" name="sname" value="${sname}" class="form-control input-field" placeholder="Фамилия">
                <span class="error">${errsname}</span>
            </p>
            <p>
                <label>Password:</label><input type="password" name="password" class="form-control input-field"
                                               placeholder="Больше 6 символов">
                <span class="error">${errpassword}</span>
            </p>
            <p>
                <label>Repeat password:</label><input type="password" name="repassword" class="form-control input-field"
                                                     placeholder="Повторите ваш пароль">
                <span class="error">${errrepassword}</span></p>
            <p>
                <label>Sex:</label>
            </p>
            <p>
                <input type="radio" name="gender" value="M"
                       <c:if test="${gender eq 'M'}">checked</c:if> >
                <label>M </label>
                <input type="radio" name="gender" value="F"
                       <c:if test="${gender eq 'F'}">checked</c:if> >
                <label>F</label>
                <span class="error">${errgender}</span>
            </p>
            <p>
                <label>Country:</label>
            </p>
            <p>
                <select name="country" class="form-control">
                    <option value="null">Choose you country</option>
                    <c:forEach var="k" items="${countries}">
                        <option value=${k}      <c:if test="${k eq country}">selected</c:if>        >${k}</option>
                    </c:forEach>
                </select>
                <span class="error">${errcountry}</span>
            </p>
            <p>
                <input type="submit" value="Submit" class="button" onclick="validate(this.form)">
            </p>
        </form>
    </div>
</div>
<div class="w-section footer center">
    <div class="w-container">
        <div class="footer-text">© 2016, ILMAZ, Inc. All Rights Reserved.</div>
    </div>
</div>
<script>
    function showError(container, errorMessage) {
        container.className = '';
        var msgElem = document.createElement('label');
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
