<%@ page contentType="text/html; charset=iso-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:titlepage>
	<jsp:body>
	        
	   <div class="form-horizontal">
			<div class="panel-default center">
                
                <div class="panel-body">
                    <form:form method="post" modelAttribute="user" action="${pageContext.request.contextPath}/login.html" 
                    class="form-horizontal" role="form">
                    <div class="form-group">
                        <div>
                            <form:input class="form-control form-v2" id="nick" path="nick" placeholder="Nombre de usuario" required="required"/>
                            <form:errors class="title-error" path="nick"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div>
                            <form:input type="password" class="form-control" id="passwd" path="passwd" placeholder="Contrase�a" required="required"/>
                            <form:errors class="title-error" path="passwd"/>
                        </div>
                    </div>
                    <div class="form-group">
                        
                    </div>
                    <div class="form-group last">
                        <div>
                            <button type="submit" class="btn btn-login">Log in</button>
                        </div>
                    </div>
                    </form:form>
                </div>
                <div class="split-container center-content-h">
                	<div class="split-item-h ">
                		<a href="forgetPass.html" class="link">�Has olvidado la contrase�a?</a>
					</div>
					
					<div class="split-item-h ">
						<a href="signup.html" class="link">Registra</a>
					</div>
				</div>
            </div>
		</div>
	</jsp:body>
</t:titlepage>