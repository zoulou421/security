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
	 
	 <form method="GET" action="${pageContext.request.contextPath}/admin">
    <!-- Filtering options -->
    <label for="filterColumn">Filter By:</label>
    <select name="filterColumn">
        <option value="firstName">First Name</option>
        <option value="lastName">Last Name</option>
        <option value="email">Email</option>
    </select>
    
    <input type="text" name="filterValue" placeholder="Filter Value" />

    <!-- Sorting options -->
    <label for="sortColumn">Sort By:</label>
    <select name="sortColumn">
        <option value="firstName">First Name</option>
        <option value="lastName">Last Name</option>
        <option value="email">Email</option>
    </select>

    <label for="sortOrder">Order:</label>
    <select name="sortOrder">
        <option value="asc">Ascending</option>
        <option value="desc">Descending</option>
    </select>

    <button type="submit">Apply</button>
</form>
    
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
 <!-- Pagination Controls -->
    <!-->div>
        <a href="<c:url value='/admin?page=1' />">First</a>
        <a href="<c:url value='/admin?page=${currentPage - 1}' />">Previous</a>
        <a href="<c:url value='/admin?page=${currentPage + 1}' />">Next</a>
        <a href="<c:url value='/admin?page=${totalPages}' />">Last</a>
    </div-->
    
	<script type="text/javascript">
		function confirmDeletion() {
			return confirm('Are you sure you want to delete this user?');
		}
	</script>

</body>
</html>
