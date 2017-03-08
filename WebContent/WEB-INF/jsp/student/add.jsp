<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>New student</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

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
            <li><a href="list.html">List</a></li>
            <li class="active"><a href="#">Add</a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>
    <div class="container">
		<h2>New student</h2>
		<form:form method="post" modelAttribute="student">
			
			<div class="form-group">
				<form:label path="nif">NIF</form:label>
				<form:input class="form-control" path="nif"/>
			</div>
			
			<div class="form-group">
				<form:label path="name">Name</form:label>
				<form:input class="form-control" path="name"/>
			</div>
			
			<div class="form-group">
				<form:label path="email">Email</form:label>
				<form:input class="form-control" path="email"/>
			</div>
			
			<div class="form-group">
				<form:label path="userName">Username</form:label>
				<form:input class="form-control" path="userName"/>
			</div>
			<div class="form-group">
				<form:label path="passwd">Password</form:label>
				<form:input class="form-control" path="passwd"/>
			</div>
			
			<input type="submit" class="btn btn-default" value="Add student"/>
			
		</form:form>
	</div><!-- /.container -->
</body>
</html>