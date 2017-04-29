<%@page contentType="text/html; charset=iso-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Skill Sharing - Anyadir Skill</title>
</head>
<body>
    <h2>Nueva Skill</h2>
    <form:form method="post" modelAttribute="skillWrapper">
        
        <table>
            <tr>
                <td><form:label path="name">Habilidad</form:label></td>
                <td><form:input path="name" /></td>
			</tr>
	
			<tr>
            <!-- Linea separadora --><td><hr/></td> <td><hr/></td>
            </tr>
            
            <tr><td><br></td></tr>
            
             <tr>
                <td></td>
                <td>Descripción</td>
                <td>Habilitado</td>
			</tr>
			
			
				
            <c:forEach items="${skillWrapper.skills}" var="skill" varStatus="i">
            	<tr>
	            <!-- Linea separadora --><td><hr/></td> <td><hr/></td> <td><hr/></td>
	            </tr>
	            
            	<tr>
            	<td><form:label path="descriptionList[${i.index}]">Nivel ${skillWrapper.levelList[i.index]}</form:label></td>
               	<td><form:input path="descriptionList[${i.index}]"></form:input></td>
                <td><form:checkbox path="activeList[${i.index}]"></form:checkbox></td>
                </tr>
                
            </c:forEach>
            
            <tr>
            <!-- Linea separadora --><td><hr/></td> <td><hr/></td> <td><hr/></td>
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