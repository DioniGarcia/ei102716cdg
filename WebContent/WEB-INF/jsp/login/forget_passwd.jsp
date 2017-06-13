<%@ page language="java" contentType="text/html; charset=iso-8859-1"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:titlepage>
	<jsp:body>
	        
			<div class="panel-default center">
                
                <div class="panel-body ">
               		<c:if test="${success }">
               			<p class="title-error">Se he enviado tu nueva contraseña al correo ${email }</p>
               		</c:if>
               		
               		<c:if test="${error }">
               			<p class="title-error">El email ${email } no existe</p>
               		</c:if>
               		
               		<c:if test="${ not success and not error }">	
               			<form action="" method="POST" class="form-horizontal">
		                    <div class="form-group">
		                        <div>
		                            <input name="email" class="form-control form-v2" id="email" placeholder="Email" required>
		                        </div>
		                    </div>
		                    <div class="form-group">
		                        
		                    </div>
		                    <div class="form-group last">
		                        <div>
		                            <button type="submit" class="btn btn-login">
		                                Recuperar contraseña</button>
		                        </div>
		                    </div>
	                    </form>
	                </c:if>
                		
                    
                </div>
                <div class="split-container center-content-h">
                	<div class="split-item-h ">
                		<a href="login.html" class="link">Log In</a>
					</div>
					
					<div class="split-item-h ">
						<a href="signup.html" class="link">Registra</a>
					</div>
				</div>
            </div>
	
	</jsp:body>
</t:titlepage>