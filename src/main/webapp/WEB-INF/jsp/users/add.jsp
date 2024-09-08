<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add User</title>
    <link rel="stylesheet" href="<c:url value='/styles/main.css' />">
</head>
<body>
    <h1>Add User</h1>
    <form action="<c:url value='/admin' />" method="post">
        <input type="hidden" name="action" value="add">
        <label for="firstName">First Name:</label>
        <input type="text" id="firstName" name="firstName" required>
        <br>
        <label for="lastName">Last Name:</label>
        <input type="text" id="lastName" name="lastName" required>
        <br>
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required>
        <br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>
        <br>
        <button type="submit">Add User</button>
    </form>
    <br>
    <!--  a href="<c:url value='/admin?action=list' />">Back to List</a-->
    <a href="${pageContext.request.contextPath}/admin?action=list">Back to List</a>
    
    <c:if test="${not empty error}">
        <p style="color:red;">${error}</p>
    </c:if>
</body>
</html>
