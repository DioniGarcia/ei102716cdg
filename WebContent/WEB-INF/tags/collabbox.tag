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
<%@ attribute name="offerAvatarId" required="false"%>
<%@ attribute name="requestAvatarId" required="false"%>
<%@ attribute name="collabEvalBtn" required="false" type="java.lang.Boolean"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<style>
.collabbox{
	border: 2px solid #ebf9e0;
	border-radius: 100px;
	margin-top:     6px;
	margin-bottom:  20px;
	padding-left:  31px;
	padding-right: 14px;
	
	background: linear-gradient(to right, #dde8f1 0%, white 18%, white 50%, white 78%, #e4f8d6 100%);
	cursor: pointer;	
}

.collabbox:HOVER{
	background: linear-gradient(to right, #7db8d5 0%, #dde8f1 18%, white 50%, #e4f8d6 78%, #95be72 100%);
	border-color: #ebf9e0;
	cursor: pointer;	
}

.colab-title, .colab-title:hover {
	color: #5E5E5E;
	text-decoration: none;
}


.offer-info {
	font-size: 10px;
	color: #9bbccd;
	padding-left: 40px;
}

.request-info {
	float:right;
	font-size: 10px;
	color: #9FC57F;
	padding-right: 45px;
}

.user-info {
	margin-top: 30px;

}

.star-line {
	float: left;
}

</style>

<div class="collabbox split-container" onclick="location.href='${collabId }.html';">
	<div class="split-item-v-foto split-container">
		<div class="split-item"></div>
		
		<div class="split-item user-info">
			<img src="${ pageContext.request.contextPath }/resources/img/avatars/${offerAvatarId}.jpg" class="img-circle-thumbnail" alt="" width="120" height="120">
			<p class="offer-info">Ofertante</p>
		</div>
		
		<div class="split-item"></div>
	</div>
		
	<div class="split-item-v-texto">
		
		<h2><a class="colab-title" href="${collabId }.html">${collabTitle }</a></h2>
		
		<p><b>Dedicación horaria: </b> ${collabTotalHours } horas</p>
		
		<div class="star-line"><p><b>Puntuación: </b></p></div>
		<div class="star-line">
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
		</div>
		
		<div></div>
		
	</div>
	
	<div class="split-item-v-foto split-container">
		<div class="split-item"></div>
		
		<div class="split-item user-info">
			<img src="${ pageContext.request.contextPath }/resources/img/avatars/${requestAvatarId}.jpg" class="img-circle-thumbnail" alt="" width="120" height="120">
			<p class="request-info">Demandante</p>
		</div>
		
		<div class="split-item"></div>
	</div>

</div>

