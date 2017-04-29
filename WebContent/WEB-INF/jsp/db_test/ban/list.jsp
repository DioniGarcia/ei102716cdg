<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html>
<head>
<title>Skill Sharing - Gestionar Baneos</title>
</head>
<body>
    <h1>Llista de Baneos</h1>
    <table>
        <tr>
        	<th>Nick estudiante</th>
        	<th>Fecha inicio del baneo</th>
			<th>Dias de castigo</th>
			<th>Motivo</th>     	
            <th colspan="2">Acciones</th>
        </tr>
        <c:forEach items="${bans}" var="ban"> 
            <tr>
            	<td>${ban.student_nick}</td>
            	<td>${ban.banDate}</td>
            	<td>${ban.days}</td>
            	<td>${ban.reason}</td>
           
                <td><a href="update/${ban.student_nick}.html">Editar</a> 
                <td><a href="delete/${ban.student_nick}.html">Borrar</a>
            </tr>
        </c:forEach>
    </table>
    <a href="add.html">Añadir nuevo baneo</a> 
</body>
</html>
