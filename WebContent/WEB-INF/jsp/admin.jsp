<html>
<head>
<link href="${pageContext.request.contextPath}/css/bootstrap.css"
	    rel="stylesheet"/>
<link href="https://cdn.datatables.net/1.10.15/css/jquery.dataTables.min.css"
	    rel="stylesheet"/>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="${pageContext.request.contextPath}/css/dataTables.fontAwesome.css"
	    rel="stylesheet"/>
</head>
<body>
<div class="container">
<button id="addRow">Añadir habilidad</button>
<table id="example" class="display" cellspacing="0" width="100%">
   <thead>
      <tr>
         <th>Nombre</th>
         <th>Descripcion</th>
         <th>Nivel</th>
      </tr>
   </thead>
</table>
</div>

</body>
</html>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery-3.2.1.min.js"></script> 
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script src="https://cdn.datatables.net/v/dt/dt-1.10.12/datatables.min.js"></script>
<script src="https://cdn.rawgit.com/ashl1/datatables-rowsgroup/fbd569b8768155c7a9a62568e66a64115887d7d0/dataTables.rowsGroup.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	   var table = $('#example').DataTable({
	      'ajax': {
	    	  url: window.location.origin + "/" + window.location.pathname.split("/")[1] + "/api/skill/all",
	    	  dataSrc: ''
	      },
	      'rowsGroup': [0],
	      "order": [[ 0, "desc" ]],
	      "pageLength": 25,
	      columns: [
	                { data: 'name' },
	                { data: 'description' },
	                { data: 'level' }
	      ],
	      language:{
	    	    "sProcessing":     "Procesando...",
	    	    "sLengthMenu":     "Mostrar _MENU_ registros",
	    	    "sZeroRecords":    "No se encontraron resultados",
	    	    "sEmptyTable":     "Ningún dato disponible en esta tabla",
	    	    "sInfo":           "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
	    	    "sInfoEmpty":      "Mostrando registros del 0 al 0 de un total de 0 registros",
	    	    "sInfoFiltered":   "(filtrado de un total de _MAX_ registros)",
	    	    "sInfoPostFix":    "",
	    	    "sSearch":         "Buscar:",
	    	    "sUrl":            "",
	    	    "sInfoThousands":  ",",
	    	    "sLoadingRecords": "Cargando...",
	    	    "oPaginate": {
	    	        "sFirst":    "Primero",
	    	        "sLast":     "Último",
	    	        "sNext":     "Siguiente",
	    	        "sPrevious": "Anterior"
	    	    },
	    	    "oAria": {
	    	        "sSortAscending":  ": Activar para ordenar la columna de manera ascendente",
	    	        "sSortDescending": ": Activar para ordenar la columna de manera descendente"
	    	    }
	    	}
	   });
	   
	   $('#addRow').on( 'click', function () {
	        table.rows.add([
			{
			    "name":"",
			    "description":"Hola",
			    "level":"Iniciado"
			},
			{
			    "name":"",
			    "description":"Hola",
			    "level":"Medio"
			},
	        {
	            "name":"",
	            "description":"Hola",
	            "level":"Experto"
	        }
	        ]).draw( true );
	    } );
	   
	});




</script>
