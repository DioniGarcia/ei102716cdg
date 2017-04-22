<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html>
<head>
<title>Skill Sharing - Gestionar Chats</title>
</head>
<body>
    <h1>Llista de Chats</h1>
    <table>
        <tr>
        	<th>Id</th>
        	<th>Nick primer  usuario</th>
			<th>Nick segundo usuario</th>
            <th colspan="2">Acciones</th>
        </tr>
        <c:forEach items="${chats}" var="chat"> 
            <tr>
            	<td>${chat.chat_id}</td>
            	<td>${chat.nick_user_one}</td>
            	<td>${chat.nick_user_two}</td>
                <td><a href="update/${chat.chat_id}.html">Editar</a> 
                <td><a href="delete/${chat.chat_id}.html">Borrar</a>
            </tr>
        </c:forEach>
    </table>
    <a href="add.html">Añadir nuevo chat</a> 
</body>
</html>