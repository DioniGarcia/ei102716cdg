<%@ tag description="Estructura d'una pàgina normal"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<nav class="navbar navbar-default navbar-fixed-top">
  <div class="row valign" >
  	
  	<!-- Titulo-->
  	<div class="navbar-header col-md-2">
      <a class="navbar-brand" href="#">SkillSharing</a>
    </div>
    
    <!-- Search-->
	<div class="col-xs-3">
	    <div class="input-group">
	        <div class="input-group-btn search-panel">
	            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
	            	<span id="search_concept">Filtrar</span> <span class="caret"></span>
	            </button>
	            <ul class="dropdown-menu" role="menu">
	              <li><a href="#contains">Oferta</a></li>
	              <li><a href="#its_equal">Demanda</a></li>
	              <li class="divider"></li>
	              <li><a href="#all">Todo</a></li>
	            </ul>
	        </div>
	        <input type="hidden" name="search_param" value="all" id="search_param">         
	        <input type="text" class="form-control" name="x" placeholder="Buscar...">
	        <span class="input-group-btn">
	            <button class="btn btn-default" type="button"><span class="glyphicon glyphicon-search"></span></button>
	        </span>
    	</div>
    </div>
    
    <!-- Offers-->
  	<div class= "col-md-1">
      <a href="${pageContext.request.contextPath}/index.jsp">Ofertas</a>
    </div>
    
    <!-- Requests-->
  	<div class= "col-md-1">
      <a href="${pageContext.request.contextPath}/index.jsp">Demandas</a>
    </div>
    
    <!-- Colabortions-->
  	<div class= "col-md-1">
      <a href="${pageContext.request.contextPath}/index.jsp">Colaboraciones</a>
    </div>
    
    <!-- Correo-->
  	<div class= "col-md-2">
      	<a href="${pageContext.request.contextPath}/index.jsp">
			<span class="glyphicon glyphicon-envelope"></span>Correo 
			<span class="badge">10</span>
		</a> 
    </div>
    
    <!-- Perfil-->
  	<div class:="col-md-2">
  		<a href="${pageContext.request.contextPath}/index.jsp">
				<img src="${pageContext.request.contextPath}/resources/img/background.jpg" class="img-circle" alt="" width="40" height="40">
				Nombre del usuario 
 		</a> 
    </div>
    
  </div>
</nav>


