<%@ tag description="Estructura d'una pàgina de la sección perfil"
    pageEncoding="UTF-8"%>
<%@ attribute name="title" required="false"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>

<style>

.sidenav {
    display: block;
    height: 100%;
    width: 250px;
    position: fixed;
    z-index: 1;
    top: 0;
    left: 0;
    background-color: #111;
    overflow-x: hidden;
    padding-top: 60px;
}

.sidenav a {
    padding: 16px 32px 16px 32px;
    text-decoration: none;
    text-align:	left;
    font-size: 21px;
    color: #818181;
    display: block;
}

.sidenav a:hover, .offcanvas a:focus{
    color: #f1f1f1;
    background-color: #fff;

}

.otherside {
    display: block;
    height: 100%;
    width: 100%;
    position: relative;
    z-index: 1;
    top: 0;
   
    background: none;
    padding-top: 60px;
}

</style>

<t:paginabasica title="SkillSharing">
	<jsp:body>
		<div id="mySidenav" class="sidenav">
		  <a href="#">Perfil</a>
		  <a href="#">Mis Ofertas</a>
		  <a href="#">Mis Demandas</a>
		  <a href="#">Mis Colaboraciones</a>
		  <a href="#">Balance</a>
		</div>
		
		<div class="otherside">
				<jsp:doBody/>
		</div>
	</jsp:body>
</t:paginabasica>