<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html>
<head>
<title>Skill Sharing - Gestionar Administradores</title>
</head>
<body>
    <h1>Llista de Administradores</h1>
    <table>
        <tr>
        	<th>Nick</th>
        	<th>E-mail</th>
        	<th>Contraseña</th>
            <th colspan="2">Acciones</th>
        </tr>
        <c:forEach items="${administrators}" var="administrator"> 
            <tr>
            	<td>${administrator.nick}</td> 
            	<td>${administrator.email}</td> 
            	<td>${administrator.passwd}</td>
           
                <td><a href="update/${administrator.nick}.html">Editar</a> 
                <td><a href="delete/${administrator.nick}.html">Borrar</a>
            </tr>
        </c:forEach>
    </table>
    <a href="add.html">Añadir nuevo administrador</a> 
</body>
</html>
