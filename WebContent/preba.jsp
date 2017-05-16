<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:paginaperfil>
	<jsp:body>
		
		<div class="split-container">
		
			<div class="split-item" >
				<div align="center"  >
					
					<!-- Botón Crear oferta -->
					<div> 
						<a class="btn-index" href="my/offer/add" role="button">Crear Oferta</a> </button>
					</div>		
					
					<!-- Listado de ofertas destacadas -->
					<c:forEach varStatus="status" items="${offers}" var="offer"> 
    				<t:offerbox 
    					postLink="${offer.id }"
    					postTitle="Oferta de ${skills[status.index].name } - ${skills[status.index].description}" 
    					postDescription="${offer.description}" 
    					postDate="${offer.startDate },${offer.endDate }">
    				</t:offerbox>
    				</c:forEach>
				</div>
				
			</div>
			
			<div class="split-item" align="center">
				<h2>Demandas</h2>
				<t:offerbox/>
				<t:offerbox/>
				<t:offerbox/>
			</div>
		
		</div>

	</jsp:body>
</t:paginaperfil>