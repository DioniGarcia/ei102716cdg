<%@ tag description="Estructura de un bloque Post"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="postLink" required="false"%>
<%@ attribute name="postTitle" required="false"%>
<%@ attribute name="postDescription" required="false"%>
<%@ attribute name="postDate" required="false"%>
<%@ attribute name="avatarId" required="false"%>
<%@ attribute name="rating" required="false"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<style>

.offer-info {
	float: right;
	font-size: 10px;
	color: #9bbccd;
}

</style>

<div tabindex="-1" class="postbox  split-container offerbox center" onclick="location.href='${postLink}';">
	<div class="split-item-v-foto split-container">
		<div class="split-item"></div>
		
		<div class="split-item">
			<img src="${ pageContext.request.contextPath }/resources/img/avatars/${avatarId}.jpg" class="img-circle-thumbnail" alt="" width="80" height="80">
		</div>
		
		<div class="split-item">
			<select class="star-readonly">
				<t:rating rating="${rating}"/>
			</select>
		</div>
		
		<div class="split-item"></div>
	</div>
		
	<div class="split-item-v-texto">
		<div class="offer-info">Oferta</div>
		<div>
			<h2 class="post-title offerText">${postTitle }</h2>
		</div>
		<div>
			<p class="post-description cortar">
				${ postDescription}
			</p>
		</div>
		<p class="post-date">${ postDate}</p>
	</div>
</div>


