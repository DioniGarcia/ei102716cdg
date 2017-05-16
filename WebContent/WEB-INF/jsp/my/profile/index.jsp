<%@page contentType="text/html; charset=iso-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:paginaperfil>
<jsp:body> 
    <h2>Perfil</h2>
    <div></div>
    <div> Usuario: ${ student.nick }</div>
    <div> Nombre: ${ student.name }</div>
    <div> Email: ${ student.email }</div>
    
    
</jsp:body>
</t:paginaperfil>