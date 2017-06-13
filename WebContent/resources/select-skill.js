$.fn.select2.amd.define('select2/i18n/es',[],function () {
	  // Spanish
	  return {
	    errorLoading: function () {
	      return 'La carga falló';
	    },
	    
	    inputTooShort: function (args) {
	      var remainingChars = args.minimum - args.input.length;

	      var message = 'Por favor, introduzca ' + remainingChars + ' car';

	      if (remainingChars == 1) {
	        message += 'ácter';
	      } else {
	        message += 'acteres';
	      }

	      return message;
	    },
	    loadingMore: function () {
	      return 'Cargando más resultados...';
	    },
	    maximumSelected: function (args) {
	      var message = 'Sólo puede seleccionar ' + args.maximum + ' elemento';

	      if (args.maximum != 1) {
	        message += 's';
	      }

	      return message;
	    },
	    noResults: function () {
	      return 'No se encontraron resultados';
	    },
	    searching: function () {
	      return 'Buscando...';
	    }
	  };
	});

$.ajax({
    url: window.location.origin + "/" + window.location.pathname.split("/")[1] + "/api/skill/names",
    dataType: 'json',
    success: function(data) {
    	$("#nombre").select2({
    		  language: "es",
    		  width: "200px",
    		  data: data,
    		  placeholder: "Habilidad"
    	});
    	if (typeof nombreSkill !== "undefined"){
    		$("#nombre").select2().val(nombreSkill).trigger("change");
    		$("#nombre").select2().trigger("select2:select");
    	}
    }
});


$('#tipo').select2({
	language: "es",
	width: "200px",
	minimumResultsForSearch: Infinity,
	placeholder: "Nivel"
});

$('#nombre').on('select2:select', function (evt) {
	axios.get(window.location.origin + "/" + window.location.pathname.split("/")[1] + "/api/skill/levels?name=" + $("#nombre").select2().val())
	.then(function (response) {
      var tipos = response.data;
	  var select = $("#tipo");
	  $("#tipo").html("");
	  $(tipos).each(function(k, o) {
	      select.append($("<option></option>").attr("value", o.id).html(o.text));
	  });
	  select.change();
	  if (typeof skillId !== "undefined")
	  	$("#tipo").select2().val(skillId).trigger("change");
	})
	.catch(function (error) {
	  console.log(error);
	});
});
$(document).ready(function(){
	$('[data-toggle="datepicker-1"]').datepicker({
		language: 'es-ES',
		offset: -50,
		autoHide: true,
		startDate: new Date()
	});	
	$('[data-toggle="datepicker-2"]').datepicker({
		language: 'es-ES',
		offset: -50,
		autoHide: true
	});	
});
