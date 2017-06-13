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
    	<br>
			<div class="profile-container">
				<div class="profile-photo">
					<img style="margin-bottom: 10px;" src="${ pageContext.request.contextPath }/resources/img/avatars/${student.avatar}.jpg" class="img-circle-thumbnail" alt="" width="80" height="80">
				</div>
			</div>
			
			<div class="profile-container">
				<div class="profile-photo">
					<select class="star-readonly">
					<t:rating rating="${rating }"/>
					</select>
				</div>
			</div>
			
			
			
			
			<div class="points-hours">
				<h4><b>Usuario: </b><c:out value="${student.name }"></c:out></h4>
				<h4><b>Horas recibidas: </b> <c:out value="${receivedHours}"></c:out> </h4>
				<h4><b>Horas prestadas: </b> <c:out value="${offeredHours}"></c:out> </h4>
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
				
				<svg width="65%" height="65%" viewBox="0 0 42 42" class="donut">
				  <circle class="donut-hole" cx="21" cy="21" r="15.91549430918954" fill="#fff"></circle>
				  <circle class="donut-ring" cx="21" cy="21" r="15.91549430918954" fill="transparent" stroke="#ffffff" stroke-width="3"></circle>
				
				  <circle class="donut-segment" cx="21" cy="21" r="15.91549430918954" fill="transparent" stroke="${pointsColor }" stroke-width="3" stroke-dashoffset="24" stroke-dasharray="32 1" ></circle>
				  <text x="50%" y="50%" text-anchor="middle" stroke="#000000" stroke-width="0.01em" dy=".18em">${totalHours }</text>
				  <text font-size="5" x="50%" y="50%" text-anchor="middle" stroke="#000000" stroke-width="0.005em" dy="1.6em">puntos</text>
				</svg>
				
			</div>
			
			<h4 style="text-align:center; margin-left: -40px; margin-top: -10px;">Saldo actual</h4>
			
		</div>
		
		<div class="points-help" >
			
			
			<h4>Sistema de Puntuación </h4>
			<hr>
			
			<h5>¿Para qué sirven los puntos?</h5>
			<p>Sirven para recibir ayuda de otros usuarios.</p>
			<ul>
				<li>Para publicar una demanda debes tener al menos 1 punto</li>
				<li>Una demanda cuesta 1 punto por hora recibida</li>
				<li>Los puntos son restados al valorar la colaboración</li>
			</ul>
			
			<p></p>
			
			<h5>¿Cómo consigo más puntos?</h5>
			<ul>
				<li>Solo por registrarte recibes 20 puntos gratis</li>
				<li>Por cada oferta publicada se ganan 2 puntos</li>
				<li>Por cada colaboración en la que se preste ayuda se ganará 1 punto por hora dedicada, más un incremento en función de la puntuación recibida</li>
			</ul>
			
			<p></p>
			
			
			<h5>Recomendaciones</h5>
			<ul>
				<li>Recomendamos consultar tu balance de puntos periodicamente</li>
				<li>Ayuda siempre que puedas y tendrás puntos cuando lo necesites</li>
				<li>Un uso responsable del sistema permite que todos disfrutemos de un intercambio de conocimiento sin costes. Respeta las normas</li>
			</ul>
			
		</div>
		
		
	
	</div>

</jsp:body>
</t:paginaperfil>