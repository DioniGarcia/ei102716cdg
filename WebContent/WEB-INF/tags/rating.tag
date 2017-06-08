<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="rating" required="false"%>
<c:forEach begin="0" end="5" varStatus="loop">
	<c:choose>
		<c:when test="${loop.index eq rating}">
			<option value="${loop.index}" selected="selected">${loop.index}</option>
		</c:when>
		<c:otherwise>
			<option value="${loop.index}">${loop.index}</option>
		</c:otherwise>
	</c:choose>
</c:forEach>