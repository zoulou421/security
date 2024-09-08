<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User List</title>
<link rel="stylesheet" href="<c:url value='/styles/main.css' />">
</head>
<body>
	 <h1>User List</h1>
    
    <!-- Search Form -->
    <form action="<c:url value='/admin' />" method="get">
        <input type="hidden" name="action" value="search" />
        <input type="text" name="search" placeholder="Search by name or email" />
        <input type="submit" value="Search" />
    </form><br />
    
	<a href="<c:url value='/admin?action=add' />">Add New User</a>
	<table border="1">
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
			<c:forEach var="user" items="${users}">
				<tr>
					<td>${user.id}</td>
					<td>${user.firstName}</td>
					<td>${user.lastName}</td>
					<td>${user.email}</td>
					<td>
					    <a href="<c:url value='/admin?action=delete&id=${user.id}' />"
                           onclick="return confirmDeletion();">Delete</a>
					</td>
					<td><a
						href="<c:url value='/admin?action=edit&id=${user.id}' />">Edit</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<script type="text/javascript">
		function confirmDeletion() {
			return confirm('Are you sure you want to delete this user?');
		}
	</script>

</body>
</html>
