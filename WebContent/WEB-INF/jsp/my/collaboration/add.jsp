<%@page contentType="text/html; charset=iso-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<t:paginabasica>
<jsp:body> 
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
				       		 	<input type="radio" name="requestId" value="${request.id }"/> 
						    	<t:offerbox 
						    			postId="${request.id }"
						    			postDescription="${request.description}" 
						    			postDate="${request.startDate },${request.endDate }">
						    	</t:offerbox>
					    	</c:forEach>
					    	<button type="submit">Elegir</button>&emsp;
					    	<a href="#">Conservar mis demandas</a>
					    	<input type="hidden" name="offerId" value="${param.offerId }"/>
					    	<input type="hidden" name="confirm" value=""/>
	    				</form>
	    			</c:when>
	    			<c:otherwise>
	    				<h4>Existe una demanda compatible con la oferta seleccionada:</h4>
	    				<form action="${ pageContext.request.contextPath }/my/collaborations/add">
					    	<t:offerbox 
					    			postId="${requests[0].id }"
					    			postDescription="${requests[0].description}" 
					    			postDate="${requests[0].startDate },${requests[0].endDate }">
					    	</t:offerbox>
					    	<button class="btn btn-primary" type="submit">Continuar</button>&emsp;
					    	<a href="#">Conservar mi demanda</a>
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
						    			postId="${offer.id }"
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
					    			postId="${offers[0].id }"
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