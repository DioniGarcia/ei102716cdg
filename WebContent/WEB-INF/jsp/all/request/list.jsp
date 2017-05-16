<%@ page language="java" contentType="text/html; charset=iso-8859-1"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:paginabasica>
<jsp:body>    
    
    <div class="center">
    	<c:forEach varStatus="status" items="${requests}" var="request"> 
    	<!--  offerbox substituir KaKa -->
    	<t:offerbox 
    			postLink="${ pageContext.request.contextPath }/all/requests/${request.id }"
    			postTitle="Demanda de ${skills[status.index].name } - ${skills[status.index].description}" 
    			postDescription="${request.description}" 
    			postDate="${request.startDate },${request.endDate }">
    	</t:offerbox>
    	</c:forEach>
    </div>
</jsp:body>
</t:paginabasica>