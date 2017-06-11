<%@page contentType="text/html; charset=iso-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:paginaperfil>
<jsp:body> 
    <h2>Editar Oferta</h2>
    <form:form method="post" modelAttribute="offer">
        
        <form:input type="hidden" path="student_nick"></form:input>
        <form:input type="hidden" path="skill_Id"></form:input>
        
        <table>
        	
        	<tr>
                <td><b>Skill:</b></td>
                <td>
               		${ skill.name } - ${ skill.description }
				</td>
            </tr>
            
            
            <tr>
            <!-- Linea separadora --><td><hr/></td> <td><hr/></td> <td><hr/></td>
            </tr>
            
            <tr>
                <td><form:label path="description">Descripción</form:label></td>
                <td>
                	<form:textarea class="form-control" type="text" path="description" /><br>
                	<form:errors class="error" path="description"/>	
                </td>
            </tr>
            
            <tr>
            <!-- Linea separadora --><td><hr/></td> <td><hr/></td> <td><hr/></td>
            </tr>
            
            <tr>
                <td><form:label path="startDate">Fecha inicio</form:label></td>
                <td>
                	<form:input data-toggle="datepicker" class="form-control" path="startDate" placeholder="dd/mm/yyyy" />
					<div data-toggle="datepicker"></div>
					<form:errors class="error" path="startDate"/>	
                </td>
            </tr>
            			 
            <tr>
            	<td><hr/></td> <!-- Linea separadora -->
            	<td><hr/></td>
            </tr>
            
            <tr>
                <td><form:label path="endDate">Fecha fin</form:label></td>
                <td>
                	<form:input data-toggle="datepicker" class="form-control" path="endDate" placeholder="dd/mm/yyyy" />
					<div data-toggle="datepicker"></div>
					<form:errors class="error" path="endDate"/>	
                </td>
            </tr>
            
            <form:input type="hidden" path="active" value="true"></form:input>
            <tr></tr>
            
            <tr>
                <td colspan="2"><input type="submit" class="btn btn-primary" value="Editar" />
                </td>
            </tr>
        </table>
    </form:form>
    
    
</jsp:body>
</t:paginaperfil>