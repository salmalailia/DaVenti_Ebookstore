<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Da Venti - Promo</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	<header>
			<nav class="navbar navbar-expand-md navbar-dark"
				style="background-color: #1F5C70">
				<div>
					<a href="<%=request.getContextPath()%>/index.jsp" class="navbar-brand"> Da Venti </a>
				</div>
	
				<ul class="navbar-nav">
					<li><a href="<%=request.getContextPath()%>/category"
						class="nav-link">Categories</a></li>
					<li><a href="<%=request.getContextPath()%>/author"
						class="nav-link">Author</a></li>
					<li><a href="<%=request.getContextPath()%>/publisher"
						class="nav-link">Publisher</a></li>
					<li><a href="<%=request.getContextPath()%>/status"
						class="nav-link">Status</a></li>
					<li><a href="<%=request.getContextPath()%>/method"
						class="nav-link">Payment Method</a></li>
					<li><a href="<%=request.getContextPath()%>/promo"
						class="nav-link">Promo</a></li>
					<li><a href="<%=request.getContextPath()%>/logout"
						class="nav-link">Logout</a></li>		
				</ul>
			</nav>
		</header>
	<br>

	<div class="row">
		<div class="container">
			<h3 class="text-center">List of Promo</h3>
			<hr>
			<div class="container text-left">

				<a href="<%=request.getContextPath()%>/promo/promo-form.jsp" class="btn btn-success">Add
					New Promo</a>
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Discount</th>
						<th>Start</th>
						<th>End</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="promo" items="${listPromo}">
						<tr>
							<td><c:out value="${promo.id_promo}" /></td>
							<td><c:out value="${promo.name_promo}" /></td>
							<td><c:out value="${promo.discount}" /></td>
							<td><c:out value="${promo.start_discount}" /></td>
							<td><c:out value="${promo.end_discount}" /></td>
							
							<td><a class="btn btn-warning" href="?action=EDIT&id_promo=<c:out value='${promo.id_promo}' />">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; 
								<a class="btn btn-danger" href="?action=DELETE&id_promo=<c:out value='${promo.id_promo}' />">Delete</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>

			</table>
		</div>
	</div>
</body>
</html>
