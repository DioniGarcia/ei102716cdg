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
        	<th>Nick</th>
        	<th>E-mail</th>
        	<th>Contraseña</th>
        	<th>Nombre</th>
        	<th>Dni</th>
        	<th>Avatar</th>
            <th colspan="2">Acciones</th>
        </tr>
        <c:forEach items="${students}" var="student"> 
            <tr>
            	<td>${student.nick}</td> 
            	<td>${student.email}</td> 
            	<td>${student.passwd}</td>
            	<td>${student.name}</td> 
            	<td>${student.dni}</td> 
            	<td>${student.avatar}</td>  
           
                <td><a href="update/${student.nick}.html">Editar</a> 
                <td><a href="delete/${student.nick}.html">Borrar</a>
            </tr>
        </c:forEach>
    </table>
    <a href="add.html">Añadir nuevo estudiante</a> 
</body>
</html>
