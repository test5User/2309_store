<%@ page import="static by.itclass.constants.AppConst.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Registration Page</title>
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body>
    <jsp:include page="<%=MENU_JSP%>"/>
    <img class="default-image" src="<%=BACKGROUND_IMAGE%>">
    <div class="form-box">
        <h2>Registration</h2>
        <form method="post" action="<%=REG_CONTROLLER%>">
            <input name="<%=NAME_PARAM%>" placeholder="FIO" required>
            <input name="<%=EMAIL_PARAM%>" placeholder="Email" required>
            <input name="<%=LOGIN_PARAM%>" placeholder="Login" required>
            <input type="password" name="<%=PASS_PARAM%>" placeholder="Password" required>
            <input type="submit" value="Register">
        </form>
        <c:if test="${not empty message}">
            <h2 class="error">${message}</h2>
        </c:if>
    </div>
</body>
</html>
