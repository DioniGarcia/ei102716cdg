<%@ page language="java" contentType="text/html; charset=iso-8859-1"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:paginabasica>
<jsp:body>   

	<c:if test="${not empty param.success }">
		<c:if test="${ param.success eq 'update' }">
			<div class="alert alert-success alert-dismissable">
		  		<a href="#" class="close" data-dismiss="alert">&times;</a>
		  		La colaboración se ha guardado con éxito.
			</div>
		</c:if>
		<c:if test="${ param.success eq 'new' }">
			<div class="alert alert-success alert-dismissable">
		  		<a href="#" class="close" data-dismiss="alert">&times;</a>
		  		La colaboración se ha creado con éxito.
			</div>
		</c:if>
		
		
	</c:if>

	<div class="center">
	<form action="">
	<div class="btn-group">
		<button type="submit" name="filter" value="all" class="btn btn-default">Todas</button>
		<button type="submit" name="filter" value="active" class="btn btn-default">Activas</button>
		<button type="submit" name="filter" value="eval" class="btn btn-default">Para evaluar</button>
		<button type="submit" name="filter" value="old" class="btn btn-default">Ya realizadas</button>
	</div>
	</form>
	<c:forEach varStatus="status" items="${collabs}" var="collab"> 
    	<t:collabbox 
    			collabId="${collab.collaboration_id }"
    			collabTitle="Colaboración de ${skills[status.index].name } - ${skills[status.index].description}" 
    			collabTotalHours="${collab.totalHours}"
    			collabRating="${collab.rating }"
    			collabEndDate="${collabEndDates[status.index]}"
    			collabEvalBtn="${evalBtn }"
    					
    			
    			collabComments="${collab.comments }">
    	</t:collabbox>
    	</c:forEach>
	</div>

</jsp:body>
</t:paginabasica>