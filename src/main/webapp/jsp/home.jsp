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
    <c:if test="${not empty tvs}">
        <c:forEach var="tv" items="${tvs}">
            <div class="item-box">
                <img class="small-img" src="/img/tv/${tv.vendor}-${tv.model}.jpg">
                <p>Vendor ${tv.vendor}</p>
                <p>Model ${tv.model}</p>
                <p>Price ${tv.price} byn.</p>
            </div>
        </c:forEach>
    </c:if>
</body>
</html>
