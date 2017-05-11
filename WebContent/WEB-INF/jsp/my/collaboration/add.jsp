<%@page contentType="text/html; charset=iso-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:paginabasica>
<jsp:body> 
	<div class="container">
		<c:choose>
    		<c:when test="${empty requests}">
        		No tienes ninguna demanda que cumpla con los criterios de la oferta. <br>
        		Se creará una demanda de forma automática.
        		<a href="${ pageContext.request.contextPath }/my/collaborations/add?confirm">Continuar</a>
    		</c:when>
    		<c:otherwise>
       		 <c:forEach varStatus="status" items="${requests}" var="request"> 
		    	<t:offerbox 
		    			postId="${request.id }"
		    			postDescription="${request.description}" 
		    			postDate="${request.startDate },${request.endDate }">
		    	</t:offerbox>
	    	</c:forEach>
    		</c:otherwise>
		</c:choose>
    	
    </div>

</jsp:body>
</t:paginabasica>