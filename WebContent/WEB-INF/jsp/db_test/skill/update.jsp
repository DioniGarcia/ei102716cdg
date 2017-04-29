<%@page contentType="text/html; charset=iso-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Skill Sharing - Modificar Skill</title>
</head>
<body>
    <h2>Editar Skill</h2>
    <form:form method="post" modelAttribute="skill">
    	<table>
            <tr>
                <td><form:label path="name">Nombre</form:label></td>
                <td><form:input path="name" /></td>
            </tr>
            <tr>
                <td><form:label path="description">Descripción</form:label></td>
                <td><form:input path="description" /></td>
            </tr>
            <tr>
                <td><form:label path="level">Nivel</form:label></td>
                <td><form:input path="level" readonly="true"/></td>
            </tr>
            <tr>
                <td><form:label path="active">Activo</form:label></td>
                <td><form:checkbox path="active" /><td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="Editar" />
                </td>
            </tr>
		</table>
    </form:form>
</body>
</html>