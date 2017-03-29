<%@page contentType="text/html; charset=iso-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Skill Sharing - Añadir Oferta</title>
</head>
<body>
    <h2>Nueva Oferta</h2>
    <form:form method="post" modelAttribute="offer">
        <table>
            <tr>
                <td><form:label path="startDate">Fecha de inicio</form:label></td>
                <td><form:input type="date" path="startDate" /></td>
            </tr>
            
            <tr>
            <!-- Linea separadora --><td><hr/></td> <td><hr/></td> <td><hr/></td>
            </tr>
            
             <tr>
                <td><form:label path="startDate">Fecha final</form:label></td>
                <td><form:input type="date" path="endDate" /></td>
            </tr>
            
            <tr>
            <!-- Linea separadora --><td><hr/></td> <td><hr/></td> <td><hr/></td>
            </tr>
            
            <tr>
                <td><form:label path="description">Descripcion de la oferta</form:label></td>
                <td colspan="3"><form:input  path="description" /></td>
            </tr>
            
            <tr>
            <!-- Linea separadora --><td><hr/></td> <td><hr/></td> <td><hr/></td>
            </tr>
            
            <tr>
                <td><form:label path="nif">Nif del ofertante</form:label></td>
                <td>
                	<form:select path="nif">
						<form:options items="${nifs }" />
					</form:select >
				</td>
            </tr>


            <tr>
            	<td><hr/></td> <!-- Linea separadora -->
            	<td><hr/></td>
            </tr>
            
            <tr>
                <td><form:label path="skillId">Skill</form:label></td>
                <td>
                	<form:select path="skillId">
                		<c:forEach items="${skills}" var="skill">
	 						<form:option value="${skill.id}">"{$skill.name}"</form:option>
	 					</c:forEach>
					</form:select >
				</td>
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
