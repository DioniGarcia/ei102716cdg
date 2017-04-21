<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html>
<head>
<title>Skill Sharing - Gestionar Ofertas</title>
</head>
<body>
    <h1>Llista de Ofertas</h1>
    <table>
        <tr>
            <th>ID</th>
            <th>Fecha de Inicio</th>
            <th>Fecha fin</th>
            <th>Descripción</th>
            <th>Ofertante</th>
            <th>Skill</th>
            <th>Activa</th>
            <th colspan="2">Acciones</th>
        </tr>
        <c:forEach items="${offers}" var="offer"> 
            <tr>
                <td>${offer.id}</td> 
                <td>${offer.startDate}</td>
                <td>${offer.endDate}</td>
                <td>${offer.description}</td>
                <td>${offer.student_nick}</td>
                <td>${offer.skill_Id}</td>
                <td>${offer.active}</td>
                <td><a href="update/${offer.id}.html">Editar</a> 
                <td><a href="delete/${offer.id}.html">Borrar</a>
            </tr>
        </c:forEach>
    </table>
    <a href="add.html">Añadir</a> 
</body>
</html>
