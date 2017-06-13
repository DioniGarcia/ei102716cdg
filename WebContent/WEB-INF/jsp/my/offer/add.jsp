<%@page contentType="text/html; charset=iso-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<style>
.label_in{
    padding: 0;
    padding-right: 24px;
}

.btn_bot {
	margin-top: 34px;
}

#description{
	width: 338px;
	height: 82px;
}

</style>

<t:paginabasica>
<jsp:body> 
    <h2>Crear Oferta</h2>
    <hr>
    <form:form method="post" modelAttribute="offer">
        <table>
        
        	<tr>
                <td class="label_in"><form:label path="skill_Id">Habilidad:</form:label></td>
                <td>
               		<select name="nombre" id="nombre"><option></option></select>
					<form:select path="skill_Id" id="tipo"></form:select><br>
					<form:errors class="error" path="skill_Id"/>	
				</td>
            </tr>
            
            
            <tr>
            <!-- Linea separadora --><td><hr/></td> <td><hr/></td> <td><hr/></td>
            </tr>
            
            <tr>
                <td class="label_in"><form:label path="description">Descripción:</form:label></td>
                <td>
                	<form:textarea class="form-control" type="text" path="description" /><br>
                	<form:errors class="error" path="description"/>	
                </td>
            </tr>
            
            <tr>
            <!-- Linea separadora --><td><hr/></td> <td><hr/></td> <td><hr/></td>
            </tr>
            
            <tr>
                <td class="label_in"><form:label path="startDate">Fecha inicio:</form:label></td>
                <td>
                	<form:input data-toggle="datepicker-1" class="form-control" path="startDate" placeholder="dd/mm/yyyy" />
					<div data-toggle="datepicker-1"></div>
					<form:errors class="error" path="startDate"/>	
                </td>
            </tr>
            			 
            <tr>
            	<td><hr/></td> <!-- Linea separadora -->
            	<td><hr/></td>
            </tr>
            
            <tr>
                <td class="label_in"><form:label path="endDate">Fecha fin:</form:label></td>
                <td>
                	<form:input data-toggle="datepicker-2" class="form-control" path="endDate" placeholder="dd/mm/yyyy" />
					<div data-toggle="datepicker-2"></div>
					<form:errors class="error" path="endDate"/>	
                </td>
            </tr>
            
            <form:input type="hidden" path="active" value="true"></form:input>
            <tr></tr>
            
            <tr>
                <td colspan="2"><input type="submit" class="btn btn_bot btn-primary btn-w-h" value="Añadir" />
                </td>
            </tr>
        </table>
    </form:form>
    
    <c:if test="${not empty nombre}">
    	<script type="text/javascript">
    		var nombreSkill = "${nombre}";     
    		var skillId = "${offer.skill_Id}";
    	</script>
    </c:if>
</jsp:body>
</t:paginabasica>