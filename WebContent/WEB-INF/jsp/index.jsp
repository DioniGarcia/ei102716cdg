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
					
					<div class="jumbotron">
						<a href="${pageContext.request.contextPath}/my/offers/add.html">
							<br>
							<br>
							<br>
							<br>
							<h2>Crear Oferta</h2> 
							<p>Ofrece ayuda a otros usuarios</p>
							<br>							
						</a> 
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
					
					<div class="jumbotron">
						<a href="${pageContext.request.contextPath}/my/request/add.html">
							<br>
							<br>
							<br>
							<br>
							<h2>Crear Demanda</h2> 
							<p>Recibe ayuda de otros miembros</p>
							<br>	
						</a> 
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