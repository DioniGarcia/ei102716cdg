<%@ tag description="Estructura d'una pàgina normal"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

<style>
    *{margin: 0;padding:0px}

	.logo-icon {
	  padding: 14px 14px;
	  padding-left: 30px;
	  text-align: left;
	  font-size: 24px;
	  font-family: 'Pacifico', cursive;
	  font-style: italic;
	}

	.nav_link {
		color: #666;
		font-size: 15px;
		padding-left: 20px;
	}
	
	.nav_link:hover{
		color: #000;
		text-decoration: none;
	}

    .header{
        width: 100%;
        background-color: #0d77b6 !important;
        height: 60px;
    }

    .showLeft{
        text-shadow: none !important;
        color:#fff !important;
        padding:10px;
    }

    .icons li {
        background: none repeat scroll 0 0 #00090B;
        opacity: 0.6;
        height: 6px;
        width: 6px;
        line-height: 0;
        list-style: none outside none;
        margin-right: 15px;
        margin-top: 3px;
        margin-bottom: 3px;
        vertical-align: top;
        border-radius:50%;
        pointer-events: none;
    }

    .btn-left {
        left: 0.4em;
    }

    .btn-right {
        right: 0.4em;
    }

    .btn-left, .btn-right {
        position: absolute;
        top: 0.24em;
    }

    .dropbtn {
        position: fixed;
        color: black;
        font-size: 12px;
        border: none;
        cursor: pointer;
    }

    .dropdown {
        position: absolute;
        display: inline-block;
        right: 0.4em;
    }

    .dropdown-content {
        display: none;
        position: relative;
        margin-top: 60px;
        background-color: #f9f9f9;
        min-width: 160px;
        overflow: auto;
        box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
        z-index: 1;
    }

    .dropdown-content a {
        color: gray;
        padding: 8px 32px 8px 32px;
        
        font-size: 18px;
        text-decoration: none;
        display: block;
    }

    .dropdown a:hover {background-color: #f1f1f1}

    .show {display:block;}

</style>

<script>
    function changeLanguage(language) {
        var element = document.getElementById("url");
        element.value = language;
        element.innerHTML = language;
    }

    function showDropdown() {
        document.getElementById("myDropdown").classList.toggle("show");
    }

    // Close the dropdown if the user clicks outside of it
    window.onclick = function(event) {
        if (!event.target.matches('.dropbtn')) {
            var dropdowns = document.getElementsByClassName("dropdown-content");
            var i;
            for (i = 0; i < dropdowns.length; i++) {
                var openDropdown = dropdowns[i];
                if (openDropdown.classList.contains('show')) {
                    openDropdown.classList.remove('show');
                }
            }
        }
    }
</script>


<nav class="navbar navbar-default navbar-fixed-top">
  <div class="row valign" >
  	
  	<!-- Titulo-->
  	<div class="navbar-header col-md-2">
      <a class="navbar-brand logo-icon" href="${pageContext.request.contextPath}/index.html">SkillSharing!</a>
    </div>
    
    <!-- Search-->
	<div class="col-xs-3">
	    <div class="input-group">
	        <div class="input-group-btn search-panel">
	            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
	            	<span id="search_concept">Filtrar</span> <span class="caret"></span>
	            </button>
	            <ul class="dropdown-menu" role="menu">
	              <li><a href="#offer">Oferta</a></li>
	              <li><a href="#request">Demanda</a></li>
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
  	<div class= "col-md-2">
      <a class="nav_link" href="${pageContext.request.contextPath}/all/offers/list.html">Ver todas las ofertas</a>
    </div>
    
    <!-- Requests-->
  	<div class= "col-md-2">
      <a class="nav_link" href="${pageContext.request.contextPath}/all/requests/list.html">Ver todas las demandas</a>

    </div>
    
    <!-- Correo-->
  	<div class= "col-md-2">
      	<a class="nav_link" href="${pageContext.request.contextPath}/404.jsp">
			<span class="glyphicon glyphicon-envelope"></span>Correo 
			<span class="badge">10</span>
		</a> 
    </div>
    
    <!-- Perfil-->
  	<div class="col-md-2">
				<img src="${pageContext.request.contextPath}/resources/img/background.jpg" class="img-circle" alt="" width="40" height="40">
				<t:logininfo/>
				
				<!-- Desplegable Perfil (Tres puntitos) -->
				<div class="dropdown">
                    <ul class="dropbtn icons btn-right showLeft" onclick="showDropdown()">
                        <li></li>
                        <li></li>
                        <li></li>
                    </ul>
                    <!-- Menu -->
                    <div id="myDropdown" class="dropdown-content">
                        <a href="${pageContext.request.contextPath}/my/profile/index.html">Perfil</a>
                        <a href="${pageContext.request.contextPath}/my/offers/list.html">Mis Ofertas</a>
                        <a href="${pageContext.request.contextPath}/my/requests/list.html">Mis Demandas</a>
                        <a href="${pageContext.request.contextPath}/my/collaborations/list.html">Mis Colaboraciones</a>
                        <a href="${pageContext.request.contextPath}/my/profile/points.html">Balance de puntos</a>
                        <hr/>
                        <a href="${pageContext.request.contextPath}/logout.html">Salir</a>
                    </div>
                </div>
 		
    </div>
    
  </div>
</nav>