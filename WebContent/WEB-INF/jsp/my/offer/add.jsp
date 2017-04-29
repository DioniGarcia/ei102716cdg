<%@page contentType="text/html; charset=iso-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Skill Sharing - Añadir Oferta</title>
<link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.min.css" rel="stylesheet" />

</head>
<body>
    <h2>Añadir Oferta</h2>
    <form:form method="post" modelAttribute="offer">
        <table>
        
        	<tr>
                <td><form:label path="skill_Id">Skill</form:label></td>
                <td>
               		<select id="prueba"></select>
					<form:select path="skill_Id" id="tipo"></form:select>
				</td>
            </tr>
            
            
            <tr>
            <!-- Linea separadora --><td><hr/></td> <td><hr/></td> <td><hr/></td>
            </tr>
            
            <tr>
                <td><form:label path="description">Descripcion de la oferta</form:label></td>
                <td colspan="3">
                	<form:input  path="description" /><br>
                	<form:errors class="error" path="description"/>	
                </td>
            </tr>
            
            <tr>
            <!-- Linea separadora --><td><hr/></td> <td><hr/></td> <td><hr/></td>
            </tr>
            
            <tr>
                <td><form:label path="startDate">Fecha de inicio</form:label></td>
                <td><form:input type="date" path="startDate" /></td>
            </tr>
            			 
            <tr>
            	<td><hr/></td> <!-- Linea separadora -->
            	<td><hr/></td>
            </tr>
            
            <tr>
                <td><form:label path="endDate">Fecha final</form:label></td>
                <td><form:input type="date" path="endDate" /></td>
            </tr>
            
            <form:input type="hidden" path="active" value="true"></form:input>
            <tr></tr>
            
            <tr>
                <td colspan="2"><input type="submit" value="Añadir" />
                </td>
            </tr>
        </table>
    </form:form>
    
    
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery-3.2.1.min.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/js/select2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/select-skill.js"></script>
</html>