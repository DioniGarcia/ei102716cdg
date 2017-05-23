<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Estadisticas</title>
<link href="${pageContext.request.contextPath}/css/bootstrap.css"
	    rel="stylesheet"/>
<style>
.center {
	text-align: center;
    vertical-align: middle;
}



</style>
</head>
<body>

<div class="container">
<div style="display:flex;justify-content: space-between;align-items: center;">
	<h3 style="margin-bottom: 30px;">Página de administrador</h3>
	<a href="${pageContext.request.contextPath}/logout.html">Salir</a>
</div>

<div class="btn-group btn-group-justified" role="group" aria-label="...">
  <div class="btn-group" role="group">
  	<a href="${pageContext.request.contextPath}/admin/index.html" ><button type="button" class="btn btn-default" id="show-form">Gestionar habilidades</button></a>
  </div>
  <div class="btn-group" role="group">
  	<a href="#"><button type="button" class="btn btn-default" id="stats">Informes/Estadísticas</button></a>
  </div>
</div>

<div id="chartGeneral" style="height: 300px; width: 100%;margin-top: 30px;">
	</div>
	
<div id="chartPosts" style="height: 300px; width: 300px;margin-top: 30px;">
	</div>

</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery-3.2.1.min.js"></script> 
<script src="https://cdnjs.cloudflare.com/ajax/libs/canvasjs/1.7.0/canvasjs.min.js"></script>
<script type="text/javascript">
window.onload = function () {
		var datos;
		var posts;

	  $.ajax({
	    type: 'GET',
	    url: window.location.origin + "/" + window.location.pathname.split("/")[1] + "/api/stats/columns",
	    async: false,
	    dataType: 'json',
	    success: function(data) {
	      	datos = data;
	    }
	  });
	  
	  $.ajax({
		    type: 'GET',
		    url: window.location.origin + "/" + window.location.pathname.split("/")[1] + "/api/stats/postStats",
		    async: false,
		    dataType: 'json',
		    success: function(data) {
		      	posts = data;
		    }
		  });

		var chart = new CanvasJS.Chart("chartGeneral",
		{
			theme: "theme3",
                        animationEnabled: true,
			title:{
				text: "Balance anual",
				fontSize: 30
			},
			toolTip: {
				shared: true
			},			
			data: datos,
          legend:{
            cursor:"pointer",
            itemclick: function(e){
              if (typeof(e.dataSeries.visible) === "undefined" || e.dataSeries.visible) {
              	e.dataSeries.visible = false;
              }
              else {
                e.dataSeries.visible = true;
              }
            	chart.render();
            }
          },
        });
		chart.render();
		
		var chart2 = new CanvasJS.Chart("chartPosts",
				{
					theme: "theme3",
		                        animationEnabled: true,
					title:{
						text: "Balance ofertas/demandas",
						fontSize: 24
					},		
					data: posts,
					legend: {
						maxWidth: 350,
						itemWidth: 120
					}
		        });
				chart2.render();
}



</script>
</html>