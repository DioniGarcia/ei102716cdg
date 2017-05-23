<%@ tag description="Estructura de un bloque Post"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="postLink" required="false"%>
<%@ attribute name="postTitle" required="false"%>
<%@ attribute name="postDescription" required="false"%>
<%@ attribute name="postDate" required="false"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<div class="postbox  split-container requestbox" onclick="location.href='${postLink}';">
	<div class="split-item-v-foto split-container">
		<div class="split-item"></div>
		
		<div class="split-item">
			<img src="${ pageContext.request.contextPath }/resources/img/background.jpg" class="img-circle-thumbnail" alt="" width="80" height="80">
		</div>
		
		<div class="split-item">
			<select class="star-readonly">
			<option value="0">0</option>
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3" selected="selected">3</option>
			<option value="4">4</option>
			<option value="5">5</option>
			</select>
		</div>
		
		<div class="split-item"></div>
	</div>
		
	<div class="split-item-v-texto">
		
		<div>
			<h2 class="post-title requestText">${postTitle }</h2>
		</div>
		
		<div class="acortar-texto">
			<p class="post-description cortar">
				${ postDescription}
			</p>
		</div>
		<p class="post-date">${ postDate}</p>
	</div>
</div>


