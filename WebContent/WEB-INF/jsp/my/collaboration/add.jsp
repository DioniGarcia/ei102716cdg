<%@page contentType="text/html; charset=iso-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<t:paginabasica>
<jsp:body> 

<div class="modal fade" id="confirm-delete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <strong>Continuar sin elegir demanda</strong>
            </div>
            <div class="modal-body">
                Si no eliges una demanda, se creará automáticamente una nueva para poder establecer la colaboración.
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                <a class="btn btn-primary btn-ok">De acuerdo</a>
            </div>
        </div>
    </div>
</div>

<div class="container">
	<c:choose>
	<c:when test="${empty offers }">	
		<c:choose>
    		<c:when test="${empty requests}">
        		No tienes ninguna demanda que cumpla con los criterios de la oferta. <br>
        		Se creará una demanda de forma automática.
        		<a href="${ pageContext.request.contextPath }/my/collaborations/add?skillId=${param.skillId }&offerId=${param.offerId }&confirm">Continuar</a>
    		</c:when>
    		<c:otherwise>
	    		<c:choose>
	    			<c:when test="${fn:length(requests) gt 1}">
	    				<form action="${ pageContext.request.contextPath }/my/collaborations/add">
				       		 <c:forEach varStatus="status" items="${requests}" var="request">
				       		 	<input style="float:left;" type="radio" class="radio-collab" name="requestId" value="${request.id }"/> 
						    	<t:offerbox 
						    			postLink="#"
						    			postDescription="${request.description}" 
						    			postDate="${request.startDate },${request.endDate }">
						    	</t:offerbox>
					    	</c:forEach>
					    	<button class="btn btn-primary" id="confirmar-radio" type="submit">Elegir</button>&emsp;
					    	<a style="cursor:pointer;" data-href="hola" data-toggle="modal" data-target="#confirm-delete">Conservar mis demandas</a>
					    	<input type="hidden" name="offerId" value="${param.offerId }"/>
					    	<input type="hidden" name="confirm" value=""/>
	    				</form>
	    			</c:when>
	    			<c:otherwise>
	    				<h4>Existe una demanda compatible con la oferta seleccionada:</h4>
	    				<form action="${ pageContext.request.contextPath }/my/collaborations/add">
					    	<t:offerbox 
					    			postLink="#"
					    			postDescription="${requests[0].description}" 
					    			postDate="${requests[0].startDate },${requests[0].endDate }">
					    	</t:offerbox>
					    	<button class="btn btn-primary" type="submit">Continuar</button>&emsp;
					    	<a style="cursor:pointer;" data-href="hola" data-toggle="modal" data-target="#confirm-delete">Conservar mi demanda</a>
					    	<input type="hidden" name="offerId" value="${param.offerId }"/>
					    	<input type="hidden" name="requestId" value="${requests[0].id }"/>
					    	<input type="hidden" name="confirm" value=""/>
	    				</form>
	    			</c:otherwise>
	    		</c:choose>
    		
    		</c:otherwise>
		</c:choose>
	</c:when>
	<c:otherwise>
		<c:choose>
	    			<c:when test="${fn:length(offers) gt 1}">
	    				<form action="${ pageContext.request.contextPath }/my/collaborations/add">
				       		 <c:forEach varStatus="status" items="${offers}" var="offer">
				       		 	<input type="radio" name="offerId" value="${offer.id }"/> 
						    	<t:offerbox 
						    			postLink="#"
						    			postDescription="${offer.description}" 
						    			postDate="${offer.startDate },${offer.endDate }">
						    	</t:offerbox>
					    	</c:forEach>
					    	<button type="submit">Elegir</button>&emsp;
					    	<a href="#">Conservar mis ofertas</a>
					    	<input type="hidden" name="requestId" value="${param.requestId }"/>
					    	<input type="hidden" name="confirm" value=""/>
	    				</form>
	    			</c:when>
	    			<c:otherwise>
	    				<h4>Existe una oferta compatible con la demanda seleccionada:</h4>
	    				<form action="${ pageContext.request.contextPath }/my/collaborations/add">
					    	<t:offerbox 
					    			postLink="#"
					    			postDescription="${offers[0].description}" 
					    			postDate="${offers[0].startDate },${offers[0].endDate }">
					    	</t:offerbox>
					    	<button class="btn btn-primary" type="submit">Continuar</button>&emsp;
					    	<a href="#">Conservar mi oferta</a>
					    	<input type="hidden" name="offerId" value="${param.requestId }"/>
					    	<input type="hidden" name="requestId" value="${offers[0].id }"/>
					    	<input type="hidden" name="confirm" value=""/>
	    				</form>
	    			</c:otherwise>
	    		</c:choose>
	</c:otherwise>    
    </c:choose>
</div>

</jsp:body>
</t:paginabasica>