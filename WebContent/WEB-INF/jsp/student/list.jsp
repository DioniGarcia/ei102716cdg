<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Manage Students</title>
</head>
<body>
	<h1>List students</h1>
	<table>
		<tr>
			<th>nif</th>
			<th>name</th>
			<th>email</th>
			<th>userName</th>
			<th>passwd</th>
		</tr>	
		<c:forEach items="${students}" var="student">
			<tr>
				<td>${student.nif }</td>
				<td>${student.name }</td>
				<td>${student.email }</td>
				<td>${student.userName }</td>
				<td>${student.passwd }</td>
				<td><a href="update/${ student.userName }.html">Edit</a></td>
				<td><a href="delete/${ student.userName }.html">Delete</a></td>
			</tr>
		</c:forEach>
	</table>
	<a href="add.html">New student</a>
</body>
</html>