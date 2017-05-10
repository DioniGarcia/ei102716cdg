<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- La sessió està disponible automàticament en l’objecte "session" -->
<c:set var="user" scope="request" value='${session.getAttribute("user")}'/>

<c:choose> 
	<c:when test='${user == null}'>
		<a href="${pageContext.request.contextPath}/login.html">SingIn</a> </c:when>
	<c:otherwise>
		${user.nick}
	</c:otherwise>
</c:choose>
