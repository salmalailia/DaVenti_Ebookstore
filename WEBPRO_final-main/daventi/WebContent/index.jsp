<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<style>
        body {
        height: 100%;
        }

        .vertical-center {
        min-height: 100%;  /* Fallback for browsers do NOT support vh unit */
        min-height: 100vh; /* These two lines are counted as one :-)       */
        width: 100%;
        display: flex;
        align-items: center;
        }
</style>


<head>
<title>Da Venti</title>
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

	<div class="row text-center vertical-center">
        <div class="col">
            <h1 class="mb-2 display-4">Welcome ${employee.name_employee}!</h1>
            <blockquote class="blockquote">
            <p class="mb-2">“A reader lives a thousand lives before he dies, said Jojen. The man who never reads lives only one.”</p>
            <footer class="blockquote-footer"><cite title="Source Title"> George R.R. Martin, A Dance with Dragons</cite></footer>
            </blockquote>
        </div>
    </div>

</body>
</html>