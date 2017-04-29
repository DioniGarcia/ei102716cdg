<%@page contentType="text/html; charset=iso-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
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
                <td>${skill.skill_id}</td> 
                <td>${skill.name}</td>
                <td>${skill.description}</td>
                <td>${skill.level}</td> 
                <td>${skill.active}</td>
                <td><a href="update/${skill.skill_id}.html">Editar</a> 
                <td><a href="delete/${skill.name}.html">Borrar</a>
            </tr>
        </c:forEach>
    </table>
    <a href="add.html">Añadir</a> 
</body>
</html>