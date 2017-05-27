<%@page contentType="text/html; charset=utf-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<style>
	.split-container {
		margin-top:  6px;
		padding-top: 6px;
	}
	
	p#date {
		text-align: right;
		font-size:15px;
		font-weight: bold;
		color: #111;
    	opacity: 0.5;
	}
	
	div.description-box {
		
		padding: 16px;
		font-size: 16px;
	
		border: 1px solid;
		border-color: #989898;
		border-radius: 4px;
		
		margin-bottom: 8px;
	}
	
</style>

<t:paginabasica>
<jsp:body>
	<div class="split-container">
		<div class="split-item-v-foto split-container">
			<img src="${ pageContext.request.contextPath }/resources/img/background.jpg" class="img-circle-thumbnail" alt="" width="120" height="120">
		</div>
		<div class="split-item-v-texto">
			<h2> <b>Oferta ofrecida por: </b><c:out value="${student.name }"></c:out> </h2>
			<div>
			Puntuación media: 
			<select class="star-readonly">
			<option value="0">0</option>
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3" selected="selected">3</option>
			<option value="4">4</option>
			<option value="5">5</option>
			</select>
			</div>
		</div>
	</div>
	<hr/>
	<div>
		<h3><b>Oferta de <c:out value="${skill.name } - ${skill.description }"></c:out></b></h3>
	</div>
		<br>
	   	<h4>Descripción:</h4>
	    <div class="description-box">
	    	<c:out value="${offer.description}"></c:out>
	    </div>
	    
	
    <div>
    	<p id=date>Fecha de inicio: <c:out value="${offer.startDate }"></c:out> - Fecha fin: <c:out value="${offer.endDate }"></c:out></p>
    </div>
    
    <a href="${ pageContext.request.contextPath }/my/collaborations/add?offerId=${offer.id }&skillId=${skill.skill_id }"><button class="btn btn-primary">Establecer collaboración</button></a>
</jsp:body>
</t:paginabasica>