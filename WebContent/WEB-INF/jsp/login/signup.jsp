<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Sign Up</title>
</head>
<body>
	<form:form method="post" modelAttribute="student" action="${pageContext.request.contextPath}/signup.html">
		<div class="">
        	<div>
        		<form:input class="" id="dni" path="dni" placeholder="DNI" required="required"/>
        		<form:errors path="dni"/>
        	</div>
        </div>
		<div class="">
        	<div>
        		<form:input class="" id="name" path="name" placeholder="Nombre"/>
        		<form:errors path="name"/>
        	</div>
        </div>
		<div class="">
        	<div>
        		<form:input class="" id="email" path="email" placeholder="Email" required="required"/>
        		<form:errors path="email"/>
        	</div>
        </div>
		<div class="">
        	<div>
        		<form:input class="" id="nick" path="nick" placeholder="Nombre de usuario" required="required"/>
        		<form:errors path="nick"/>
        	</div>
        </div>
		<div class="">
        	<div>
        		<form:input class="" id="passwd" path="passwd" placeholder="Contraseña" required="required"/>
        		<form:errors path="passwd"/>
        	</div>
        </div>
        <div class="">
        	<div>
        		<input class="" id="confirm_passwd" placeholder="Confirmar contraseña" required="required"/>
        	</div>
        </div>
        <button type="submit">Registrarse</button>
	
	</form:form>	
</body>
<script type="text/javascript">
var password = document.getElementById("passwd")
, confirm_password = document.getElementById("confirm_passwd");

function validatePassword(){
if(password.value != confirm_password.value) {
  confirm_password.setCustomValidity("Las contraseñas no coinciden");
} else {
  confirm_password.setCustomValidity('');
}
}

password.onchange = validatePassword;
confirm_password.onkeyup = validatePassword;
</script>
</html>