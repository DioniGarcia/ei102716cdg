<%@page contentType="text/html; charset=iso-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Skill Sharing - Anyadir Estudiante</title>
</head>
<body>
    <h2>Nuevo Estudiante</h2>
    <form:form method="post" modelAttribute="student">
        <table>
            <tr>
                <td><form:label path="userName">Nick</form:label></td>
                <td><form:input path="userName" /></td>
                <td><form:errors path="userName" /></td>
            </tr>
            <tr>
                <td><form:label path="passwd">Password</form:label></td>
                <td><form:input type="password" path="passwd" /></td>
                <td><form:errors type="password" path="passwd" /></td>
            </tr>
            
            <tr>
            	<td><hr/></td> <!-- Linea separadora -->
            	<td><hr/></td>
            </tr>
            
            <tr>
            	<td><form:label path="nif">NIF</form:label></td>
                <td><form:input path="nif" /></td>
                <td><form:errors path="nif" /></td>
            </tr>
            
            <tr>
                <td><form:label path="name">Name</form:label></td>
                <td><form:input path="name" /></td>
                <td><form:errors path="name" /></td>
            </tr>
            <tr>
                <td><form:label path="email">E-mail</form:label></td>
                <td><form:input path="email" /></td>
                <td><form:errors path="email" /></td>
            </tr>            
            
            <tr>
            	<td><hr/></td> <!-- Linea separadora -->
            	<td><hr/></td>
            </tr>
            
            <tr></tr>
            
            <tr>
                <td colspan="2"><input type="submit" value="Añadir estudiante" />
                </td>
            </tr>
        </table>
    </form:form>
</body>
</html>
