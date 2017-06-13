<%@page contentType="text/html; charset=iso-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:paginacorreo chats="${chats }">
<jsp:body> 

<div class="chat">

	<c:choose>
	<c:when test="${ chatIndex }">
		<h2>Correo personal</h2>
		<hr>
		<h4>Seleccione un canal de chat para hablar con otro usuario.</h4>
		<br>
		<h4>Si aun no tienes ningún canal activo, puedes abrir uno desde el botón "Chat" que encontraras </h4>
		<h4>en la información de las ofertas y demandas.</h4>
	</c:when>	
	<c:otherwise>
		<div class="messages">
		<c:if test="${not empty messages }">
			<c:set var="previousDate"><fmt:formatDate value="${messages[0].sendingDate}" pattern="dd/MM/yyyy" /></c:set>
			<div class="message"><span class="sameDateGroup">${previousDate }</span></div>
		</c:if>
		
			<c:forEach items="${messages }" var="message">
				<c:set var="thisDate"><fmt:formatDate value="${message.sendingDate}" pattern="dd/MM/yyyy" /></c:set>
				<c:if test="${thisDate ne previousDate }">
				<div class="message"><span class="sameDateGroup">${thisDate }</span></div>
				</c:if>
				<c:set var="previousDate"><fmt:formatDate value="${message.sendingDate}" pattern="dd/MM/yyyy" /></c:set>
				<c:choose>
					<c:when test="${message.senderNick eq nick }">
						<div class="message">
							<c:choose>
							<c:when test="${message.active}">
								<span class="my-message">${message.content}&thinsp;<span style="font-size:11px;"><fmt:formatDate value="${message.sendingDate}" pattern="HH:mm" /></span><span style="font-size:11px;margin-left:5px">&#10004;</span><span style="font-size:11px;margin-left:-5px">&#10004;</span>
								</span>
							</c:when>
							<c:otherwise>
								<span class="my-message">${message.content}&thinsp;<span style="font-size:11px;"><fmt:formatDate value="${message.sendingDate}" pattern="HH:mm" /></span><span style="font-size:11px;margin-left:5px">&#10004;</span>
								</span>
							</c:otherwise>
							</c:choose>
						</div>
					</c:when>
					<c:otherwise>
						<div class="message">
							<span class="his-message">${message.content}&thinsp;<span style="font-size:11px;"><fmt:formatDate value="${message.sendingDate}" pattern="HH:mm" /></span></span>
						</div>
					</c:otherwise>
				</c:choose>
				
			</c:forEach>
		</div>
		
		<form id="send-message-form" action="${activeChat }/send" method="POST">
		<div class="send-message-box">
				
				<input name="content" placeholder="Escribe un mensaje..." autocomplete="off" autofocus required></input>
				<button type="submit"><div class="enviar" title="Enviar"><span style="vertical-align:middle;margin-right: 7px;" class="glyphicon glyphicon-send"></span></div></button>
			
		</div>
		</form>
	</c:otherwise>
	</c:choose>
	
	
</div>

</jsp:body>
</t:paginacorreo>