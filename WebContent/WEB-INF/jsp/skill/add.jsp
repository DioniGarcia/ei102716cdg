<%@page contentType="text/html; charset=iso-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Skill Sharing - Anyadir Skill</title>
</head>
<body>
    <h2>Nueva Skill</h2>
    <form:form method="post" modelAttribute="skillWrapper">
        <table>
            <tr> <td/> <td/> <td>Habilitado</td> </tr>
            <tr>
                <td><form:label path="name">Habilidad</form:label></td>
                <td><form:input path="name" /></td>
            </tr>
            
            <tr>
            <!-- Linea separadora --><td><hr/></td> <td><hr/></td> <td><hr/></td>
            </tr>
            
            <tr>
                <td><form:label path="descIniciado">Descripcion nivel Iniciado</form:label></td>
                <td><form:input  path="descIniciado" /></td>
                <td><form:checkbox path="statusIniciado" /><td>
            </tr>
            
            <tr>
            <!-- Linea separadora --><td><hr/></td> <td><hr/></td> <td><hr/></td>
            </tr>
            
            <tr>
                <td><form:label path="descMedio">Descripcion nivel Medio</form:label></td>
                <td><form:input  path="descMedio" /></td>
                <td><form:checkbox path="statusMedio" /><td>
            </tr>
            
            <tr>
            <!-- Linea separadora --><td><hr/></td> <td><hr/></td> <td><hr/></td>
            </tr>
            
            <tr>
                <td><form:label path="descExperto">Descripcion nivel Experto</form:label></td>
                <td><form:input  path="descExperto" /></td>
                <td><form:checkbox path="statusExperto" /><td>
            </tr>


            <tr>
            	<td><hr/></td> <!-- Linea separadora -->
            	<td><hr/></td>
            </tr>
            
            <tr></tr>
            
            <tr>
                <td colspan="2"><input type="submit" value="Añadir" />
                </td>
            </tr>
        </table>
    </form:form>
</body>
</html>
