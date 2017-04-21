<%@page contentType="text/html; charset=iso-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Skill Sharing - Editar Administrador</title>
</head>
<body>
    <h2>Editar Administrador</h2>
    <form:form method="post" modelAttribute="administrator">
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
            
            <tr></tr>
            
            <tr>
                <td colspan="2"><input type="submit" value="Editar estudiante" />
                </td>
            </tr>
        </table>
    </form:form>
</body>
</html>