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
            <th>ID de la Oferta</th>
            <th>ID de la Demanda</th>
            <th>Rating</th>
            <th>Horas totales</th>
            <th>Comentarios</th>
            <th colspan="2">Acciones</th>
        </tr>
        <c:forEach items="${collaborations}" var="collaboration">
            <tr>
                <td>${collaboration.collaboration_id}</td> 
                <td>${collaboration.offer_id}</td>
                <td>${collaboration.request_id}</td>
                <td>${collaboration.rating}</td>
                <td>${collaboration.totalHours}</td>
                <td>${collaboration.comments}</td>
                <td><a href="update/${collaboration.collaboration_id}.html">Editar</a> 
                <td><a href="delete/${collaboration.collaboration_id}.html">Borrar</a>
            </tr>
        </c:forEach>
    </table>
    <a href="add.html">Añadir</a> 
</body>
</html>
