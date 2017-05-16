<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:paginabasica title="SkillSharing">
	<jsp:body>
		
		<div class="split-container">
		
			<div class="split-item" >
				<div align="center" >
					
					<!-- Botón Crear oferta -->
					
					<div class="jumbotron banner offer-banner">
					  <div>
					    <h2 class="banner-text" style="color: #375d70;">Ayuda a otros usuarios</h2>
					    <p><a class="btn btn-primary btn-lg banner-btn-offer" href="${pageContext.request.contextPath}/my/offers/add.html" >
					    	Crear Oferta
				    	</a></p>
					  </div>
					</div>
					
					<!-- Listado de ofertas destacadas -->
					<c:forEach varStatus="status" items="${offers}" var="offer"> 
    				<t:offerbox 
    					postId="${offer.id }"
    					postTitle="Oferta de ${skillsOf[status.index].name } - ${skillsOf[status.index].description}" 
    					postDescription="${offer.description}" 
    					postDate="${offer.startDate },${offer.endDate }">
    				</t:offerbox>
    				</c:forEach>
				</div>
				
			</div>
			
			
			
			<div style="margin-left: 15px; margin-right: 15px;" class="split-item" >
				<div align="center" >
					
					<!-- Botón Crear demandas -->
					
					<div class="jumbotron banner request-banner">
					  <div>
					    <h2 class="banner-text" style="color: #445736;">¿Necesitas ayuda?</h2>
					    <p><a class="btn btn-primary btn-lg banner-btn-request" href="${pageContext.request.contextPath}/my/request/add.html" >
					    	Crear Demanda
				    	</a></p>
					  </div>
					</div>
					
					<!-- Listado de demandas destacadas -->
					<c:forEach varStatus="status" items="${requests}" var="request"> 
    				<t:offerbox 
    					postId="${request.id }"
    					postTitle="Demanda de ${skillsRq[status.index].name } - ${skillsRq[status.index].description}" 
    					postDescription="${request.description}" 
    					postDate="${request.startDate },${request.endDate }">
    				</t:offerbox>
    				</c:forEach>
				</div>
				
			</div>
			

		
		</div>
		
	</jsp:body>
</t:paginabasica>