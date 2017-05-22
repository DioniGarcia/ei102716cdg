<%@ page language="java" contentType="text/html; charset=iso-8859-1"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:paginabasica>
<jsp:body>    
    <c:set var="page" value="${(empty param.page) ? 1 : param.page}" />
    
    <label style="margin-bottom:15px;">
     Mostrar
     <select id="selectPageCount">
   		 <option value="#">5</option>
    	 <option value="${ pageContext.request.contextPath }/all/offers/list.html?page=${page }&pageCount=10">10</option>
    	 <option value="${ pageContext.request.contextPath }/all/offers/list.html?page=${page }&pageCount=25">25</option>
    	 <option value="${ pageContext.request.contextPath }/all/offers/list.html?page=${page }&pageCount=50">50</option>
    	 <option value="${ pageContext.request.contextPath }/all/offers/list.html?page=${page }&pageCount=100">100</option>
	 </select>
	 registros
	 </label>
    
    <div class="center">
    	<c:forEach varStatus="status" items="${offers}" var="offer"> 
    	<t:offerbox 
    			postLink="${ pageContext.request.contextPath }/all/offers/${offer.id }"
    			postTitle="Oferta de ${skills[status.index].name } - ${skills[status.index].description}" 
    			postDescription="${offer.description}" 
    			postDate="${offer.startDate },${offer.endDate }">
    	</t:offerbox>
    	</c:forEach>
    </div>
    
     <t:pagination 
     	page="${page }" 
     	totalPages="${pageCount}" 
     	baseURL="${ pageContext.request.contextPath }/all/offers/list.html">
     </t:pagination>
     
</jsp:body>
</t:paginabasica>