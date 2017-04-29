<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- La sessió està disponible automàticament en l’objecte "session" -->
<c:set var="user" scope="request" value='${session.getAttribute("user")}'/>

<c:choose> 
<c:when test='${user == null}'>
<a href="${pageContext.request.contextPath}/login.html">Entrar</a> </c:when>
<c:otherwise>
Bienvenido ${user.nick}
<a href="${pageContext.request.contextPath}/logout.html">Salir</a> </c:otherwise>
</c:choose>
