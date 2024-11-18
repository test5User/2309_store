<%@ page import="static by.itclass.constants.AppConst.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Change Pass Page</title>
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body>
    <jsp:include page="<%=MENU_JSP%>"/>
    <img class="default-image" src="<%=BACKGROUND_IMAGE%>">
    <div class="form-box">
        <h2>Change Password</h2>
        <form method="post" action="<%=CHANGE_CONTROLLER%>">
            <input type="hidden" name="<%=ID_PARAM%>" value="${user.id}">
            <input type="password" name="<%=PASS_PARAM%>" placeholder="NEW Password" required>
            <input type="submit" value="Change">
        </form>
        <c:if test="${not empty message}">
            <h2 class="error">${message}</h2>
        </c:if>
    </div>
</body>
</html>
