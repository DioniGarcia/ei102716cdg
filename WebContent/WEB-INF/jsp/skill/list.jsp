<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html>
<head>
<title>Skill Sharing - Listar Skills</title>
</head>
<body>
    <h1>Llista de Skills</h1>
    <table>
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Descripcion</th>
            <th>Nivel</th>
            <th>Status</th>
            <th colspan="2">Acción</th>
        </tr>
        <c:forEach items="${skills}" var="skill"> 
            <tr>
                <td>${skill.id}</td> 
                <td>${skill.name}</td>
                <td>${skill.description}</td>
                <td>${skill.level}</td> 
                <td>${skill.status}</td>
                <td><a href="update/${skill.id}.html">Editar</a> 
                <td><a href="delete/${skill.id}.html">Borrar</a>
            </tr>
        </c:forEach>
    </table>
    <a href="add.html">Añadir</a> 
</body>
</html>
