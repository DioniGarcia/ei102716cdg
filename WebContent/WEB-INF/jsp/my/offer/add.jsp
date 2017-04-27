<%@page contentType="text/html; charset=iso-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Skill Sharing - Añadir Oferta</title>
<link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.min.css" rel="stylesheet" />

</head>
<body>
    <h2>Añadir Oferta</h2>
    <form:form method="post" modelAttribute="offer">
        <table>
            <tr>
                <td><form:label path="startDate">Fecha de inicio</form:label></td>
                <td><form:input type="date" path="startDate" /></td>
            </tr>
            
            <tr>
            <!-- Linea separadora --><td><hr/></td> <td><hr/></td> <td><hr/></td>
            </tr>
            
             <tr>
                <td><form:label path="startDate">Fecha final</form:label></td>
                <td><form:input type="date" path="endDate" /></td>
            </tr>
            
            <tr>
            <!-- Linea separadora --><td><hr/></td> <td><hr/></td> <td><hr/></td>
            </tr>
            
            <tr>
                <td><form:label path="description">Descripcion de la oferta</form:label></td>
                <td colspan="3">
                	<form:input  path="description" /><br>
                	<form:errors class="error" path="description"/>	
                </td>
            </tr>
            			 

            <tr>
            	<td><hr/></td> <!-- Linea separadora -->
            	<td><hr/></td>
            </tr>
            
            
            <tr>
                <td><form:label path="skill_Id">Skill</form:label></td>
                <td>
                	<form:select path="skill_Id">
                		<c:forEach items="${skill_list}" var="skill">
	 						<form:option value="${skill.skill_id}">${skill.name} - Nivel ${skill.level}</form:option>
	 					</c:forEach>
					</form:select >
				</td>
            </tr>
            
            <tr>
            	<td><hr/></td> <!-- Linea separadora -->
            	<td><hr/></td>
            </tr>
            
            <tr>
                <td><form:label path="active">Activa</form:label></td>
                <td><form:checkbox path="active"></form:checkbox></td>
            </tr>
            
            <tr></tr>
            
            <tr>
                <td colspan="2"><input type="submit" value="Añadir" />
                </td>
            </tr>
        </table>
    </form:form>
    <select id="prueba">
  		<option value="3620194" selected="selected">select2/select2</option>
	</select>
    
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery-3.2.1.min.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/js/select2.min.js"></script>
<script type="text/javascript">
	var delay = (function(){
	  var timer = 0;
	  return function(callback, ms){
	    clearTimeout (timer);
	    timer = setTimeout(callback, ms);
	  };
	})();
	
	$('#test').keyup(function() {
	    delay(function(){
	      
	    }, 1000 );
	});
	
	var data = [{ id: 0, text: 'enhancement' }, { id: 1, text: 'bug' }, { id: 2, text: 'duplicate' }, { id: 3, text: 'invalid' }, { id: 4, text: 'wontfix' }];

	$("#prueba").select2({
		  width: "180px",
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
		  // escapeMarkup: function (markup) { return markup; }, // let our custom formatter work
		  minimumInputLength: 1,
		  //templateResult: formatRepo, // omitted for brevity, see the source of this page
		  //templateSelection: formatRepoSelection // omitted for brevity, see the source of this page
		});
	
	axios.get('http://localhost:8080/ei102716cdg/api/skill/all?id=5')
	.then(function (response) {
	  console.log(response.data);
	})
	.catch(function (error) {
	  console.log(error);
	});

</script>
</html>