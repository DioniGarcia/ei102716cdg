<html>
<head>
<link href="${pageContext.request.contextPath}/css/bootstrap.css"
	    rel="stylesheet"/>
<link href="https://cdn.datatables.net/1.10.15/css/jquery.dataTables.min.css"
	    rel="stylesheet"/>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="${pageContext.request.contextPath}/css/dataTables.fontAwesome.css"
	    rel="stylesheet"/>
<link href="https://cdnjs.cloudflare.com/ajax/libs/x-editable/1.5.0/bootstrap3-editable/css/bootstrap-editable.css" rel="stylesheet"/>
<style type="text/css">
.center {
	text-align: center;
    vertical-align: middle;
}
</style>
</head>
<body>
<div class="container">

<div class="alert alert-success" role="alert" style="display:none;">Se ha guardado correctamente<a class="close" onclick="$('.alert').hide()">×</a></div>
<div class="alert alert-danger" role="alert" style="display:none;">Ha habido un error<a class="close" onclick="$('.alert').hide()">×</a></div>

<button id="show-form">Nueva habilidad</button>

<form id="form-skill" style="display:none;">
<table class="dataTable no-footer">
	<thead>
	  <tr>
         <th>Nombre</th>
         <th>Descripción</th>
         <th>Nivel</th>
         <th>Activa</th>
      </tr>
	</thead>
	<tbody>
		<tr>
			<td rowspan="3"><input type="text"/></td>
			<td><input type="text"/></td>
			<td>Iniciado</td>
			<td class="center"><input type="checkbox" checked/></td>
		</tr>
		<tr>
			<td><input type="text"/></td>
			<td>Medio</td>
			<td class="center"><input type="checkbox" checked/></td>
		</tr>
		<tr>
			<td><input type="text"/></td>
			<td>Experto</td>
			<td class="center"><input type="checkbox" checked/></td>
		</tr>
	</tbody>
</table>
<button id="add-skill" type="submit">Añadir habilidad</button>
<button id="cancelar">Cancelar</button>
</form>

<table id="example" class="display" cellspacing="0" width="100%">
   <thead>
      <tr>
         <th>Nombre</th>
         <th>Descripción</th>
         <th>Nivel</th>
         <th>Ofertas</th>
         <th>Demandas</th>
         <th>Activa</th>
      </tr>
   </thead>
</table>
</div>

</body>
</html>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery-3.2.1.min.js"></script> 
<script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://cdn.datatables.net/v/dt/dt-1.10.12/datatables.min.js"></script>
<script src="https://cdn.rawgit.com/ashl1/datatables-rowsgroup/fbd569b8768155c7a9a62568e66a64115887d7d0/dataTables.rowsGroup.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/x-editable/1.5.0/bootstrap3-editable/js/bootstrap-editable.min.js"></script>
<script src="https://gyrocode.github.io/jquery-datatables-checkboxes/1.2.4/js/dataTables.checkboxes.min.js"></script>
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
	      'createdRow': function( row, data, dataIndex ) {
	      		$(row).attr('id', 'skill-' + data.id);
	  	  },
	  	  'columnDefs': [
	  	      		{
	  	         		'targets': 1,
	  	         		"width": "30%",
	  	         		'createdCell':  function (td, cellData, rowData, row, col) {
	  	            			$(td).attr('id', 'description-' + rowData.id); 
	  	            			$(td).attr('class', "editar-descripcion");
	  	            			$( td ).wrapInner( "<a href='#' data-pk=" + rowData.id + "></a>");
	  	         		}
	  	      		},
	  	      		{
	  	         		'targets': 0,
	  	         		"width": "30%",
	  	         		'createdCell':  function (td, cellData, rowData, row, col) {
	  	            			$(td).attr('id', 'name-' + rowData.id); 
	  	            			$(td).attr('class', "editar-nombre");
	  	            			$( td ).wrapInner( "<a href='#' data-pk=" + rowData.id + "></a>");
	  	         		}
	  	      		},
	  	      		{
	  	         		'targets': 5,
	  	         		"width": "5%",
	  	         		'render': function ( data, type, full, meta ) {
	  	         			return '<input type=\"checkbox\" class="' + full.id + '" ' + (full.active ? 'checked' : '') + '>';
	  	         	    },
	  	         	 	'createdCell':  function (td, cellData, rowData, row, col) {
	            			$(td).attr('class', 'center'); 
	         			}
	  	      		}
	  	      		
	  	  ],
	      columns: [
	                { data: 'name' },
	                { data: 'description' },
	                { data: 'level' },
	                { data: 'offers' },
	                { data: 'requests' },
	                { data: 'active' }
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
	    	},
	    	"initComplete": function(settings, json) {
	    		$.fn.editable.defaults.mode = 'inline';   
	    	    $('.editar-descripcion a').editable({
	    	    	params: function(params) {
	    	            var data = {};
	    	            data['id'] = params.pk;
	    	            data['description'] = params.value;
	    	            return data;
	    	        },
				    type: 'text',
				    name: 'username',
				    url: window.location.origin + "/" + window.location.pathname.split("/")[1] + "/api/skill/description",
				    success: function(response, newValue) {
				    	$('.alert-success').slideDown(200);
						$(".alert-success").delay(4000).slideUp(200);
				    },
				    error: function(res) {
   		        	 	$('.alert-danger').slideDown(200);
						$(".alert-danger").delay(4000).slideUp(200);
   		             console.log(res);
   		         }
				});
	    	    $('.editar-nombre a').editable({
	    	    	params: function(params) {
	    	            var data = {};
	    	            data['name'] = params.value;
	    	            data['original'] = $(this).text();
	    	            return data;
	    	        },
				    type: 'text',
				    name: 'username',
				    url: window.location.origin + "/" + window.location.pathname.split("/")[1] + "/api/skill/title",
				    success: function(response, newValue) {
				    	$('.alert-success').slideDown(200);
						$(".alert-success").delay(4000).slideUp(200);
				    },
				    error: function(res) {
   		        	 	$('.alert-danger').slideDown(200);
						 $(".alert-danger").delay(4000).slideUp(200);
   		             console.log(res);
   		         }
				});
	    	    
	    	    $('input[type="checkbox"]').on('change', function() {
	    		    var skillId = this.getAttribute('class');
	    		    console.dir(skillId);
	    		      $.ajax({
	    		         url: window.location.origin + "/" + window.location.pathname.split("/")[1] + "/api/skill/active",
	    		         data: { id: skillId, active: this.checked },
	    		         success: function(res) {
	    		        	 $('.alert-success').slideDown(200);
	 						 $(".alert-success").delay(4000).slideUp(200);
	    		             console.log(res);
	    		         },
	    		         error: function(res) {
	    		        	 $('.alert-danger').slideDown(200);
	 						 $(".alert-danger").delay(4000).slideUp(200);
	    		             console.log(res);
	    		         }
	    		      });
	    	    	
	    			});
	    	}
	   });
	   
	   $('#show-form').on('click', function(){
		  $('#form-skill').show(); 
		  $('.alert-success').slideDown(200);
		  $(".alert").delay(4000).slideUp(200);
	   });
	   
	   $('#cancelar').on('click', function(e){
		      e.preventDefault();
			  $('#form-skill').hide(); 
		   });
	   
	   
	   
	   $('#addRow').on( 'click', function (e) {
		   	e.preventDefault();
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
