<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit User</title>
    <link rel="stylesheet" href="<c:url value='/styles/main.css' />">
</head>
<body>
    <h1>Edit User</h1>
    <form action="<c:url value='/admin' />" method="post">
        <input type="hidden" name="action" value="edit">
        <input type="hidden" name="id" value="${user.id}">
        <label for="firstName">First Name:</label>
        <input type="text" id="firstName" name="firstName" value="${user.firstName}" required>
        <br>
        <label for="lastName">Last Name:</label>
        <input type="text" id="lastName" name="lastName" value="${user.lastName}" required>
        <br>
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" value="${user.email}" required>
        <br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>
        <br>
        <button type="submit">Update User</button>
    </form>
    <br>
    <!--a href="<c:url value='/admin?action=list' />">Back to List</a-->
    <!--  a href="<c:url value='/admin?action=list' />">Back to List</a-->
    <a href="${pageContext.request.contextPath}/admin?action=list">Back to List</a>
    <c:if test="${not empty error}">
        <p style="color:red;">${error}</p>
    </c:if>
</body>
</html>
