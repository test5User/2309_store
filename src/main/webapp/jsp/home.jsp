<%@ page import="static by.itclass.constants.AppConst.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Home page</title>
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body>
    <h2>User info</h2>
    <p>Id: ${user.id}</p>
    <p>Name: ${user.name}</p>
    <p>Email: ${user.email}</p>
    <p>Login: ${user.login}</p>
    <p>Password: ${user.password}</p>
</body>
</html>
