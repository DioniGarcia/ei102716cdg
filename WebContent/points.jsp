<%@page contentType="text/html; charset=iso-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:paginaperfil>
<jsp:body> 

	<div class="points-container">
		<div class="points-main">
			<div class="profile-container">
				<div class="profile-photo">
					<img style="margin-bottom: 10px;" src="${ pageContext.request.contextPath }/resources/img/background.jpg" class="img-circle-thumbnail" alt="" width="80" height="80">
					<span>Puntuación media:</span>
					<select class="star-readonly">
					<option value="0">0</option>
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3" selected="selected">3</option>
					<option value="4">4</option>
					<option value="5">5</option>
					</select>
				</div>
				<div class="profile-details">
					
					<h3><b>Nombre:&emsp;</b><c:out value="${student.name }"></c:out></h3></td>
		
				</div>
			</div>
			
			<div class="points-hours">
			<div>Numero de horas recibidas : 5</div>
			<div>Numero de horas prestadas : 10</div>
			
			</div>
			
			<div class="points-balance">
				<svg width="50%" height="50%" viewBox="0 0 42 42" class="donut">
				  <circle class="donut-hole" cx="21" cy="21" r="15.91549430918954" fill="#fff"></circle>
				  <circle class="donut-ring" cx="21" cy="21" r="15.91549430918954" fill="transparent" stroke="#ffffff" stroke-width="3"></circle>
				
				  <circle class="donut-segment" cx="21" cy="21" r="15.91549430918954" fill="transparent" stroke="#7cb518" stroke-width="3" stroke-dashoffset="24" stroke-dasharray="32 1" ></circle>
				  <text x="50%" y="50%" text-anchor="middle" stroke="#000000" stroke-width="0.01em" dy=".18em">5</text>
				  <text font-size="5" x="50%" y="50%" text-anchor="middle" stroke="#000000" stroke-width="0.005em" dy="1.6em">puntos</text>
				</svg>
			</div>
		</div>
		
		<div class="points-help">
			<fieldset>
			<legend>Title</legend>
			Lorem epfjepfojsp feps ofjefsoefsidvnsoefi ogihso isoiñnñoinvosn oehs noeisoh ishnoisno insnoñie
			</fieldset>
		</div>
	
	</div>

</jsp:body>
</t:paginaperfil>