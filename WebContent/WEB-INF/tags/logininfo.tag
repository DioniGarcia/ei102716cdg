<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="user" scope="request" value='${session.getAttribute("user")}'/>

<img src="${pageContext.request.contextPath}/resources/img/avatars/${myAvatarId}.jpg" class="img-circle" alt="" width="40" height="40">
${user.nick}
	

