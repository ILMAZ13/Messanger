<%--
  Created by IntelliJ IDEA.
  User: ilmaz
  Date: 26.10.16
  Time: 1:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
