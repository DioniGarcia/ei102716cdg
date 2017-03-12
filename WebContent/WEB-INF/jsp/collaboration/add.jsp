<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>New collaboration</title>
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.indigo-pink.min.css">
<!--getmdl-select-->   
<link rel="stylesheet" href="<c:url value="/resources/mdl-selectfield.min.css"/> ">
</head>
<body>
	<!-- Always shows a header, even in smaller screens. -->
	<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
	  <header class="mdl-layout__header">
	    <div class="mdl-layout__header-row">
	      <!-- Title -->
	      <span class="mdl-layout-title">Title</span>
	      <!-- Add spacer, to align navigation to the right -->
	      <div class="mdl-layout-spacer"></div>
	      <!-- Navigation. We hide it in small screens. -->
	      <nav class="mdl-navigation mdl-layout--large-screen-only">
	        <a class="mdl-navigation__link" href="${pageContext.request.contextPath }/index.jsp">Home</a>
	      </nav>
	    </div>
	  </header>
	  <div class="mdl-layout__drawer">
	    <span class="mdl-layout-title">Student</span>
	    <nav class="mdl-navigation">
	      <a class="mdl-navigation__link" href="${pageContext.request.contextPath }/student/list.html">List</a>
	      <a class="mdl-navigation__link" href="${pageContext.request.contextPath }/student/add.html">Add</a>
	    </nav>
	    <span class="mdl-layout-title">Offer</span>
	    <nav class="mdl-navigation">
	      <a class="mdl-navigation__link" href="${pageContext.request.contextPath }/offer/list.html">List</a>
	      <a class="mdl-navigation__link" href="${pageContext.request.contextPath }/offer/add.html">Add</a>
	    </nav>
	    <span class="mdl-layout-title">Skill</span>
	    <nav class="mdl-navigation">
	      <a class="mdl-navigation__link" href="${pageContext.request.contextPath }/skill/list.html">List</a>
	      <a class="mdl-navigation__link" href="${pageContext.request.contextPath }/skill/add.html">Add</a>
	    </nav>
	    <span class="mdl-layout-title">Request</span>
	    <nav class="mdl-navigation">
	      <a class="mdl-navigation__link" href="${pageContext.request.contextPath }/request/list.html">List</a>
	      <a class="mdl-navigation__link" href="${pageContext.request.contextPath }/request/add.html">Add</a>
	    </nav>
	    <span class="mdl-layout-title">Collaboration</span>
	    <nav class="mdl-navigation">
	      <a class="mdl-navigation__link" href="${pageContext.request.contextPath }/collaboration/list.html">List</a>
	      <a class="mdl-navigation__link" href="${pageContext.request.contextPath }/collaboration/add.html">Add</a>
	    </nav>
	  </div>
	  <main class="mdl-layout__content">
	    <div class="page-content">
	    	<!-- Your content goes here -->
	    	<h4>New student</h4>
			<form:form method="post" modelAttribute="collaboration">
			<br>
			<div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
				<form:label class="mdl-textfield__label" path="startDate">Start Date</form:label>
				<form:input class="mdl-textfield__input" id="today1" type="date" path="startDate"/>
			</div>
			<br>
			<div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
				<form:label class="mdl-textfield__label" path="endDate">End Date</form:label>
				<form:input class="mdl-textfield__input" id="today2" type="date" path="endDate"/>
			</div>
			<br>
			<div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
				<form:label class="mdl-textfield__label" path="totalHours">totalHours</form:label>
				<form:input class="mdl-textfield__input" path="totalHours"/>
			</div>
			<br>
			<div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
				<form:label class="mdl-textfield__label" path="comments">comments</form:label>
				<form:input class="mdl-textfield__input" path="comments"/>
			</div>
			<br>
			<div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
				<form:label class="mdl-textfield__label" path="rating">rating</form:label>
				<form:input class="mdl-textfield__input" path="rating"/>
			</div>
			<br>
			<div class="mdl-selectfield mdl-js-selectfield mdl-selectfield--floating-label">
 				<form:select id="myselect" name="myselect" class="mdl-selectfield__select" path="offerId">
 					<c:forEach items="${offers}" var="offer">
 						<form:option value="${offer.id }">${offer.description}</form:option>
 					</c:forEach>
  				</form:select>
  				<label class="mdl-selectfield__label" for="myselect">Choose offer</label>
			</div>
			<br>
			<div class="mdl-selectfield mdl-js-selectfield mdl-selectfield--floating-label">
 				<form:select id="myselect2" name="myselect2" class="mdl-selectfield__select" path="requestId">
 					<c:forEach items="${requests}" var="request">
 						<form:option value="${request.id }">${request.description}</form:option>
 					</c:forEach>
  				</form:select>
  				<label class="mdl-selectfield__label" for="myselect2">Choose request</label>
			</div>
        	<br>
			<input type="submit" class="mdl-button mdl-js-button mdl-button--raised" value="Add collaboration"/>
			
		</form:form>
	    </div>
	  </main>
	</div>
<script defer src="https://code.getmdl.io/1.3.0/material.min.js"></script>
<script defer src="<c:url value="/resources/mdl-selectfield.min.js" />"></script>
<script src="<c:url value="/resources/base.js" />"></script>
</body>
</html>