<%@ tag description="Estructura d'una pàgina normal"
    pageEncoding="UTF-8"%>
<%@ attribute name="title" required="false"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>

<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>${title}</title>
<!-- Fonts -->
		<link href="https://fonts.googleapis.com/css?family=Pacifico|Roboto:400,700" rel="stylesheet"> 

<!-- Bootstrap -->
	<link href="${pageContext.request.contextPath}/css/bootstrap.css"
	    rel="stylesheet"/>
	<link
	    href="${pageContext.request.contextPath}/css/bootstrap-theme.min.css"
	    rel="stylesheet"/>
    
<!-- Estils propis -->

	<link href="https://fonts.googleapis.com/css?family=Pacifico|Roboto:400,700" rel="stylesheet"> 

	<link 
		href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.min.css" 
		rel="stylesheet" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/my/offer.css" />
	    
	<link
	    href="${pageContext.request.contextPath}/css/my/sidenav.css"
	    rel="stylesheet"/>
	   

	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/datepicker.min.css" />
  	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-bar-rating/1.2.2/themes/bootstrap-stars.css" />
	<link
	    href="${pageContext.request.contextPath}/css/personalizado.css"
	    rel="stylesheet"/>
		
<style>
	.image-bg {
		position: relative;
		opacity: 1.0;
		background-position: center;
		background-repeat: no-repeat;
		background-size: cover;
		  
	 	background-position: center;
		  
		border-radius: 10px;
	}
</style>
	
</head>


<body class="image-bg">
    <t:navegacio/>
    <div class="container ">
        <jsp:doBody />
    </div>
    <footer>
    <hr>
    <p class="text-muted">
    Sergi Cuenca Luque, Daniel Dinu Sanchez, Dioni García García
    </p>
    </footer>
</body>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/js/select2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/select-skill.js" charset="UTF-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/moment-with-locales.min.js" charset="UTF-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery.dotdotdot.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/datepicker.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/datepicker.es-ES.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery.barrating.min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery.twbsPagination.min.js"></script>  

    <script type="text/javascript">
    $(document).ready(function(){
    	
    	      $('.star-readonly').barrating({
    	    	  theme: 'bootstrap-stars',
    	    	  readonly: true,
    	    	  emptyValue: 0
    	      });
    	      
    	      $('.star-rating').barrating({
    	    	  theme: 'bootstrap-stars',
    	    	  emptyValue: 0
    	      });
    	   
   	      $('#pagination').twbsPagination({
   	          totalPages: 2,
   	          visiblePages: 5,
   	          onPageClick: function (event, page) {
   	        	  console.dir(event);
   	              window.location.href = window.location.origin + "/" + window.location.pathname.split("/")[1] + "/all/offers/list?page="+page;
   	          }
   	      });
    	      
    	$(".br-widget").css("display","inline");
    	
    	$('#confirm-delete').on('show.bs.modal', function(e) {
    	    $(this).find('.btn-ok').attr('href', $(e.relatedTarget).data('href'));
    	});
    	
    	$('#confirmar-radio').click(function(e) {
    		   if(! $('.radio-collab').is(':checked')) { 
    			   e.preventDefault();
    			   alert("No has elegido ninguna opción!"); 
    		   }
    	});
    
    	moment.locale("es");
    	var fechas = document.getElementsByClassName("post-date");
    	for (i = 0; i < fechas.length; i++) {
    		var fecha = fechas[i].textContent.split(",");
    		var fechaIni = moment(fecha[0], "YYYY-MM-DD").format('L');
    		var fechaFin = moment(fecha[1], "YYYY-MM-DD").format('L');
    		fechas[i].textContent = fechaIni + "-" + fechaFin;	
    	}
    	
    	var collabFechas = document.getElementsByClassName("collabEndDate");
    	for (j = 0; j < collabFechas.length; j++) {
    		var collabEndDate = moment(collabFechas[j].textContent, "YYYY-MM-DD").add(1,'days').fromNow();
    		collabFechas[j].textContent = "Termina " + collabEndDate;	
    	}
    	
    	$(document).ready(function() {
    		$(".acortar-texto").dotdotdot({
    			height: 60,
    			watch: window,
    			after: "a.readmore",
    			callback	: function( isTruncated, orgContent ) {
    				if ( isTruncated ) { $(this).find("a")
    												.css("display","inline"); 
    				}
    			},
    		});
    	});
    	
    });	
    	
    </script> 
</html>
