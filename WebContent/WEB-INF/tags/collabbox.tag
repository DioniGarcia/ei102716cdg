<%@ tag description="Estructura d'una pàgina normal"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ attribute name="collabId" required="false"%>
<%@ attribute name="collabTitle" required="false"%>
<%@ attribute name="collabTotalHours" required="false"%>
<%@ attribute name="collabRating" required="false"%>
<%@ attribute name="collabComments" required="false"%>
<%@ attribute name="collabEndDate" required="false"%>
<%@ attribute name="collabAvatarId" required="false"%>
<%@ attribute name="collabEvalBtn" required="false" type="java.lang.Boolean"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="collabbox split-container">
	<div class="split-item-v-foto split-container">
		<div class="split-item"></div>
		
		<div class="split-item">
			<img src="${ pageContext.request.contextPath }/resources/img/avatars/${collabAvatarId}.jpg" class="img-circle-thumbnail" alt="" width="120" height="120">
		</div>
		
		<div class="split-item"></div>
	</div>
		
	<div class="split-item-v-texto">
		
		<h2><a href="${collabId }.html">${collabTitle }</a></h2>
		
		<p>Horas : ${collabTotalHours }</p>
		<p>Puntuación :  
			<select class="star-readonly">
				<c:forEach begin="0" end="5" var="val">
    				<c:choose>
    					<c:when test="${collabRating eq val }">
    						<option value="${val }" selected="selected"><c:out value="${val}"/></option>
    					</c:when>
    					<c:otherwise>
    						<option value="${val }"><c:out value="${val}"/></option>
    					</c:otherwise>
    				</c:choose>
				</c:forEach>
			</select>
		</p>
		
		<p>Comentarios: ${collabComments }</p>
		
		<c:if test="${not empty collabEndDate }">
		<p class="collabEndDate">${collabEndDate}</p>
		</c:if>
		
		<c:if test="${collabEvalBtn }"><a href="edit/${collabId }.html"><button class="btn btn-primary">Evaluar</button></a></c:if>
		
	</div>
</div>

