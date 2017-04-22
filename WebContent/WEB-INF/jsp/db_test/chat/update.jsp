<%@page contentType="text/html; charset=iso-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Skill Sharing - Editar Chat</title>
</head>
<body>
    <h2>Editar Chat</h2>
    <form:form method="post" modelAttribute="chat">
        <table>          
            
            <tr>
                <td><form:label path="nick_user_one">Nick primer estudiante</form:label></td>
                <td>
                	<form:select path="nick_user_one">
						<form:options items="${nick_list}" />
					</form:select >
				</td>
				<td><form:errors path="nick_user_one" /></td>
            </tr>
                        
            <tr>
            	<td><hr/></td> <!-- Linea separadora -->
            </tr>

			 
            <tr>
                <td><form:label path="nick_user_two">Nick segundo estudiante</form:label></td>
                <td>
                	<form:select path="nick_user_two">
						<form:options items="${nick_list}" />
					</form:select >
				</td>
            </tr>

            <tr>
            	<td><hr/></td> <!-- Linea separadora -->
            	<td><hr/></td>
            </tr>
            
            <tr></tr>
            
            <tr>
                <td colspan="2"><input type="submit" value="Editar chat" />
                </td>
            </tr>
        </table>
    </form:form>
</body>
</html>