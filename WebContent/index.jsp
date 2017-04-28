<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:paginabasica title="SkillSharing">
	<jsp:body>
		
		<div class="split-container">
		
			<div class="split-item" >
				<div align="center"  >
					<h2 class="">Ofertas</h2>
					<t:offerbox/>
					<t:offerbox/>
					<t:offerbox/>
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