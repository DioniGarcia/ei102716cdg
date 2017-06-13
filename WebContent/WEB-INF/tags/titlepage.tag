<%@ tag description="Estructura d'una pàgina normal"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- Fonts -->
		<link href="https://fonts.googleapis.com/css?family=Pacifico|Roboto:400,700" rel="stylesheet"> 
	
	<!-- Bootstrap -->
		<link href="${pageContext.request.contextPath}/css/bootstrap.css"
		    rel="stylesheet">
		<link
		    href="${pageContext.request.contextPath}/css/bootstrap-theme.min.css"
		    rel="stylesheet">
	    
	<!-- Estils propis -->
		<link
		    href="${pageContext.request.contextPath}/css/personalizado.css"
		    rel="stylesheet">
	
	<!-- JS -->
	    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>    
<style>

body, html {
  height: 100%;
  margin: 0;
  font: 400 15px/1.8 "Lato", sans-serif;
  color: #777;
}

.bgimg-1, .bgimg-2, .bgimg-3 {
  position: relative;
  opacity: 1.0;
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
}

.bgimg-1 {
  background-image: url("resources/img/bg.jpg");
  height: 100%;
}

span.border {
  background-color: #111;
  color: #fff;
  opacity: 0.6;
  padding: 18px;
  text-align: center;
  font-size: 32px;
  font-family: 'Pacifico', cursive;
  letter-spacing: 7px;
}

.welcome-footer {
	display: flex;
	align-items: center;
	justify-content: center;
}

.welcome-footer-item {
	
}

</style>
</head>

<body>

<div class="bgimg-1 split-container">
	<div class="split-item-h">
	</div>
	
	
	<div class="split-item-v split-container center-content-v round-shadow" >
		<div class="split-item-v split-container">
			<div class="split-item-v"></div>
			<div class="login-box split-item-v center"><span class="border">SkillSharing!</span></div>
		</div>
		
		<div class="split-item-v split-container">
			<jsp:doBody />
		</div>
		
		<div class="split-item">
		</div>
	</div>

</div>

<div class="welcome-footer">
	<div class="welcome-footer-item">Powered by Spring MVC, created by DASERDI <b>&#9400;</b> &emsp; Universidad Jaume I, EI1027 - Diseño e Implementación de Sistemas de Información </div>
</div>

</body>
</html>