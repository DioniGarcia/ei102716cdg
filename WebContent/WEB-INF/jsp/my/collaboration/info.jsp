<%@page contentType="text/html; charset=utf-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:paginaperfil>
<jsp:body>
	<div>
		<h2>Colaboración de <c:out value="${skill.name } - ${skill.description }"></c:out></h2>
	</div>
	<hr/>
	
	<div class="collab-info-students">
	<div class="collab-info-student split-container">
		<div class="split-item-v-foto split-container">
			<img src="${ pageContext.request.contextPath }/resources/img/avatars/${studentOf.avatar}.jpg" class="img-circle-thumbnail" alt="" width="120" height="120">
		</div>
		<div class="split-item-v-texto">
			<h3><c:out value="Ofertante: ${studentOf.name }"></c:out></h3>
			<div>
			<select class="star-readonly">
			<t:rating rating="${ratingOf}"></t:rating>
			</select>
			</div>
		</div>
	</div>
	
	<div class="collab-info-student split-container">
		<div class="split-item-v-foto split-container">
			<img src="${ pageContext.request.contextPath }/resources/img/avatars/${studentRq.avatar}.jpg" class="img-circle-thumbnail" alt="" width="120" height="120">
		</div>
		<div class="split-item-v-texto">
			<h3><c:out value="Demandante: ${studentRq.name }"></c:out></h3>
			<div>
			<select class="star-readonly">
			<t:rating rating="${ratingRq}"></t:rating>
			</select>
			</div>
		</div>
	</div>
	</div>

	<c:if test="${collabStatus eq 'active' }">
		<h4>Estado de la colaboración: En curso</h4>
		<p class="collabEndDate">${collabEndDate}</p>
		<div>
    		<p class="post-date" style="font-size:15px; font-weight:bold;"><c:out value="${collabStartDate }, ${collabEndDate } "></c:out></p>
    	</div>
	</c:if>
	
	
	<c:if test="${collabStatus ne 'active' and collabStatus eq 'finished' }">
		<h4>Estado de la colaboración: Finalizada</h4>
		
		<p>Puntuación :  
			<select class="star-readonly">
				<c:forEach begin="0" end="5" var="val">
    				<c:choose>
    					<c:when test="${collaboration.rating eq val }">
    						<option value="${val }" selected="selected"><c:out value="${val}"/></option>
    					</c:when>
    					<c:otherwise>
    						<option value="${val }"><c:out value="${val}"/></option>
    					</c:otherwise>
    				</c:choose>
				</c:forEach>
			</select>
		</p>
		
		<p>Horas totales:  ${collaboration.totalHours }</p>
		
		<p>Comentarios: ${collaboration.comments }</p>
		
		<div>
    		<p class="post-date" style="font-size:15px; font-weight:bold;"><c:out value="${collabStartDate }, ${collabEndDate } "></c:out></p>
    	</div>
	</c:if>
	
	
	<c:if test="${collabStatus ne 'active' and collabStatus ne 'finished' and collabStatus eq 'pending' }">
		<h4>Estado de la colaboración: Pendiente de evaluación</h4>
		<c:if test="${ evalBtn }">
			<a href="edit/${collaboration.collaboration_id }.html">
				<button class="btn btn-primary">Evaluar</button>
			</a>
		</c:if>
	</c:if>

    
    
    
</jsp:body>
</t:paginaperfil>