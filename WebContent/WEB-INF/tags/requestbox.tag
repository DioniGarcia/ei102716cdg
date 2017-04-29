<%@ tag description="Estructura d'una pàgina normal"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="postTitle" required="false"%>
<%@ attribute name="postDescription" required="false"%>
<%@ attribute name="postDate" required="false"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<div class="postbox split-container">
	<div class="split-item-v split-container">
		<div class="split-item"></div>
		
		<div class="split-item">
			<img src="${ pageContext.request.contextPath }/resources/img/background.jpg" class="img-circle-thumbnail" alt="" width="80" height="80">
		</div>
		
		<div class="split-item">
			* * * * *
		</div>
		
		<div class="split-item"></div>
	</div>
		
	<div>
		
		<h2 class="post-title">Matemáticas experto: algebra cuántico nazi</h2>
		<p class="post-description">Descripción Lopus lopulopusblabalbalablabalbalablablab
		bsalbdlsabd bsdka lbdd bsaldn lnd askjdljsldjlaskj jaslk
		sadbasjdaslkjdl qsd lslk a lopus no es lupus.</p>
		<p class="post-date">12/02/07 - 13/03/07</p>
	</div>
</div>


