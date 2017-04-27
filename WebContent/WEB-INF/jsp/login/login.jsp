<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:titlepage>
	<jsp:body>
	        
	   <form class="form-horizontal">
			<div class="panel-default center">
                
                <div class="panel-body ">
                    <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <div>
                            <input class="form-control form-v2" id="nick" placeholder="Nombre de usuario" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <div>
                            <input type="password" class="form-control" id="passwd" placeholder="Contraseña" required>
                        </div>
                    </div>
                    <div class="form-group">
                        
                    </div>
                    <div class="form-group last">
                        <div>
                            <button type="submit" class="btn btn-login">
                                Log in</button>
                        </div>
                    </div>
                    </form>
                </div>
                <div class="split-container center-content-h">
                	<div class="split-item-h ">
                		<a href="login/forget_passwd.jsp" class="link">¿Has olvidado la contraseña?</a>
					</div>
					
					<div class="split-item-h ">
						<a href="login/register.jsp" class="link">Registra</a>
					</div>
				</div>
            </div>
	
	</jsp:body>
</t:titlepage>