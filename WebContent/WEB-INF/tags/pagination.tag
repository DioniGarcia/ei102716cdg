<%@ tag description="Paginacion"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="page" required="false"%>
<%@ attribute name="totalPages" required="false"%>
<%@ attribute name="baseURL" required="false"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<nav aria-label="Page navigation" class="text-center">
  <ul class="pagination pagination-sm custom-pagination">
    <li>
      <c:choose>
		<c:when test="${page eq 1 }">
			<a href="#" aria-label="Previous">
		        <span aria-hidden="true">&laquo;</span>
		      </a>
		</c:when>
		<c:otherwise>
			 <a href="${baseURL}?page=${page-1}" aria-label="Previous">
		        <span aria-hidden="true">&laquo;</span>
		      </a>
		</c:otherwise>
	  </c:choose>
    	
      
    </li>
    
    <c:forEach begin="1" end="${totalPages }" var="val">
		<c:choose>
			<c:when test="${page eq val }">
				<li class="active"><a href="#"><c:out value="${val}"/><span class="sr-only">(current)</span></a></li>
			</c:when>
			<c:otherwise>
				 <li><a href="${baseURL}?page=${val}"><c:out value="${val}"/></a></li>
			</c:otherwise>
		</c:choose>
	</c:forEach>
    
    <li>
    
    <c:choose>
		<c:when test="${page eq totalPages }">
			<a href="#" aria-label="Next">
       			 <span aria-hidden="true">&raquo;</span>
     		 </a>
		</c:when>
		<c:otherwise>
			 <a href="${baseURL}?page=${page+1}" aria-label="Next">
       			 <span aria-hidden="true">&raquo;</span>
     		 </a>
		</c:otherwise>
	</c:choose>
    
      
    </li>
  </ul>
</nav>