<%@ page language="java" contentType="text/html; charset=iso-8859-1"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<style>
	div.list-background {
		background-color: rgba(100, 100, 100, 0.05);
		border-radius: 12px;
		border: 2px solid;
		border-color: #DDD;
		width: 60%;
		margin-top: 40px;
		margin-bottom: 20px;
		padding: 24px;
		padding-bottom: 51px;
	}
	
	div.pageCount {
		color: #a0a0a0;
		font-size: 12px;
		
		float: right;
   	 	padding-right: 	30px;
   	 	padding-bottom: 12px;
  	}
  	
  	select#selectPageCount {
  		border-radius: 12px;
  		padding-left:   4px; 
  		padding-right:  4px;
  	}
  	
  	@-moz-document url-prefix() {
	    select#selectPageCount {
	    	border-radius: 12px 5px 5px 12px;
	    	padding-right: 20px;
	    }
	}
	
</style>

<t:paginabasica>
<jsp:body>
	
	<div>
		<h2 style="text-align: center">Listado de ofertas</h2>
	</div>
	
	<div class="list-background center">
	    
	    <div class="pageCount">
			
			<c:set var="page" value="${(empty param.page) ? 1 : param.page}" />
			<label style="margin-bottom:15px; float: left;">
		     Mostrar
		     <select id="selectPageCount">
		   		 <option value="#">5</option>
		    	 <option value="${ pageContext.request.contextPath }/all/offers/list.html?page=${page }&pageCount=10">10</option>
		    	 <option value="${ pageContext.request.contextPath }/all/offers/list.html?page=${page }&pageCount=25">25</option>
		    	 <option value="${ pageContext.request.contextPath }/all/offers/list.html?page=${page }&pageCount=50">50</option>
		    	 <option value="${ pageContext.request.contextPath }/all/offers/list.html?page=${page }&pageCount=100">100</option>
			 </select>
			 ofertas
			 </label>
		</div>
		
	    
	    <div class="center">
	    	<c:forEach varStatus="status" items="${offers}" var="offer"> 
	    	<t:offerbox 
	    			postLink="${ pageContext.request.contextPath }/all/offers/${offer.id }"
	    			postTitle="${skills[status.index].name } - ${skills[status.index].description}" 
	    			postDescription="${offer.description}" 
	    			postDate="${offer.startDate },${offer.endDate }">
	    	</t:offerbox>
	    	</c:forEach>
	    </div>
	</div>    
	
	<div>
		<t:pagination 
	     	page="${page }" 
	     	totalPages="${pageCount}" 
	     	baseURL="${ pageContext.request.contextPath }/all/offers/list.html">
	    </t:pagination>		
	</div>
     
</jsp:body>
</t:paginabasica>