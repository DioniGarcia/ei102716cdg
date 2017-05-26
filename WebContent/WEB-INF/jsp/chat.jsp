<%@page contentType="text/html; charset=iso-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:paginacorreo chats="${chats }">
<jsp:body> 

<div class="chat">

	<c:choose>
	<c:when test="${ chatIndex }">
		Selecciona un chat para comenzar
	</c:when>	
	<c:otherwise>
		<div class="messages">
			<c:forEach items="${messages }" var="message">
				<c:choose>
					<c:when test="${message.senderNick eq nick }">
						<div class="message">Tú: ${message.content}<c:if test="${message.active}">(Leido)</c:if></div>
					</c:when>
					<c:otherwise>
						<div class="message">${message.senderNick }: ${message.content}</div>
					</c:otherwise>
				</c:choose>
				
			</c:forEach>
		</div>
		
		
		<div class="send-message-box">
			<form action="" method="POST">
				<textarea name="content" rows="2" cols="120"></textarea>
				<button type="submit">Enviar</button>
			</form>
		</div>
	</c:otherwise>
	</c:choose>
	
	
</div>

</jsp:body>
</t:paginacorreo>