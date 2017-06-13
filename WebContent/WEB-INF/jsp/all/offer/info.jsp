<%@page contentType="text/html; charset=utf-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<style>
.no-hover div.postbox.requestbox {
	background: linear-gradient(to top,#e4f8d6, white) !important;	
	border-color: #e4f8d6 !important;
	cursor: default !important;
}

.no-hover div.postbox.requestbox:hover {
	background: linear-gradient(to top,#e4f8d6, white) !important;	
	border-color: #e4f8d6 !important;
	cursor: default !important;
}
</style>

<t:paginabasica>
<jsp:body>

<!-- Modal: No existen demandas -->
<div id="no-request" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <strong>Sin demandas compatibles</strong>
            </div>
            <div class="modal-body">
            	<p>
					Aún no has creado ninguna demanda compatible.
					Para establecer una colaboración debes disponer de
					una demanda compatible.
					Esta demanda no será visible por el resto de usuarios.
					¿Quieres que creemos una por ti?      
				</p>         
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                <a href="${ pageContext.request.contextPath }/my/collaborations/add?fromOffer=true&skillId=${offer.skill_Id }&offerId=${offer.id }&confirm" class="btn btn-primary btn-ok">Generar automáticamente</a>
            </div>
        </div>
    </div>
</div>

<!-- Modal: Existe 1 demanda compatible -->
<div id="one-request" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <strong>Tienes una demanda compatible con esta oferta</strong>
            </div>
            <div class="modal-body">
				<p>
					Tienes una demanda. Una vez establecida la colaboración está dejará
					de estar disponible para el resto de usuarios.
					Si quieres conservar la demanda, generaremos una nueva para establecer
					la colaboración.   
				</p>
				<div class="no-hover"> <!-- anyadele una class tamb para quitarle el efecto hover -->
					<t:requestbox
	    				postLink="#"
	    				postTitle="Demanda de ${skills[0].name } - ${skills[0].description}" 
	    				postDescription="${requests[0].description}" 
	    				postDate="${requests[0].startDate },${requests[0].endDate }"
	    				avatarId="${myAvatarId}"
	    				rating="${rating}">
	    			</t:requestbox>
	    		</div>   
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                <a href="${ pageContext.request.contextPath }/my/collaborations/add?fromOffer=true&skillId=${offer.skill_Id }&offerId=${offer.id }&confirm" class="btn btn-primary btn-ok">Conservar demanda</a>
            	<a href="${ pageContext.request.contextPath }/my/collaborations/add?fromOffer=true&skillId=${offer.skill_Id }&offerId=${offer.id }&requestId=${request.id}&confirm" class="btn btn-primary btn-ok">Vincular</a>
            </div>
        </div>
    </div>
</div>

<!-- Modal: Existen demandas compatibles -->
<div id="many-request" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <strong>Tienes una demanda compatible con esta oferta</strong>
            </div>
            <div class="modal-body">
            	<p>
					Tienes varias demandas, <b>selecciona</b> una de ellas.<br/>
					Una vez establecida la colaboración está dejará de estar disponible 
					para el resto de usuarios.
					Si quieres conservar tus demandas, generaremos una nueva para establecer
					la colaboración.  
				</p> 
				<div id="demandas">
				<c:forEach varStatus="status" items="${requests}" var="request">
					<div tabindex="-1"> 
			    	<t:requestbox
			    			postLink="#${request.id}"
			    			postTitle="Demanda de ${skills[status.index].name } - ${skills[status.index].description}" 
			    			postDescription="${request.description}" 
			    			postDate="${request.startDate },${request.endDate }"
			    			avatarId="${myAvatarId}"
			    			rating="${rating}">
			    	</t:requestbox>
			    	</div>
    			</c:forEach>  
    			</div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                <a href="${ pageContext.request.contextPath }/my/collaborations/add?skillId=${offer.skill_Id }&fromOffer=true&offerId=${offer.id }&confirm" class="btn btn-primary btn-ok">Conservar demandas</a>
            	<a id="many-req-link" href="#" onClick="comprobarSeleccion();" class="btn btn-primary btn-ok">Vincular</a>
            </div>
        </div>
    </div>
</div>


<!-- Vista principal -->
	<h2>Oferta de <c:out value="${skill.name } - ${skill.description }"></c:out></h2>
   	<hr>
	<div class="split-container">
		<div class="split-item-v-foto split-container">
			<img src="${ pageContext.request.contextPath }/resources/img/avatars/${student.avatar}.jpg" class="img-circle-thumbnail" alt="" width="120" height="120">
		</div>
		<div class="split-item-v-texto">
			<h3><b>Ofertante: </b><c:out value="${student.name }"></c:out></h3>
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
	<div class="post-info post-info-offer">
		<h4>Descripción:</h4>
		<div class="description">
			<c:out value="${offer.description}"></c:out>
		</div>
	    <div>
	    	<p class="post-date" style="font-size:15px; font-weight:bold;"><c:out value="${offer.startDate }, ${offer.endDate } "></c:out></p>
	    </div>

	</div>
	
    <c:choose>
		<c:when test="${empty requests}"> <!-- No existen demandas  -->
			<a class ="btn btn-primary" data-href="hola" data-toggle="modal" data-target="#no-request">Establecer colaboración</a>
		</c:when>
		<c:otherwise>
			<c:choose>
				<c:when test="${fn:length(requests) gt 1}"> <!-- Existen demandas compatibles -->
					<a class ="btn btn-primary" data-href="hola" data-toggle="modal" data-target="#many-request">Establecer colaboración</a>
				</c:when>
				<c:otherwise> <!-- Existe una demanda compatible -->
					<a class ="btn btn-primary" data-href="hola" data-toggle="modal" data-target="#one-request">Establecer colaboración</a>
				</c:otherwise>
			</c:choose>
		</c:otherwise>
	
	</c:choose>
	   
	<a href="${ pageContext.request.contextPath }/chat/new?with=${offer.student_nick }"><button class="btn btn-primary btn-chat">Chat</button></a>


	<script type="text/javascript">
	window.location.hash = "";
	var els = document.getElementById("demandas");
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
		var requestId = window.location.hash.substr(1);
		if (requestId == ""){
			alert("Selecciona una demanda, por favor.");
		} else {
			document.getElementById("many-req-link").href = "${ pageContext.request.contextPath }/my/collaborations/add?fromOffer=true&confirm=true&skillId=${offer.skill_Id }&offerId=${offer.id }&requestId=" + requestId;
			
		}
	}
	</script>


</jsp:body>
</t:paginabasica>