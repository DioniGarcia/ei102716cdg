<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- La sessió està disponible automàticament en l’objecte "session" -->
<c:set var="user" scope="request" value='${session.getAttribute("user")}'/>
<p class="loggeduser"> 
<c:choose> 
<c:when test='${user == null}'>
<a href="${pageContext.request.contextPath}/login.html">LogIn</a> </c:when>
<c:otherwise>
Benvingut/da ${user.username}
<a href="${pageContext.request.contextPath}/logout.html">Eixir</a> </c:otherwise>
</c:choose>
</p>