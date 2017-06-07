<%@ page language="java" contentType="text/html; charset=iso-8859-1"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:paginaperfil>
<jsp:body>    
    <div class="center">
    	<c:forEach varStatus="status" items="${offers}" var="offer"> 
    	<t:offerbox 
    			postLink="${ pageContext.request.contextPath }/my/offers/${offer.id }"
    			postTitle="Oferta de ${skills[status.index].name } - ${skills[status.index].description}" 
    			postDescription="${offer.description}" 
    			postDate="${offer.startDate },${offer.endDate }"
    			avatarId="${myAvatarId}">
    	</t:offerbox>
    	</c:forEach>
    </div>
</jsp:body>
</t:paginaperfil>