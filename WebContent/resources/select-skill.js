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
	      return 'Cargando más resultados…';
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
	      return 'Buscando…';
	    }
	  };
	});

$("#prueba").select2({
	  language: "es",
	  width: "200px",
	  placeholder: "Habilidad",
	  ajax: {
	    url: "http://localhost:8080/ei102716cdg/api/skill/search",
	    dataType: 'json',
	    delay: 250,
	    data: function (params) {
	      return {
	        name: params.term, // search term
	      };
	    },
	    processResults: function (data, params) {
	
	      return {
	        results: data
	      };
	    },
	    cache: true
	  },
	  minimumInputLength: 3
});

$('#tipo').select2({
	language: "es",
	width: "200px",
	minimumResultsForSearch: Infinity,
	placeholder: "Nivel"
});

$('#prueba').on('select2:select', function (evt) {
	console.dir(evt.params.data);
	axios.get('http://localhost:8080/ei102716cdg/api/skill/levels?name=' + evt.params.data.text)
	.then(function (response) {
      var tipos = response.data;
	  console.log(tipos);
	  var select = $("#tipo");
	  console.dir(select);
	  $("#tipo").html("");
	  $(tipos).each(function(k, o) {
	      select.append($("<option></option>").attr("value", o.id).html(o.text));
	  });
	  select.change();
	})
	.catch(function (error) {
	  console.log(error);
	});
});

$('#tipo').on('select2:select', function (evt) {
	console.dir(evt.params.data.id);
});

