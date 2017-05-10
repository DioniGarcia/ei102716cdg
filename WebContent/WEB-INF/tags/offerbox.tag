<%@ tag description="Estructura d'una pàgina normal"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="postId" required="false"%>
<%@ attribute name="postTitle" required="false"%>
<%@ attribute name="postDescription" required="false"%>
<%@ attribute name="postDate" required="false"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<div class="postbox split-container">
	<div class="split-item-v-foto split-container">
		<div class="split-item"></div>
		
		<div class="split-item">
			<img src="${ pageContext.request.contextPath }/resources/img/background.jpg" class="img-circle-thumbnail" alt="" width="80" height="80">
		</div>
		
		<div class="split-item">
			* * * * *
		</div>
		
		<div class="split-item"></div>
	</div>
		
	<div class="split-item-v-texto">
		
		<h2 class="post-title"><a href="${ pageContext.request.contextPath }/all/offers/${postId}">${postTitle }</a></h2>
		<div class="acortar-texto">
			<p class="post-description">
				${ postDescription}
				<a style="display:none;" class="readmore" href="${ pageContext.request.contextPath }/all/offers/${postId}">+ info</a>
			</p>
		</div>
		<p class="post-date">${ postDate}</p>
	</div>
</div>

