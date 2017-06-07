<%@page contentType="text/html; charset=iso-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>


<t:paginabasica>
<jsp:body>   


<form:form method="post" modelAttribute="collaboration">
<form:input type="hidden" path="offer_id"/>
<form:input type="hidden" path="request_id"/>
<table>
            
            <tr>
                <td><form:label path="rating">Puntuación</form:label></td>
                <td>
                	<form:select cssClass="star-rating" path="rating">
						<form:option value="0" />
						<form:option value="1" />
						<form:option value="2" />
						<form:option value="3" />
						<form:option value="4" />
						<form:option value="5" />
					</form:select >
				</td>
            </tr>

            <tr>
            <!-- Linea separadora --><td><hr/></td> <td><hr/></td> <td><hr/></td>
            </tr>
            
            <tr>
                <td><form:label path="totalHours">Horas Totales</form:label></td>
                <td><form:input  path="totalHours" /></td>
            </tr>
            
            <tr>
            <!-- Linea separadora --><td><hr/></td> <td><hr/></td> <td><hr/></td>
            </tr>
            
            <tr>
                <td><form:label path="comments">Comentario</form:label></td>
                <td colspan="3"><form:input  path="comments" /></td>
            </tr>
            
            <tr></tr>
            
            <tr>
                <td colspan="2"><input style="margin-top:30px;" class="btn btn-primary" type="submit" value="Editar" />
                </td>
            </tr>
        </table>
</form:form>
</jsp:body>
</t:paginabasica>
