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
		Selecciona un chat para comenzar
	</c:when>	
	<c:otherwise>
		<div class="messages">
			<c:forEach items="${messages }" var="message">
				<c:choose>
					<c:when test="${message.senderNick eq nick }">
						<div class="message">
							<c:choose>
							<c:when test="${message.active}">
								<span class="my-message">${message.content}&thinsp;<span style="font-size:11px;"><fmt:formatDate value="${message.sendingDate}" pattern="HH:mm" /></span><span style="font-size:11px;margin-left:5px">&#10004;</span><span style="font-size:11px;margin-left:-5px">&#10004;</span>
								</span>
							</c:when>
							<c:otherwise>
								<span class="my-message">${message.content}&thinsp;<span style="font-size:11px;">17:31</span><span style="font-size:11px;margin-left:5px">&#10004;</span>
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