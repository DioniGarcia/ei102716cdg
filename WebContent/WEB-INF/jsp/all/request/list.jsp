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
	
	.searcher {
		float: left;
		margin-left: 20px;
		padding-bottom: 15px;
	}
	
	.form-control {
		height: 28px !important;
		width: 340px  !important;
		margin-right: 0px;
	}
	
	.input-group-btn {
	    float: left;
	}
	
	.search-result {
		padding: 12px;
		padding-left: 20px;
		color: #A2A0A5;
	}
	
	.center {
		padding-left: 16px;
	}
	
</style>

<t:paginabasica>
<jsp:body>    
    
    <div>
		<h2 style="text-align: center">Listado de demandas</h2>
	</div>
	
	<div class="list-background center">
	   
		<div class="searcher">
	    	<form action="list.html">
	    		<div class="input-group">
	    			<input class="form-control" name="q" type="text" placeholder="Buscar: por nombre o descripción"></input>
		    		<span class="input-group-btn">
			            <button class="btn btn-default" type="button"><span class="glyphicon glyphicon-search"></span></button>
			        </span>
	    		</div>
	    	</form>
	    </div>
	    
	    <div class="pageCount">
			
			<c:set var="page" value="${(empty param.page) ? 1 : param.page}" />
			<label style="margin-bottom:20px; float: left; padding-top: 6px;">
		     Mostrar
		     <select id="selectPageCount">
		   		 <option id="size-5" value="${ pageContext.request.contextPath }/all/requests/list.html">5</option>
		    	 <option id="size-10" value="${ pageContext.request.contextPath }/all/requests/list.html?pageSize=10">10</option>
		    	 <option id="size-25" value="${ pageContext.request.contextPath }/all/requests/list.html?pageSize=25">25</option>
		    	 <option id="size-50" value="${ pageContext.request.contextPath }/all/requests/list.html?pageSize=50">50</option>
		    	 <option id="size-100" value="${ pageContext.request.contextPath }/all/requests/list.html?pageSize=100">100</option>
			 </select>
			 ofertas
			 </label>
		</div>
		
		<c:if test="${ not empty param.q }">
			<div>&nbsp; Resultados para: ${param.q} </div> <!-- Texto a mostrar cuando se busca -->
		</c:if> 
    	<c:if test="${ empty requests and empty param.q}"><h4>¡Parece que no hay demandas en este momento!</h4></c:if>
	    <div class="center">
	    	<c:forEach varStatus="status" items="${requests}" var="request"> 
	    	<t:requestbox
	    			postLink="${ pageContext.request.contextPath }/all/requests/${request.id }"
	    			postTitle="Demanda de ${skills[status.index].name } - ${skills[status.index].description}" 
	    			postDescription="${request.description}" 
	    			postDate="${request.startDate },${request.endDate }"
	    			avatarId="${students[status.index].avatar}"
	    			rating="${ratings[status.index]}">
	    	</t:requestbox>
	    	</c:forEach>
	    </div>
	</div>
	
	<div>
		<t:pagination 
	     	page="${page }" 
	     	totalPages="${pageCount}" 
	     	baseURL="${ pageContext.request.contextPath }/all/requests/list.html">
	    </t:pagination>		
	</div>
    
</jsp:body>
</t:paginabasica>