<%@ page import="static by.itclass.constants.AppConst.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Home page</title>
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body>
    <jsp:include page="<%=MENU_JSP%>"/>
    <h2>Hello ${user.name}</h2>
    <c:if test="${not empty message}">
        <h2>${message}</h2>
    </c:if>
    <jsp:include page="/jsp/slider/slider.html"/>
    <script src="/js/script.js"></script>
</body>
</html>
