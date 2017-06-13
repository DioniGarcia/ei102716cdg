<%@page contentType="text/html; charset=iso-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:paginaperfil>
<jsp:body> 

	<div class="points-container">
		<div class="points-main">
		<h2>Balance de puntos</h2>
    	<hr>
    	<h4><b>Usuario: </b><c:out value="${student.name }"></c:out></h4>
    	<br>
			<div class="profile-container">
				<div class="profile-photo">
					<img style="margin-bottom: 10px;" src="${ pageContext.request.contextPath }/resources/img/avatars/${student.avatar}.jpg" class="img-circle-thumbnail" alt="" width="80" height="80">
				</div>
				<div class="profile-details">
					<span style="margin-right:12px">Puntuación media</span>
					<select class="star-readonly">
					<t:rating rating="${rating }"/>
					</select>
				</div>
			</div>
			
			<div class="points-hours">
			<div>Horas recibidas : ${receivedHours}</div>
			<div>Horas prestadas : ${offeredHours}</div>
			</div>
			
			<hr>
			
			<div class="points-balance">
				<c:choose>
					<c:when test="${totalHours ge 20 }">
						<c:set var="pointsColor" value="#7cb518"></c:set>
					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="${totalHours gt 5 }">
								<c:set var="pointsColor" value="#fff200"></c:set>
							</c:when>
							<c:otherwise>
								<c:set var="pointsColor" value="#ff0000"></c:set>
							</c:otherwise>
						</c:choose>
					
					</c:otherwise>
				
				</c:choose>
				
				<svg width="60%" height="50%" viewBox="0 0 42 42" class="donut">
				  <circle class="donut-hole" cx="21" cy="21" r="15.91549430918954" fill="#fff"></circle>
				  <circle class="donut-ring" cx="21" cy="21" r="15.91549430918954" fill="transparent" stroke="#ffffff" stroke-width="3"></circle>
				
				  <circle class="donut-segment" cx="21" cy="21" r="15.91549430918954" fill="transparent" stroke="${pointsColor }" stroke-width="3" stroke-dashoffset="24" stroke-dasharray="32 1" ></circle>
				  <text x="50%" y="50%" text-anchor="middle" stroke="#000000" stroke-width="0.01em" dy=".18em">${totalHours }</text>
				  <text font-size="5" x="50%" y="50%" text-anchor="middle" stroke="#000000" stroke-width="0.005em" dy="1.6em">puntos</text>
				</svg>
				
			</div>
		</div>
		
		<div class="points-help" >
			
			<fieldset>
			<legend>Sistema de Puntuación </legend>
			<p style="color:white;"> 
			
			<br><br>-Consulta periódicamente tu balance de puntos desde el menu de perfil.
			
			<br><br>-La cantidad inicial de puntos serán de 20.
			
			<br><br>-Para poder recibir ayuda debe el saldo ser superior a 0 puntos.
			
			<br><br>-Seran premiadas aquellas colaboraciones prestadas en función de la cantidad de horas de servicio teniendo en cuenta como puntuen 
			las personas que reciben la ayuda, en un rango de 1 a 5.
			<br><br>-Por servicio recibido serán restados una cantidad de puntos equivalente al número de horas recibidas. 
			
			<br><br>-Por cada oferta publicada se sumarán dos puntos al balance. Usuarios con 0 puntos, puede volver a estar activo!
			
			<br><br>Hacer un uso responsable del sistema, permite disfrutar de una herramienta que promueva el intercambio de conocimiento entre estudiantes sin coste económico.
			</p>
			
			</fieldset>
		</div>
	
	</div>

</jsp:body>
</t:paginaperfil>