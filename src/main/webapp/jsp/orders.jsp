<%@ page import="static by.itclass.constants.AppConst.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Orders</title>
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body>
    <jsp:include page="<%=MENU_JSP%>"/>
    <h2>Hello ${user.name}</h2>
    <c:choose>
      <c:when test="${not empty orders}">
          <h2>Yours orders:</h2>
          <c:forEach var="order" items="${orders}">
              <div class="order-list-container">
                  <h3>${order.date}, number: ${order.id}, address: ${order.address}</h3>
                  <form method="post" action="<%=PRINT_ORDER_CONTROLLER%>">
                      <input type="hidden" name="orderId" value="${order.id}">
                      <input type="submit" value="Print">
                  </form>
              </div>
          </c:forEach>
      </c:when>
      <c:otherwise>
          <p>You have no orders</p>
      </c:otherwise>
    </c:choose>
</body>
</html>
