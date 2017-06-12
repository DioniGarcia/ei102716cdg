<%@page contentType="text/html; charset=utf-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:paginaperfil>
<jsp:body>
	<h2>Oferta <c:out value="${skill.name }"></c:out></h2>
   	<hr>
	<div class="split-container">
		<div class="split-item-v-foto split-container">
			<img src="${ pageContext.request.contextPath }/resources/img/avatars/${myAvatarId}.jpg" class="img-circle-thumbnail" alt="" width="120" height="120">
		</div>
		<div class="split-item-v-texto">
			<h3><c:out value="Nombre: ${student.name }"></c:out></h3>
			<div>
			<select class="star-readonly">
			<t:rating rating="${rating}"></t:rating>
			</select>
			</div>
		</div>
	</div>
	<hr/>
	<div>
		<h3><c:out value="${skill.name } - ${skill.description }"></c:out></h3>
	</div>
	<div class="post-info post-info-offer">
		<h4>Descripción:</h4>
		<div class="description">
			<c:out value="${offer.description}"></c:out>
		</div>
	    <div>
	    	<p class="post-date" style="font-size:15px; font-weight:bold;"><c:out value="${offer.startDate }, ${offer.endDate } "></c:out></p>
	    </div>
	</div>
	
    
    <a href="${ pageContext.request.contextPath }/my/offers/update/${offer.id }"><button class="btn btn-primary btn-w-h">Editar</button></a>
</jsp:body>
</t:paginaperfil>