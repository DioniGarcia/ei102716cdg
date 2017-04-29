<%@ tag description="Estructura d'una pàgina normal"
    pageEncoding="UTF-8"%>
<%@ attribute name="title" required="false"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
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
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>


<body>
    <t:navegacio />
    <div class="loggeduser"><t:logininfo /></div>
    <div class="container">
        <jsp:doBody />
    </div>
    <footer>
    <hr>
    <p class="text-muted">
    Sergi Cuenca Luque, Daniel Dinu Sanchez, Dioni García García
    </p>
    </footer>
</body>
</html>
