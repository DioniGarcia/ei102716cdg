<%@page contentType="text/html; charset=iso-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Skill Sharing - Editar Mensaje</title>
</head>
<body>
    <h2>Editar Mensaje</h2>
    <form:form method="post" modelAttribute="message">
        <table>

            <tr>
                <td><form:label path="chat_id">Chat correspondiente</form:label></td>
                <td>
                	<form:select path="chat_id">
                		<c:forEach items="${chat_list}" var="chat">
	 						<form:option value="${chat.chat_id}">${chat.nick_user_one} - ${chat.nick_user_two}</form:option>
	 					</c:forEach>
					</form:select >
				</td>
				<td><form:errors path="chat_id" /></td>
            </tr>
            
           <tr>
                <td><form:label path="sender_nick">Nick estudiante que envia el mensaje</form:label></td>
                <td>
                	<form:select path="sender_nick">
						<form:options items="${nick_list}" />
					</form:select >
				</td>
				<td><form:errors path="sender_nick" /></td>
            </tr>
            
            <tr>
                <td ><form:label  path="content">Contenido</form:label></td>
                <td colspan="4"><form:input  path="content" /></td>
                <td><form:errors path="content" /></td>
            </tr>
            
            <tr>
                <td><form:label  path="sendingDate">Fecha de envio</form:label></td>
                <td><form:input  type="date" path="sendingDate" /></td>
                <td><form:errors path="sendingDate" /></td>
            </tr>
            
            <tr>
           		<td><form:label  path="active">Leido</form:label></td>
             	<td><form:checkbox path="active"></form:checkbox></td>
			</tr>
                            
            <tr>
            	<td><hr/></td> <!-- Linea separadora -->
            	<td><hr/></td>
            </tr>
            
            <tr></tr>
            
            <tr>
                <td colspan="2"><input type="submit" value="Editar mensaje" />
                </td>
            </tr>
        </table>
    </form:form>
</body>
</html>
