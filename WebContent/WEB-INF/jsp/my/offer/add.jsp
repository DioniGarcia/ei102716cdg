<%@page contentType="text/html; charset=iso-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:paginabasica>
<jsp:body> 
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
                <td colspan="1">
                	<form:input class="form-control" type="text" path="description" /><br>
                	<form:errors class="error" path="description"/>	
                </td>
            </tr>
            
            <tr>
            <!-- Linea separadora --><td><hr/></td> <td><hr/></td> <td><hr/></td>
            </tr>
            
            <tr>
                <td><form:label path="startDate">Fecha de inicio</form:label></td>
                <td><form:input class="form-control" type="date" path="startDate" /></td>
            </tr>
            			 
            <tr>
            	<td><hr/></td> <!-- Linea separadora -->
            	<td><hr/></td>
            </tr>
            
            <tr>
                <td><form:label path="endDate">Fecha final</form:label></td>
                <td><form:input class="form-control" type="date" path="endDate" /></td>
            </tr>
            
            <form:input type="hidden" path="active" value="true"></form:input>
            <tr></tr>
            
            <tr>
                <td colspan="2"><input type="submit" class="btn btn-primary" value="Añadir" />
                </td>
            </tr>
        </table>
    </form:form>
    
    
</jsp:body>
</t:paginabasica>