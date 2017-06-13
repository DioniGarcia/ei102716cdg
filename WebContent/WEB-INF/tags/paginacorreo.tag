<%@ tag description="Estructura d'una pàgina de la sección perfil"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="title" required="false"%>
<%@ attribute name="chats" required="false" type="java.util.List"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<c:set var="user" scope="request" value='${session.getAttribute("user")}'/>
<!DOCTYPE html>

<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>${title}</title>
<!-- Fonts -->
		<link href="https://fonts.googleapis.com/css?family=Pacifico|Roboto:400,700" rel="stylesheet"> 

<!-- Bootstrap -->
	<link href="${pageContext.request.contextPath}/css/bootstrap.css"
	    rel="stylesheet"/>
	<link
	    href="${pageContext.request.contextPath}/css/bootstrap-theme.min.css"
	    rel="stylesheet"/>
	    
	<link
	    href="${pageContext.request.contextPath}/css/chat.css"
	    rel="stylesheet"/>
    
<!-- Estils propis -->

	<link 
		href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.min.css" 
		rel="stylesheet" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/my/offer.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/my/profile.css" />
	    
	<link
	    href="${pageContext.request.contextPath}/css/my/sidenav.css"
	    rel="stylesheet"/>
	   

	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/datepicker.min.css" />
  	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-bar-rating/1.2.2/themes/bootstrap-stars.css" />
	<link
	    href="${pageContext.request.contextPath}/css/personalizado.css"
	    rel="stylesheet"/>
<style>

