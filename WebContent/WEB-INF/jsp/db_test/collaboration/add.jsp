<%@page contentType="text/html; charset=iso-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Skill Sharing - Añadir Colaboración</title>
</head>
<body>
    <h2>Nueva Colaboración</h2>
    <form:form method="post" modelAttribute="collaboration">
        <table>
			<tr>
                <td><form:label path="offer_id">Oferta</form:label></td>
                <td>
                	<form:select path="offer_id">
                		<c:forEach items="${offers}" var="offer">
	 						<form:option value="${offer.id}">${offer.description}</form:option>
	 					</c:forEach>
					</form:select >
				</td>
            </tr>
            
            <tr>
            	<td><hr/></td> <!-- Linea separadora -->
            	<td><hr/></td>
            </tr>
            
            <tr>
                <td><form:label path="request_id">Demanda</form:label></td>
                <td>
                	<form:select path="request_id">
                		<c:forEach items="${requests}" var="request">
	 						<form:option value="${request.id}">${request.description}</form:option>
	 					</c:forEach>
					</form:select >
				</td>
            </tr>
            
            <tr>
            	<td><hr/></td> <!-- Linea separadora -->
            	<td><hr/></td>
            </tr>
            
            <tr>
                <td><form:label path="rating">Puntuación</form:label></td>
                <td>
                	<form:select path="rating">
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
                <td colspan="2"><input type="submit" value="Añadir" />
                </td>
            </tr>
        </table>
    </form:form>
</body>
</html>
