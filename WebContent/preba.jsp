<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>${title}</title>

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
</head>


<body>

<div class="split-container">
  <div class="split-item-h">
    left
  </div>
  
  <div class="split-item-v ">
    <div class="split-item-h">1H</div>
    <div class="split-item-h">2H</div>
    <div class="split-item-h">3H</div>
    
  </div>
    <div > Abajo </div>
  
</div>
	
</body>
</html>

