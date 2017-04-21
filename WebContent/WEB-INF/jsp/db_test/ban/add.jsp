<%@page contentType="text/html; charset=iso-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Skill Sharing - Añadir Baneo</title>
</head>
<body>
    <h2>Nuevo Baneo</h2>
    <form:form method="post" modelAttribute="ban">
        <table>
            <tr>
                <td><form:label  path="student_nick">Nick estudiante</form:label></td>
                <td><form:input  path="student_nick" /></td>
                <td><form:errors path="student_nick" /></td>
            </tr>
            <tr>
                <td><form:label  path="banDate">Fecha inicio del baneo</form:label></td>
                <td><form:input  type="date" path="banDate" /></td>
                <td><form:errors path="banDate" /></td>
            </tr>
            <tr>
                <td><form:label  path="days">Dias de castigo</form:label></td>
                <td><form:input  path="days" /></td>
                <td><form:errors path="days" /></td>
            </tr>
            
            <tr>
            	<td><hr/></td> <!-- Linea separadora -->
            	<td><hr/></td>
            </tr>
            
             <tr>
                <td><form:label  path="reason">Razon del castigo</form:label></td>
                <td><form:input  path="reason" /></td>
                <td><form:errors path="reason" /></td>
            </tr>
            
            <tr>
            	<td><hr/></td> <!-- Linea separadora -->
            	<td><hr/></td>
            </tr>
            
            <tr></tr>
            
            <tr>
                <td colspan="2"><input type="submit" value="Añadir baneo" />
                </td>
            </tr>
        </table>
    </form:form>
</body>
</html>
