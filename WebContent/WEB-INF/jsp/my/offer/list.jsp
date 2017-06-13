<%@ page language="java" contentType="text/html; charset=iso-8859-1"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:paginaperfil>
<jsp:body>    
    <div class="center">
    	<h2>Mis ofertas</h2>
    	<hr>
    	<c:if test="${ empty offers }"><h4>No tienes ofertas aún</h4></c:if>
    	<c:forEach varStatus="status" items="${offers}" var="offer"> 
    	<t:offerbox 
    			postLink="${ pageContext.request.contextPath }/my/offers/${offer.id }"
    			postTitle="Oferta de ${skills[status.index].name } - ${skills[status.index].description}" 
    			postDescription="${offer.description}" 
    			postDate="${offer.startDate },${offer.endDate }"
    			avatarId="${myAvatarId}"
    			rating="${rating}">
    	</t:offerbox>
    	</c:forEach>
    </div>
</jsp:body>
</t:paginaperfil>