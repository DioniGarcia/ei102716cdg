<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html>
<head>
<title>Skill Sharing - Gestionar Estudiantes</title>
</head>
<body>
    <h1>Llista de Estudiantes</h1>
    <table>
        <tr>
            <th>NIF</th>
            <th>Nombre</th>
            <th>Email</th>
            <th>Nick</th>
            <th>Constraseña</th>
            <th colspan="2">Acciones</th>
        </tr>
        <c:forEach items="${students}" var="student"> 
            <tr>
                <td>${student.nif}</td> 
                <td>${student.name}</td>
                <td>${student.email}</td>
                <td>${student.userName}</td>
                <td>${student.passwd}</td>
                <td><a href="update/${student.userName}.html">Edita</a> 
                <td><a href="delete/${student.userName}.html">Esborra</a>
            </tr>
        </c:forEach>
    </table>
    <a href="add.html">Añadir</a> 
</body>
</html>
