<%@ page language="java" contentType="text/html; charset=iso-8859-1"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:paginabasica>
<jsp:body>    
    <a href="add.html">Añadir demanda</a><br>
    
    <div class="center">
    	<c:forEach varStatus="status" items="${requests}" var="request"> 
    	<t:offerbox 
    			postId="${request.id }"
    			postTitle="Oferta de ${skills[status.index].name } - ${skills[status.index].description}" 
    			postDescription="${request.description}" 
    			postDate="${request.startDate },${request.endDate }">
    	</t:offerbox>
    	</c:forEach>
    </div>
</jsp:body>
</t:paginabasica>