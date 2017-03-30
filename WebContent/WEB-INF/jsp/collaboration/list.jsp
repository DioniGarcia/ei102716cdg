<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html>
<head>
<title>Skill Sharing - Gestionar Colaboraciones</title>
</head>
<body>
    <h1>Llista de Colaboraciones</h1>
    <table>
        <tr>
            <th>ID</th>
            <th>Fecha de Inicio</th>
            <th>Fecha fin</th>
            <th>Horas Totales</th>
            <th>Comentarios</th>
            <th>Rating</th>
            <th>IdOferta</th>
            <th>IdDemanda</th>
            <th colspan="2">Acciones</th>
        </tr>
        <c:forEach items="${collaborations}" var="collaboration">
            <tr>
                <td>${collaboration.id}</td> 
                <td>${collaboration.startDate}</td>
                <td>${collaboration.endDate}</td>
                <td>${collaboration.totalHours}</td>
                <td>${collaboration.comments}</td>
                <td>${collaboration.rating}</td>
                <td>${collaboration.offerId}</td>
                <td>${collaboration.requestId}</td>
                <td><a href="update/${collaboration.id}.html">Editar</a> 
                <td><a href="delete/${collaboration.id}.html">Borrar</a>
            </tr>
        </c:forEach>
    </table>
    <a href="add.html">Editar</a> 
</body>
</html>
