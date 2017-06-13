<%@page contentType="text/html; charset=utf-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<!-- Modal eliminar -->
<div id="eliminate" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <strong>Eliminar oferta</strong>
            </div>
            <div class="modal-body">
            	<p>
					Una vez eliminada la oferta no volverá a estar disponible.
					¿Seguro que quieres eliminarla?    
				</p>         
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default btn-w-h" data-dismiss="modal">Cancelar</button>
                <a href="${ pageContext.request.contextPath }/my/offers/eliminate/${offer.id }" class="btn btn-primary btn-ok btn-w-h btn-delete">Eliminar</a>
            </div>
        </div>
    </div>
</div>


<t:paginaperfil>
<jsp:body>
	<!-- Vista principal -->
	<h2>Oferta de <c:out value="${skill.name } - ${skill.description }"></c:out></h2>
   	<hr>
	<div class="split-container">
		<div class="split-item-v-foto split-container">
			<img src="${ pageContext.request.contextPath }/resources/img/avatars/${myAvatarId}.jpg" class="img-circle-thumbnail" alt="" width="120" height="120">
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
	
    
    <a href="${ pageContext.request.contextPath }/my/offers/update/${offer.id }"><button class="btn btn-primary btn-w-h">Editar</button></a>
    <a class ="btn btn-primary btn-w-h btn-delete" data-href="hola" data-toggle="modal" data-target="#eliminate">Eliminar</a>

	
</jsp:body>
</t:paginaperfil>