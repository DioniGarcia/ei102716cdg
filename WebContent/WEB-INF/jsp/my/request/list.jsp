<%@ page language="java" contentType="text/html; charset=iso-8859-1"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:paginaperfil>
<jsp:body>    
    <div class="center">
    	<h2>Mis demandas</h2>
    	<hr>
    	<div style="margin-left: 202px">
    		<c:forEach varStatus="status" items="${requests}" var="request"> 
	    	<t:requestbox
	    			postLink="${ pageContext.request.contextPath }/my/requests/${request.id }"
	    			postTitle="Demanda de ${skills[status.index].name } - ${skills[status.index].description}" 
	    			postDescription="${request.description}" 
	    			postDate="${request.startDate },${request.endDate }"
	    			avatarId="${myAvatarId}"
	    			rating="${rating}">
	    	</t:requestbox>
    	</c:forEach>    	
    	</div>
    	
    	
    </div>
    
</jsp:body>
</t:paginaperfil>