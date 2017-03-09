<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Manage Students</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="<c:url value="/resources/style.css" />">
</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">Project name</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
            <li><a href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
            <li class="active"><a href="#">List</a></li>
            <li><a href="add.html">Add</a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>
    <div class="container">
		<h1>List students</h1>
		<table class="table">
			<tr>
				<th>NIF</th>
				<th>Name</th>
				<th>Email</th>
				<th>Username</th>
				<th>Password</th>
			</tr>	
			<c:forEach items="${students}" var="student">
				<tr>
					<td>${student.nif }</td>
					<td>${student.name }</td>
					<td>${student.email }</td>
					<td>${student.userName }</td>
					<td>${student.passwd }</td>
					<td>
						<a href="update/${ student.userName }.html">
							<button type="button" class="btn btn-default btn-sm">
	  							<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
							</button>
						</a>
						<a href="delete/${ student.userName }.html">
							<button type="button" class="btn btn-danger btn-sm">
	  							<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
							</button>
						</a>
					</td>
				</tr>
			</c:forEach>
		</table>
		<a href="add.html">
			<button type="button" class="btn btn-default btn-sm">
	  			<span class="glyphicon glyphicon-plus" aria-hidden="true"></span> New Student
			</button>
		</a>
	</div><!-- /.container -->
</body>
</html>