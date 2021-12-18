<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Da Venti - Author</title>
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
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<form action="${pageContext.request.contextPath}/author" method="post">


				<caption>
					<h2>
						<c:if test="${author != null}">
            			Edit Author
            		</c:if>
						<c:if test="${author == null}">
            			Add New Author
            		</c:if>
					</h2>
				</caption>

				<c:if test="${author != null}">
					<fieldset class="form-group">
						<label>Author ID</label> <input type="text" readonly
							value="<c:out value='${author.id_author}' />" class="form-control"
							name="id_author">
					</fieldset>
					<input type="hidden" name="type" value="UPDATE" />
				</c:if>
				
				<c:if test="${author == null}">
					<fieldset class="form-group">
						<label>Author ID</label> <input type="text"
							value="<c:out value='${author.id_author}' />" class="form-control"
							name="id_author" required="required">
					</fieldset>
					<input type="hidden" name="type" value="INSERT" />
				</c:if>
				
				<fieldset class="form-group">
					<label>Author Name</label> <input type="text"
						value="<c:out value='${author.name_author}' />" class="form-control"
						name="name_author" required="required">
				</fieldset>
				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
