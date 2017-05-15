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
.alert-fixed {
    position:fixed; 
    bottom: 0px; 
    left: 0px; 
    width: 100%;
    z-index:9999; 
    border-radius:0px
}
</style>
</head>
<body>
<div class="container">
<div style="display:flex;justify-content: space-between;align-items: center;">
	<h3>Página de administrador</h3>
	<a href="${pageContext.request.contextPath}/logout.html">Salir</a>
</div>
<div id="alert-container" class="alert-fixed"></div>
<div class="alert alert-success alert-fixed" role="alert" style="display:none;">Se ha guardado correctamente<a class="close" onclick="$('.alert').hide()">×</a></div>
<div class="alert alert-danger alert-fixed" role="alert" style="display:none;">Ha habido un error<a class="close" onclick="$('.alert').hide()">×—</a></div>

<div class="btn-group btn-group-justified" role="group" aria-label="...">
  <div class="btn-group" role="group">
  	<button type="button" class="btn btn-default" id="show-form">Nueva habilidad</button>
  </div>
  <div class="btn-group" role="group">
  	<button type="button" class="btn btn-default" id="#">Informes/Estadísticas</button>
  </div>
</div>

<br><br>

<form id="form-skill" style="display:none;">
<table class="dataTable no-footer">
	<thead>
	  <tr>
         <th>Nombre</th>
         <th>Activa</th>
         <th>Nivel</th>
         <th>Descripción</th>
      </tr>
	</thead>
	<tbody>
		<tr>
			<td rowspan="3"><input name="name" type="text"/></td>
			<td class="center"><input name="active-1" type="checkbox"/></td>
			<td>Iniciado</td>
			<td><input name="description-1" type="text" disabled/></td>
		</tr>
		<tr>
			<td class="center"><input name="active-2" type="checkbox"/></td>
			<td>Medio</td>
			<td><input name="description-2" type="text" disabled/></td>
		</tr>
		<tr>
			<td class="center"><input name="active-3" type="checkbox"/></td>
			<td>Experto</td>
			<td><input name="description-3" type="text" disabled/></td>
		</tr>
	</tbody>
</table>
<button id="add-skill" type="submit">Validar</button>
</form>

<table id="skill-table" class="display" cellspacing="0" width="100%">
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
<br><br>

</body>
</html>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery-3.2.1.min.js"></script> 
<script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://cdn.datatables.net/v/dt/dt-1.10.12/datatables.min.js"></script>
<script src="https://cdn.rawgit.com/ashl1/datatables-rowsgroup/fbd569b8768155c7a9a62568e66a64115887d7d0/dataTables.rowsGroup.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/x-editable/1.5.0/bootstrap3-editable/js/bootstrap-editable.min.js"></script>
<script src="https://gyrocode.github.io/jquery-datatables-checkboxes/1.2.4/js/dataTables.checkboxes.min.js"></script>

<script type="text/javascript">
function showalert(message,alerttype) {

    $('#alert-container').append('<div id="alertdiv" class="alert ' +  alerttype + '"><a class="close" data-dismiss="alert">×</a><span>'+message+'</span></div>')

    setTimeout(function() { 


      $("#alertdiv").remove();

    }, 4000);
  }
  
$.fn.editable.defaults.mode = 'inline';   
var myCallback = function () { 
    $('.editar-descripcion a').editable({
    	params: function(params) {
            var data = {};
            data['id'] = params.pk;
            data['description'] = params.value;
            return data;
        },
	    type: 'text',
	    emptytext: 'Vacío', 
	    name: 'username',
	    url: window.location.origin + "/" + window.location.pathname.split("/")[1] + "/api/skill/description",
	    success: function(response, newValue) {
	    	showalert("<strong>Descripción</strong> actualizada correctamente" ,"alert-success");
	    },
	    error: function(res) {
	    	showalert("Ha habido un problema al actualizar la <strong>descripción</strong>","alert-danger");
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
	    emptytext: 'Vacío', 
	    name: 'username',
	    url: window.location.origin + "/" + window.location.pathname.split("/")[1] + "/api/skill/title",
	    success: function(response, newValue) {
	    	showalert("<strong>Nombre de la habilidad</strong> actualizado correctamente","alert-success");
	    },
	    error: function(res) {
	    	showalert("Ha habido un problema al actualizar el <strong>nombre de la habilidad</strong>","alert-danger");
            console.log(res);
        }
	});
    
    $('input[type="checkbox"]').on('change', function() {
	    var skillId = this.getAttribute('class');
	    if (skillId == null){
	    	if (!$(this).is(':checked'))
	    		$('input[name="description-' + $(this).attr("name").slice(-1) + '"]').prop('disabled', true);
    		else
	    		$('input[name="description-' + $(this).attr("name").slice(-1) + '"]').prop('disabled', false);
	    	return;
	    }
	     $.ajax({
	         url: window.location.origin + "/" + window.location.pathname.split("/")[1] + "/api/skill/active",
	         data: { id: skillId, active: this.checked },
	         success: function(res) {
	        	 showalert("El <strong>Estado de la habilidad</strong> se ha actualizado correctamente","alert-success");
	         },
	         error: function(res) {
	        	 showalert("Ha habido un problema al actualizar el <strong>estado de la habilidad</strong>","alert-danger");
	             console.log(res);
	         }
	      });
    	
		});
	
};
$(document).ready(function(){
	   var table = $('#skill-table').DataTable({
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
	                { data: 'description', "orderable" : false  },
	                { data: 'level' , "orderable" : false },
	                { data: 'offers' , "orderable" : false },
	                { data: 'requests' , "orderable" : false },
	                { data: 'active' , "orderable" : false }
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
	    	"initComplete": myCallback,
	    	"drawCallback": myCallback
	   });
	   
	   $('#show-form').on('click', function(){
		   $('#form-skill').toggle(); 
	   });
	   
	   $("#form-skill").submit(function(event) {

		      /* stop form from submitting normally */
		      event.preventDefault();
		      if ($(this).find('input[name="name"]').val() == ''){
		    	  showalert("Error al crear habilidad: el campo del <strong>nombre de la habilidad</strong> esta vacío", "alert-danger");
		      	  return;
		      }
			  var url =  window.location.origin + "/" + window.location.pathname.split("/")[1] + "/api/skill/add";
		      /* Send the data using post with element id name and name2*/
		      $.post( url, $("#form-skill").serialize() )
		      .done(function( data ) {
		    	  showalert("Se ha añadido correctamente la habilidad", "alert-success");
		    	  table.ajax.reload(myCallback);
		    	  table.order( [[ 0, 'desc' ]] ).draw( false );
		    	  $( this ).trigger('reset');
		      });
		    });
	   
	   
	});

</script>
