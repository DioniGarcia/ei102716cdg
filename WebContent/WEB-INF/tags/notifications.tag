<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:choose>
	<c:when test="${notifications ne 0}">
		<span class="badge" style="background-color: #e11;">${notifications}</span>
	</c:when>
	<c:otherwise>
		<span class="badge">${notifications}</span>
	</c:otherwise>
</c:choose>
