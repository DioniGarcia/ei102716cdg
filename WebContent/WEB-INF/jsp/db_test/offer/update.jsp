<%@page contentType="text/html; charset=iso-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Skill Sharing - Editar Oferta</title>
</head>
<body>
    <h2>Editar Oferta</h2>
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
                <td><form:label path="student_nick">Nif del ofertante</form:label></td>
                <td>
                	<form:select path="student_nick">
						<form:options items="${nick_list}" />
					</form:select >
				</td>
            
            </tr>
			 

            <tr>
            	<td><hr/></td> <!-- Linea separadora -->
            	<td><hr/></td>
            </tr>
            
            
            <tr>
                <td><form:label path="skill_Id">Skill</form:label></td>
                <td>
                	<form:select path="skill_Id">
                		<c:forEach items="${skill_list}" var="skill">
	 						<form:option value="${skill.skill_id}">${skill.name} - Nivel ${skill.level}</form:option>
	 					</c:forEach>
					</form:select >
				</td>
            </tr>
            
             <tr>
            	<td><hr/></td> <!-- Linea separadora -->
            	<td><hr/></td>
            </tr>
            
            <tr>
                <td><form:label path="active">Activa</form:label></td>
                <td><form:checkbox path="active"></form:checkbox></td>
            </tr>
            
            <tr></tr>
            
            <tr>
                <td colspan="2"><input type="submit" value="Editar" />
                </td>
            </tr>
        </table>
    </form:form>
</body>
</html>