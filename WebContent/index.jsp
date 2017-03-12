<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>EI1027</title>
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
	      <img src= "<c:url value="/resources/logo.png" />" style="mix-blend-mode: multiply;" height="72" width="72"/>
	      <span class="mdl-layout-title">Title</span>
	      <!-- Add spacer, to align navigation to the right -->
	      <div class="mdl-layout-spacer"></div>
	      <!-- Navigation. We hide it in small screens. -->
	      <nav class="mdl-navigation mdl-layout--large-screen-only">
	        <a class="mdl-navigation__link" href="">Home</a>
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
	    	<ul>
				<li><a href="student/list.html">List all students</a></li>
				<li><a href="student/add.html">Add student</a></li>
			</ul>
	    </div>
	  </main>
</body>
</html>