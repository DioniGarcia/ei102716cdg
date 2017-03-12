<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>New skill</title>
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.indigo-pink.min.css">
<script defer src="https://code.getmdl.io/1.3.0/material.min.js"></script>
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
	    	<h4>New skill</h4>
			<form:form method="post" modelAttribute="skill">
			<div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
				<form:label class="mdl-textfield__label" path="name">Name</form:label>
				<form:input class="mdl-textfield__input" path="name"/>
			</div>
			<br>
			<div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
				<form:label class="mdl-textfield__label" path="description">Description</form:label>
				<form:input class="mdl-textfield__input" path="description"/>
			</div>
			<br>
			<div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
				<form:label class="mdl-textfield__label" path="level">Level</form:label>
				<form:input class="mdl-textfield__input" path="level"/>
			</div>
			<br>
			<label for="checkbox-1" class="mdl-checkbox mdl-js-checkbox mdl-js-ripple-effect" >
        		<input type="checkbox" id="skill_status" class="mdl-checkbox__input" path="status" />
        		<span class="mdl-checkbox__label" path="status">Status</span>
    		</label>
			<br>
			<input type="submit" class="mdl-button mdl-js-button mdl-button--raised" value="Add skill"/>
			
		</form:form>
	    </div>
	  </main>
	</div>
</body>
</html>