<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<html>
	<head>
	<style type="text/css">
		body {
			display: flex;
			justify-content: center;
			align-items: center;	
		}
		
		button {
			margin-left: auto;
			display: flex;
			justify-content: center;
			margin-right: 5px;
		}
		
		div {
			padding: 5px;
		}
		
		.error , input {
			display: flex;
			flex-direction: column;
		}
		
	</style>
	</head>
	<body>		
	
		<div class="login-header"></div>
		<form:form method="post" modelAttribute="user"
	        action="${pageContext.request.contextPath}/login.html">
	        
	        <div class="form-group">
			    <form:input  path="nick" placeholder="Usuario"/>
				<form:errors class="error" path="nick"/>	
	            
			</div>
			
			<div class="form-group">
			    <form:input  path="passwd" placeholder="Contraseña"/>
				<form:errors class="error" path="passwd"/>		
	            
			</div>

			<button type="submit">Acceder</button>

	    </form:form>
		<div class="login-footer"></div>
	</body>

</html>