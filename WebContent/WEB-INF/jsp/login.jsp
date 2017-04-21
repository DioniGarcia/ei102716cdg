<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:paginabasica title="SkillSharing - Login">
	<jsp:body>
		<h2>Accés</h2>
		
		<form:form method="post" modelAttribute="user"
	        action="${pageContext.request.contextPath}/login.html">
	        
	        <div class="form-group">
				<form:label  path="username">Nom d'usuari:</form:label>		
				<form:errors class="error" path="username"  />	
			    <form:input  class="form-control" path="username" placeholder="Usuari"/>
	            
			</div>
			
			<div class="form-group">
				<form:label  path="password">Contrasenya:</form:label>	
				<form:errors class="error" path="password"  />		
			    <form:input  class="form-control" path="password" placeholder="Contrasenya"/>
	            
			</div>

			<input class="btn btn-default" type="submit" value="Accedir"/>

	    </form:form>

	</jsp:body>
</t:paginabasica>