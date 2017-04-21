<%@page contentType="text/html; charset=iso-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Skill Sharing - Editar Estudiante</title>
</head>
<body>
    <h2>Editar Estudiante</h2>
    <form:form method="post" modelAttribute="student">
        <table>
            <tr>
                <td><form:label  path="nick">Nick</form:label></td>
                <td><form:input  path="nick" /></td>
                <td><form:errors path="nick" /></td>
            </tr>
            <tr>
                <td><form:label  path="email">E-mail</form:label></td>
                <td><form:input  path="email" /></td>
                <td><form:errors path="email" /></td>
            </tr>
            <tr>
                <td><form:label  path="passwd">Contraseña</form:label></td>
                <td><form:input  path="passwd" /></td>
                <td><form:errors path="passwd" /></td>
            </tr>
            
            <tr>
            	<td><hr/></td> <!-- Linea separadora -->
            	<td><hr/></td>
            </tr>
            
             <tr>
                <td><form:label  path="name">Nombre</form:label></td>
                <td><form:input  path="name" /></td>
                <td><form:errors path="name" /></td>
            </tr>
            <tr>
            	<td><form:label  path="dni">Dni</form:label></td>
                <td><form:input  path="dni" /></td>
                <td><form:errors path="dni" /></td>
            </tr>
            <tr>
                <td><form:label  path="points">Puntos</form:label></td>
                <td><form:input  path="points" /></td>
                <td><form:errors path="points" /></td>
            </tr>
            
            <tr>
            	<td><hr/></td> <!-- Linea separadora -->
            	<td><hr/></td>
            </tr>
            
            <tr></tr>
            
            <tr>
                <td colspan="2"><input type="submit" value="Editar estudiante" />
                </td>
            </tr>
        </table>
    </form:form>
</body>
</html>