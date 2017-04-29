<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html>
<head>
<title>Skill Sharing - Gestionar Demanda</title>
</head>
<body>
    <h1>Llista de Demandas</h1>
    <table>
        <tr>
            <th>ID</th>
            <th>Fecha de Inicio</th>
            <th>Fecha fin</th>
            <th>Descripción</th>
            <th>Demandante</th>
            <th>Skill</th>
            <th>Activa</th>
            <th colspan="2">Acciones</th>
        </tr>
        <c:forEach items="${requests}" var="request"> 
            <tr>
                <td>${request.id}</td> 
                <td>${request.startDate}</td>
                <td>${request.endDate}</td>
                <td>${request.description}</td>
                <td>${request.student_nick}</td>
                <td>${request.skill_Id}</td>
				<td>${request.active}</td>
                <td><a href="update/${request.id}.html">Editar</a> 
                <td><a href="delete/${request.id}.html">Borrar</a>
            </tr>
        </c:forEach>
    </table>
    <a href="add.html">Añadir</a> 
</body>
</html>