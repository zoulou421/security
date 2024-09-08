<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit User</title>
    <style>
        /* Add some basic styling */
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            margin: 0;
            padding: 20px;
        }

        .container {
            width: 400px;
            margin: 0 auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        label {
            display: block;
            margin-bottom: 10px;
            font-weight: bold;
        }

        input[type="text"], input[type="email"], input[type="password"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 10px;
            border-radius: 4px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }

        .error {
            color: red;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Edit User</h2>
        <c:if test="${not empty error}">
            <div class="error">${error}</div>
        </c:if>
        
       
        <form action="${pageContext.request.contextPath}/admin" method="post">
            <input type="hidden" name="action" value="edit" />
            <input type="hidden" name="id" value="${userService.id}" />

            <label for="firstName">First Name:</label>
            <input type="text" id="firstName" name="firstName" value="${userService.firstName}" required />

            <label for="lastName">Last Name:</label>
            <input type="text" id="lastName" name="lastName" value="${userService.lastName}" required />

            <label for="email">Email:</label>
            <input type="email" id="email" name="email" value="${userService.email}" required />

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" value="${userService.password}" required />

            <input type="submit" value="Update User" name="valider"/>
        </form>

    </div>
</body>
</html>
