<%@ page language="java" contentType="text/html; charset=iso-8859-1"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:paginabasica>
<jsp:body>   

	<div class="center">
	<form action="">
		<button type="submit" name="filter" value="active">Activas</button>
		<button type="submit" name="filter" value="eval">Para evaluar</button>
		<button type="submit" name="filter" value="old">Ya realizadas</button>
	</form>
	<c:forEach varStatus="status" items="${collabs}" var="collab"> 
    	<t:collabbox 
    			collabId="${collab.collaboration_id }"
    			collabTitle="Colaboración de ${skills[status.index].name } - ${skills[status.index].description}" 
    			collabTotalHours="${collab.totalHours}">
    	</t:collabbox>
    	</c:forEach>
	</div>

</jsp:body>
</t:paginabasica>