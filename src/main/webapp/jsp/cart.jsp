<%@ page import="static by.itclass.constants.AppConst.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Cart Page</title>
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body>
    <jsp:include page="<%=MENU_JSP%>"/>
    <h2>Hello ${user.name}</h2>
    <c:choose>
        <c:when test="${not empty orderItems}">
            <h2>Your cart:</h2>
            <c:forEach var="item" items="${orderItems}">
                <div class="cart-item">
                    <img class="cart-img" src="/img/${item.itemType eq 1 ? 'tv' : 'laptop'}/${item.itemVendor}-${item.itemModel}.jpg">
                    <h3 class="cart-text">${item.itemVendor} ${item.itemModel} by ${item.itemPrice} byn.</h3>
                    <form method="post" action="<%=CART_CONTROLLER%>">
                        <input type="hidden" name="<%=CART_ACTION_PARAM%>" value="remove">
                        <input type="hidden" name="<%=ITEM_TYPE_PARAM%>" value="${item.itemType}">
                        <input type="hidden" name="<%=ITEM_ID_PARAM%>" value="${item.itemId}">
                        <input type="hidden" name="<%=ITEM_PRICE_PARAM%>" value="${item.itemPrice}">
                        <input type="hidden" name="<%=QUANTITY_PARAM%>" value="${item.quantity}">
                        <input type="submit" value="Remove from Cart">
                    </form>
<%--                    <form method="post" action="<%=CART_CONTROLLER%>">--%>
<%--                        <input type="hidden" name="<%=CART_ACTION_PARAM%>" value="decrease">--%>
<%--                        <input type="hidden" name="<%=ITEM_TYPE_PARAM%>" value="${item.itemType}">--%>
<%--                        <input type="hidden" name="<%=ITEM_ID_PARAM%>" value="${item.itemId}">--%>
<%--                        <input type="hidden" name="<%=ITEM_PRICE_PARAM%>" value="${item.itemPrice}">--%>
<%--                        <input type="hidden" name="<%=QUANTITY_PARAM%>" value="${item.quantity}">--%>
<%--                        <input class="change-number" type="submit" value="-">--%>
<%--                    </form>--%>
<%--                    ${item.quantity}--%>
<%--                    <form method="post" action="<%=CART_CONTROLLER%>">--%>
<%--                        <input type="hidden" name="<%=CART_ACTION_PARAM%>" value="increase">--%>
<%--                        <input type="hidden" name="<%=ITEM_TYPE_PARAM%>" value="${item.itemType}">--%>
<%--                        <input type="hidden" name="<%=ITEM_ID_PARAM%>" value="${item.itemId}">--%>
<%--                        <input type="hidden" name="<%=ITEM_PRICE_PARAM%>" value="${item.itemPrice}">--%>
<%--                        <input type="hidden" name="<%=QUANTITY_PARAM%>" value="${item.quantity}">--%>
<%--                        <input class="change-number" type="submit" value="+">--%>
<%--                    </form>--%>
                    <form method="post" action="<%=CART_CONTROLLER%>">
                        <input type="hidden" name="<%=CART_ACTION_PARAM%>" value="change">
                        <input type="hidden" name="<%=ITEM_TYPE_PARAM%>" value="${item.itemType}">
                        <input type="hidden" name="<%=ITEM_ID_PARAM%>" value="${item.itemId}">
                        <input type="hidden" name="<%=ITEM_PRICE_PARAM%>" value="${item.itemPrice}">
                        <div class="number-input">
                            <button onclick="this.parentNode.querySelector('input[type=number]').stepDown()"></button>
                            <input type="number" name="<%=QUANTITY_PARAM%>" value="${item.quantity}" min="1">
                            <button onclick="this.parentNode.querySelector('input[type=number]').stepUp()" class="plus"></button>
                        </div>
                    </form>
                </div>
            </c:forEach>
            <div class="order-container">
                <form method="post" action="<%=ORDER_CONTROLLER%>">
                    <input name="<%=ADDRESS_PARAM%>" placeholder="Delivery address" required>
                    <input type="submit" value="Submit order">
                </form>
                <c:if test="${not empty message}">
                    <h2 class="error">${message}</h2>
                </c:if>
            </div>
        </c:when>
        <c:otherwise>
            <p>Your cart is empty</p>
        </c:otherwise>
    </c:choose>
</body>
</html>
