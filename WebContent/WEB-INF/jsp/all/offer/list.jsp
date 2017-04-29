<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/my/offer.css" />
<title>All offers</title>
</head>
<body>
    
    <a href="add.html">Añadir oferta</a><br>
    
    <div class="offers">
    	<c:forEach varStatus="status" items="${offers}" var="offer"> 
	    	<div class="offer"><span class="titulo-oferta">Oferta de ${skills[status.index].name } - ${skills[status.index].description }</span>
	    						<span class="fecha-inicio">creado ${offer.startDate }</span><span class="nick"> creado por ${users[status.index].nick }</span>
	    						<p>${offer.description}</p>
	    	</div>
    	</c:forEach>
    	
    </div>
    
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/moment-with-locales.min.js"></script>
    <script type="text/javascript">
    	moment.locale("es");
    	var fechas = document.getElementsByClassName("fecha-inicio");
    	for (i = 0; i < fechas.length; i++) {
    		console.dir(fechas[i]);
    		fechas[i].textContent = moment(fechas[i].textContent, "YYYY-MM-DD").fromNow();
    	}
    	
    </script>
</html>
