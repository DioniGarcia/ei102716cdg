<%@ tag description="Estructura d'una pàgina normal"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ attribute name="collabId" required="false"%>
<%@ attribute name="collabTitle" required="false"%>
<%@ attribute name="collabTotalHours" required="false"%>
<%@ attribute name="postDate" required="false"%>

<div class="postbox split-container">
	<div class="split-item-v-foto split-container">
		<div class="split-item"></div>
		
		<div class="split-item">
			<img src="${ pageContext.request.contextPath }/resources/img/background.jpg" class="img-circle-thumbnail" alt="" width="80" height="80">
		</div>
		
		<div class="split-item"></div>
	</div>
		
	<div class="split-item-v-texto">
		
		<h2><a href="edit/${collabId }.html">${collabTitle }</a></h2>
		
		<p>Horas : ${collabTotalHours }</p>
		<p>Puntuación :  
			<select class="star-readonly">
 				<option value="1">1</option>
 				<option value="2">2</option>
 				<option value="3">3</option>
 				<option value="4">4</option>
 				<option value="5">5</option>
			</select>
		</p>
		
	</div>
</div>

