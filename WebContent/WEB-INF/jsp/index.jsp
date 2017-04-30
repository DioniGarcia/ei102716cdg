<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:paginabasica title="SkillSharing">
	<jsp:body>
		
		<div class="split-container">
		
			<div class="split-item" >
				<div align="center"  >
					<h2 class="">Ofertas</h2>
					<c:forEach varStatus="status" items="${offers}" var="offer"> 
    				<t:offerbox 
    					postId="${offer.id }"
    					postTitle="Oferta de ${skills[status.index].name } - ${skills[status.index].description}" 
    					postDescription="${offer.description}" 
    					postDate="${offer.startDate },${offer.endDate }">
    				</t:offerbox>
    				</c:forEach>
				</div>
				
			</div>
			
			<div class="split-item" align="center">
				<h2>Demandas</h2>
				<t:offerbox/>
				<t:offerbox/>
				<t:offerbox/>
			</div>
		
		</div>
		
	</jsp:body>
</t:paginabasica>