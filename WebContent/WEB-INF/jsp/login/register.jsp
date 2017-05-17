<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:titlepage>
	<jsp:body>
	        
	   <form:form class="form-horizontal" method="post" modelAttribute="student" action="${pageContext.request.contextPath}/signup.html">
			<div class="panel-default center">
                
                <div class="panel-body ">
                    <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <div>
                            <form:input path="dni" class="form-control form-v2" id="dni" placeholder="DNI" required="required"/>
                            <form:errors path="dni"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div>
                            <form:input path="name" class="form-control form-v2" id="name" placeholder="Nombre" required="required"/>
                            <form:errors path="name"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div>
                            <form:input path="email" class="form-control form-v2" id="email" placeholder="Email" required="required"/>
                            <form:errors path="email"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div>
                            <form:input path="nick" class="form-control form-v2" id="nick" placeholder="Nombre de usuario" required="required"/>
                            <form:errors path="nick"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div>
                            <form:input path="passwd" type="password" class="form-control" id="passwd" placeholder="Contraseña" required="required"/>
                            <form:errors path="passwd"/>
                        </div>
                    </div>
                    <div class="form-group">
                        
                    </div>
                    <div class="form-group last">
                        <div>
                            <button type="submit" class="btn btn-login">
                                Registrate</button>
                        </div>
                    </div>
                    </form:form>
                </div>
                <div class="split-container center-content-h">
                	<div class="split-item-h ">
                		<a href="#" class="link">¿Has olvidado la contraseña?</a>
					</div>
					
					<div class="split-item-h ">
						<a href="login.html" class="link">Log in</a>
					</div>
				</div>
            </div>
	
	</jsp:body>
</t:titlepage>