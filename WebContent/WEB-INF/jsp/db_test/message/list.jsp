<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html>
<head>
<title>Skill Sharing - Gestionar Mensajes</title>
</head>
<body>
    <h1>Llista de Mensajes</h1>
    <table>
        <tr>
        	<th>ID</th>
        	<th>ID del Chat</th>
        	<th>Nick del estudiante que lo envia</th>
        	<th>Contenido</th>
        	<th>Fecha de envio</th>
        	<th>Leido</th>
            <th colspan="2">Acciones</th>
        </tr>
        <c:forEach items="${messages}" var="message"> 
            <tr>
            	<td>${message.message_id}</td>
            	<td>${message.chat_id}</td>
            	<td>${message.sender_nick}</td>
            	<td>${message.content}</td>
            	<td>${message.sendingDate}</td>
            	<td>${message.active}</td>
            	
           
                <td><a href="update/${message.message_id}.html">Editar</a> 
                <td><a href="delete/${message.message_id}.html">Borrar</a>
            </tr>
        </c:forEach>
    </table>
    <a href="add.html">Añadir nuevo mensaje</a> 
</body>
</html>