.sidenav {
    display: block;
    height: 100%;
    width: 250px;
    position: fixed;
    z-index: 1;
    top: 0;
    left: 0;
    background-image: linear-gradient(to top,#fff 0,#f8f8f8 100%);
    background-repeat: repeat-x;
    background-color: #f8f8f8;
    box-shadow: 0px 0px 4px 0px rgba(0,0,0,0.2);    
    border-right: 1px solid;
    border-color: #e7e7e7;
    overflow-x: hidden;
    padding-top: 62px;
}

.sidenav a {
    padding: 25px 0px 16px 32px;
    text-decoration: none;
    text-align:	left;
    font-size: 21px;
    color: gray;
    display: block;
}

.sidenav a:hover, .offcanvas a:focus{
    background-color: #f1f1f1;
    color: gray !important;
}

.sidenav a:active , .active{
  	color: #fff !important;
    background-color: #939393;
    border-right: 1px solid;
  	border-color: #939393;
}

.otherside {
	min-height: calc(100% - 60px);
    display: block;
    height: calc(100% - 60px);
    width: calc(100% - 250px);
    position: absolute;
    z-index: 1;
    top: 0;
    margin-top:60px;
    margin-left: 250px;
    background: none;
    padding: 15px 40px;
}

</style>
</head>
<body>
	<t:navegacio/>
		<div id="mySidenav" class="sidenav">
			<c:forEach varStatus="status" items="${chats }" var="chat">
				<c:choose>
					<c:when test="${chat.nickUserOne eq user.nick }">
				  		<a href="${pageContext.request.contextPath}/chat/${chat.chatId}.html" <c:if test="${activeChat eq chat.chatId }"> class="active" </c:if>  >${chat.nickUserTwo}
				  			<c:if test="${chatUnread[status.index] ne 0}">
				  				<span class="badge" style="background-color: #e11;">${chatUnread[status.index]}</span>
				  			</c:if>
				  		</a>
					</c:when>
					<c:otherwise>
						<a href="${pageContext.request.contextPath}/chat/${chat.chatId}.html" <c:if test="${activeChat eq chat.chatId }"> class="active" </c:if>  >${chat.nickUserOne}
							<c:if test="${chatUnread[status.index] ne 0}">
					  				<span class="badge" style="background-color: #e11;">${chatUnread[status.index]}</span>
					  		</c:if>
						</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</div>
		
		<div class="otherside">
				<jsp:doBody/>
		</div>
	
</body>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/js/select2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/select-skill.js" charset="UTF-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/moment-with-locales.min.js" charset="UTF-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery.dotdotdot.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/datepicker.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/datepicker.es-ES.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery.barrating.min.js"></script>  

    <script type="text/javascript">
    $(document).ready(function(){
    	
    	      $('.star-readonly').barrating({
    	    	  theme: 'bootstrap-stars',
    	    	  readonly: true,
    	    	  emptyValue: 0
    	      });
    	      
    	      $('.star-rating').barrating({
    	    	  theme: 'bootstrap-stars',
    	    	  emptyValue: 0
    	      });
    	   
    	$(".br-widget").css("display","inline");
    	
    	$('#confirm-delete').on('show.bs.modal', function(e) {
    	    $(this).find('.btn-ok').attr('href', $(e.relatedTarget).data('href'));
    	});
    	
    	$('#confirmar-radio').click(function(e) {
    		   if(! $('.radio-collab').is(':checked')) { 
    			   e.preventDefault();
    			   alert("No has elegido ninguna opción!"); 
    		   }
    	});
    
    	moment.locale("es");
    	var fechas = document.getElementsByClassName("post-date");
    	for (i = 0; i < fechas.length; i++) {
    		var fecha = fechas[i].textContent.split(",");
    		var fechaIni = moment(fecha[0], "YYYY-MM-DD").format('L');
    		var fechaFin = moment(fecha[1], "YYYY-MM-DD").format('L');
    		fechas[i].textContent = fechaIni + "-" + fechaFin;	
    	}
    	
    	var fechasChat = document.getElementsByClassName("sameDateGroup");
    	for (j = 0; j < fechasChat.length; j++) {
    		var collabEndDate = moment(fechasChat[j].textContent, "DD/MM/YYYY").calendar(null,{
    		    lastDay : '[Ayer]',
    		    sameDay : '[Hoy]',
    		    lastWeek : 'dddd[, ] LL',
    		    sameElse : 'dddd[, ] LL'
    		});
    		fechasChat[j].textContent = collabEndDate;	
    	}
    	
    	var messageId = 1; //sirve para identificar los mensajes enviados por JS
    	
   		var messages = $(".messages");
   		messages.animate({ scrollTop: messages.prop("scrollHeight") - messages.height() }, 500);
    		
   		$('#send-message-form').submit(function(e) {  
   		  var formAction = $(this).attr('action');
   		  var formData = $(this).serialize();
   		  var currentMessageId = messageId++;
   		  
   		  $(".messages").append("<div class='message'><span id='msg-" + currentMessageId +"' class='my-message'>" + $("input[name='content']").val() + "&thinsp;<span style='font-size:11px;'>" +  moment().format('LT') + "</span><span id='clk-" + currentMessageId +"' style='font-size:11px;margin-left:5px;' class='glyphicon glyphicon-time'></span>"  +  "</span></div>");
   		  $('#send-message-form').trigger('reset');
   		  $.ajax({   
   		    url: formAction,   
   		 	xhrFields: { withCredentials:true },
   		 	headers: { 
   		    	'Accept': 'application/json'
   			},
   		    type: 'POST', 
   		    data: formData, // or try  $(this).serializeArray()    
   		    success: function(data) {    
   		         $("#clk-"+currentMessageId).remove();
   		         $("#msg-"+currentMessageId).append('<span style="font-size:11px;margin-left:5px">&#10004;</span>');
   		      	 messages.animate({ scrollTop: messages.prop("scrollHeight") - messages.height() }, 200);
   		    },
   		    error: function(hey){
   		    	alert("No se ha podido enviar el mensaje. Intentalo de nuevo mas tarde.")
   		    }
   		  });
   		  e.preventDefault();
   		});	
    	
    	
    	
    	
    });	
    	
    </script> 
</html>	