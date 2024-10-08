<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin-List</title>
</head>
<body>
	<jsp:include page="../welcome.jsp"></jsp:include>
	<div class="container">
    <table class="table">
        <thead>
            <tr>
                <th>ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.email}</td>
                    <!-- Edit link -->
                    <td>
                        <a href="${pageContext.request.contextPath}/admin?action=edit&id=${user.id}">Update</a>
                    </td>
                    <!-- Delete link with confirmation dialog -->
                    <td>
                        <a href="${pageContext.request.contextPath}/admin?action=delete&id=${user.id}" 
                           onclick="return confirm('Are you sure you want to delete this user?');">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
	
	<!--div class="container">
		<table class="table">
			<thead>
				<tr>
					<th>ID</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${users}" var="user">
					<tr>
						<td>${user.id}</td>
						<td>${user.firstName}</td>
						<td>${user.lastName}</td>
						<td>${user.email}</td>
						<td><a href="#">Delete</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div-->
	
	<!-- Debut ajout FORM -->
	<div class="container">
	   
			<form action="admin" method="post">
			<!-- Hidden input to specify the action (add) -->
			 <input type="hidden" name="action" value="add">
			  <div class="mb-3">
			    <label for="exampleInputEmail1" class="form-label">First name</label>
			    <input type="text" name="firstName" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp">
			  </div>
			  <div class="mb-3">
			    <label for="exampleInputEmail1" class="form-label">Last name</label>
			    <input type="text" name="lastName" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp">
			  </div>
			  <div class="mb-3">
			    <label for="exampleInputEmail1" class="form-label">Email address</label>
			    <input type="text" name="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp">
			  </div>
			  <div class="mb-3">
			    <label for="exampleInputPassword1" class="form-label">Password</label>
			    <input type="password" name="password" class="form-control" id="exampleInputPassword1">
			  </div>
			  
			  <button type="submit" class="btn btn-primary">Submit</button>
			</form>
		</div>
	<!-- Fin ajout Form -->
	
	
</body>
</html>