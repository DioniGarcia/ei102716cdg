<%@ tag description="Estructura d'una pàgina normal"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>


<div class="postbox split-container">
	<div class="split-item-v-foto split-container">
		<div class="split-item"></div>
		
		<div class="split-item">
			<img src="${ pageContext.request.contextPath }/resources/img/background.jpg" class="img-circle-thumbnail" alt="" width="80" height="80">
		</div>
		
		<div class="split-item"></div>
	</div>
		
	<div class="split-item-v-texto">
		
		<h2><a href="#">Oferta: Chino Mandarín - A1 y A2</a></h2>
		
		<p>Horas : 0</p>
		<p>Puntuación :  
			<select id="example">
 				<option value="1">1</option>
 				<option value="2">2</option>
 				<option value="3">3</option>
 				<option value="4">4</option>
 				<option value="5">5</option>
			</select>
		</p>
		
	</div>
</div>

