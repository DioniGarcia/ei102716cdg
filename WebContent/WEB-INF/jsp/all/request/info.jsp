<%@page contentType="text/html; charset=utf-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<style>
.no-hover div.postbox.offerbox {
	background: linear-gradient(to top,#dde8f1, white) !important;	
	border-color: #dde8f1 !important;
	cursor: default !important;
}

.no-hover div.postbox.offerbox:hover {
	background: linear-gradient(to top,#dde8f1, white) !important ;	
	border-color: #dde8f1 !important;
	cursor: default !important;
}
</style>

<t:paginabasica>
<jsp:body>

	<!-- Modal: No existen ofertas -->
	<div id="no-offer" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	    <div class="modal-dialog">
	        <div class="modal-content">
	            <div class="modal-header">
	                <strong>Sin ofertas compatibles</strong>
	            </div>
	            <div class="modal-body">
	            <p>
					Aún no has creado ninguna oferta compatible.
					Para establecer una colaboración debes disponer de
					una oferta compatible.
					Esta oferta no será visible por el resto de usuarios.
					¿Quieres que creemos una por ti?               
	            </p>
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
	                <a href="${ pageContext.request.contextPath }/my/collaborations/add?fromRequest=true&skillId=${request.skill_Id }&requestId=${request.id }&confirm" class="btn btn-primary btn-ok">Generar automáticamente</a>
	            </div>
	        </div>
	    </div>
	</div>
	
	<!-- Modal: Existe 1 oferta compatible -->
	<div id="one-offer" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	    <div class="modal-dialog">
	        <div class="modal-content">
	            <div class="modal-header">
	                <strong>Tienes una oferta compatible con esta demanda</strong>
	            </div>
	            <div class="modal-body">
	            <p>
					Tienes una oferta. Una vez establecida la colaboración está dejará
					de estar disponible para el resto de usuarios.
					Si quieres conservar la oferta, generaremos una nueva para establecer
					la colaboración.   
				</p>
					<div class="no-hover"> <!-- anyadele una class tamb para quitarle el efecto hover -->
						<t:offerbox
		    				postLink="#"
		    				postTitle="Demanda de ${skills[0].name } - ${skills[0].description}" 
		    				postDescription="${offers[0].description}" 
		    				postDate="${offers[0].startDate },${offers[0].endDate }"
		    				avatarId="${myAvatarId}"
		    				rating="${rating}">
		    			</t:offerbox>
		    		</div>   
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
	                <a href="${ pageContext.request.contextPath }/my/collaborations/add?fromRequest=true&skillId=${request.skill_Id }&requestId=${request.id }&confirm" class="btn btn-primary btn-ok">Conservar demanda</a>
	            	<a href="${ pageContext.request.contextPath }/my/collaborations/add?fromRequest=true&skillId=${request.skill_Id }&offerId=${offer.id }&requestId=${request.id}&confirm" class="btn btn-primary btn-ok">Vincular</a>
	            </div>
	        </div>
	    </div>
	</div>
	
	<!-- Modal: Existen ofertas compatibles -->
	<div id="many-offer" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	    <div class="modal-dialog">
	        <div class="modal-content">
	            <div class="modal-header">
	                <strong>Tienes una demanda compatible con esta oferta</strong>
	            </div>
	            <div class="modal-body">
	            <p>
					Tienes varias ofertas, <b>selecciona</b> una de ellas.<br/>
					Una vez establecida la colaboración está dejará de estar disponible 
					para el resto de usuarios.
					Si quieres conservar tus ofertas, generaremos una nueva para establecer
					la colaboración.   
				</p>
					<div id="ofertas">
					<c:forEach varStatus="status" items="${offers}" var="offer">
						<div tabindex="-1"> 
				    	<t:requestbox
				    			postLink="#${offer.id}"
				    			postTitle="Demanda de ${skills[status.index].name } - ${skills[status.index].description}" 
				    			postDescription="${offer.description}" 
				    			postDate="${offer.startDate },${offer.endDate }"
				    			avatarId="${myAvatarId}"
				    			rating="${rating}">
				    	</t:requestbox>
				    	</div>
	    			</c:forEach>  
	    			</div>
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
	                <a href="${ pageContext.request.contextPath }/my/collaborations/add?fromRequest=true&skillId=${request.skill_Id }&requestId=${request.id }&confirm" class="btn btn-primary btn-ok">Conservar demandas</a>
	            	<a id="many-req-link" href="#" onClick="comprobarSeleccion();" class="btn btn-primary btn-ok">Vincular</a>
	            </div>
	        </div>
	    </div>
	</div>
	
	<!-- Vista principal -->
	<h2>Demanda <c:out value="${skill.name } - ${skill.description }"></c:out></h2>
	<hr>
	<div class="split-container">
		<div class="split-item-v-foto split-container">
			<img src="${ pageContext.request.contextPath }/resources/img/avatars/${student.avatar}.jpg" class="img-circle-thumbnail" alt="" width="120" height="120">
		</div>
		<div class="split-item-v-texto">
			<h3><b>Demandante: </b><c:out value="${student.name }"></c:out></h3>
			<div>
			<select class="star-readonly">
			<t:rating rating="${rating}"></t:rating>
			</select>
			</div>
		</div>
	</div>
	<hr/>
	<div>
	</div>
	
	<div class="post-info post-inf-request">
		<h4>Descripción:</h4>
		<div class="description">
			<c:out value="${request.description}"></c:out>
		</div>
	    <div>
	    	<p class="post-date" style="font-size:15px; font-weight:bold;"><c:out value="${request.startDate }, ${request.endDate } "></c:out></p>
	    </div>
	</div>
	    
    <c:choose>
		<c:when test="${empty offers}"> <!-- No existen ofertas-->
			<a class ="btn btn-primary" data-href="hola" data-toggle="modal" data-target="#no-offer">Establecer colaboración</a>
		</c:when>
		<c:otherwise>
			<c:choose>
				<c:when test="${fn:length(offers) gt 1}"> <!-- Existen ofertas compatibles -->
					<a class ="btn btn-primary" data-href="hola" data-toggle="modal" data-target="#many-offer">Establecer colaboración</a>
				</c:when>
				<c:otherwise> <!-- Existe una oferta compatible -->
					<a class ="btn btn-primary" data-href="hola" data-toggle="modal" data-target="#one-offer">Establecer colaboración</a>
				</c:otherwise>
			</c:choose>
		</c:otherwise>
	
	</c:choose>
	
    <a href="${ pageContext.request.contextPath }/chat/new?with=${request.student_nick }"><button class="btn btn-primary btn-chat">Chat</button></a>
    
    <script type="text/javascript">
	window.location.hash = "";
	var els = document.getElementById("ofertas");
	for(var i=0; i<els.length; ++i) {
	    els[i].addEventListener('focus', focus, true);
	    els[i].addEventListener('blur', blur, true);
	}
	function focus() {
	    this.classList.add('focus');
	}
	function blur() {
	    this.classList.remove('focus');
	}
	
	function comprobarSeleccion(){
		var offerId = window.location.hash.substr(1);
		if (offerId == ""){
			alert("Selecciona una oferta, por favor.");
		} else {
			document.getElementById("many-req-link").href = "${ pageContext.request.contextPath }/my/collaborations/add?fromRequest=true&confirm=true&skillId=${request.skill_Id }&requestId=${request.id }&offerId=" + offerId;
			
		}
	}
	
	
	
	
	</script>
    
    
</jsp:body>
</t:paginabasica>