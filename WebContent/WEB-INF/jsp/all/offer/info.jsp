<%@page contentType="text/html; charset=utf-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

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
				Aún no has creado ninguna demanda compatible.
				Para establecer una colaboración debes disponer de
				una demanda compatible.
				Esta demanda no será visible por el resto de usuarios.
				¿Quieres que creemos una por ti?               
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                <a class="btn btn-primary btn-ok">Generar automáticamente</a>
            </div>
        </div>
    </div>
</div>

<!-- Modal: Existen demandas compatibles -->
<div id="one-request" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <strong>Tienes una demanda compatible con esta oferta</strong>
            </div>
            <div class="modal-body">
				Tienes una demanda. Una vez establecida la colaboración está dejará
				de estar disponible para el resto de usuarios.
				Si quieres conservar la demanda, generaremos una nueva para establecer
				la colaboración.       
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                <a class="btn btn-primary btn-ok">Conservar demanda</a>
            	<a class="btn btn-primary btn-ok">Vincular</a>
            </div>
        </div>
    </div>
</div>

<!-- Vista principal -->

	<div class="split-container">
		<div class="split-item-v-foto split-container">
			<img src="${ pageContext.request.contextPath }/resources/img/avatars/${student.avatar}.jpg" class="img-circle-thumbnail" alt="" width="120" height="120">
		</div>
		<div class="split-item-v-texto">
			<h3><c:out value="Nombre: ${student.name }"></c:out></h2>
			<div>
			<select class="star-readonly">
			<t:rating rating="${rating}"></t:rating>
			</select>
			</div>
		</div>
	</div>
	<hr/>
	<div>
		<h4>Oferta de <c:out value="${skill.name } - ${skill.description }"></c:out></h4>
	</div>
	<h4>Descripción:</h4>
	<div>
		<c:out value="${offer.description}"></c:out>
	</div>
    <div>
    	<p class="post-date" style="font-size:15px; font-weight:bold;"><c:out value="${offer.startDate }, ${offer.endDate } "></c:out></p>
    </div>
    
    <a href="${ pageContext.request.contextPath }/my/collaborations/add?offerId=${offer.id }&skillId=${skill.skill_id }"><button class="btn btn-primary">Establecer collaboración</button></a>
    <a href="${ pageContext.request.contextPath }/chat/new?with=${offer.student_nick }"><button class="btn btn-primary">Chat</button></a>

	<a class ="btn btn-primary" data-href="hola" data-toggle="modal" data-target="#confirm-delete">Establecer colaboración (POPUP)</a>



</jsp:body>
</t:paginabasica>