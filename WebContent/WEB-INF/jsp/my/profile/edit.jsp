<%@page contentType="text/html; charset=iso-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:paginaperfil>
<jsp:body> 
	<h2>Perfil</h2><br>
	<form:form method="post" modelAttribute="student">
	<div class="profile-container">
		<div class="profile-photo">
			<img style="margin-bottom: 10px;" src="${ pageContext.request.contextPath }/resources/img/avatars/${student.avatar}.jpg" class="img-circle-thumbnail" alt="" width="120" height="120">
			<span>Puntuación media:</span>
			<select class="star-readonly">
			<t:rating rating="${rating }"/>
			</select>
			<a style="margin-top: 15px;" href="edit.html"><button class="btn btn-primary">Guardar</button></a>
		</div>
		<div class="profile-details">
		
		<table>
		<tr>
			<td><h3><b>Nick:&emsp;</b></h3></td>
			<td><h3><c:out value="${student.nick }"></c:out></h3></td>
			
		</tr>
		<tr>
			<td><h3><b>Nombre:&emsp;</b></h3></td>
			<td><h3><form:input  path="name" /></h3></td>
		</tr>
		<tr>
			<td><h3><b>Email:&emsp;</b></h3></td>
			<td><h3><form:input  path="email" /></h3></td>
		</tr>
		</table>
		
		</div>
	</div>
	<form:input type="hidden" path="nick" />
	<form:input type="hidden" path="passwd" />
	<form:input type="hidden" path="dni" />
	<form:input type="hidden" path="avatar" />
    </form:form>
   
    
    
</jsp:body>
</t:paginaperfil>