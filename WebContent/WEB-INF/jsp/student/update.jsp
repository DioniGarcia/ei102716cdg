<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Modify student</title>
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
	        <a class="mdl-navigation__link" href="../list.html">List</a>
	        <a class="mdl-navigation__link" href="../add.html">Add</a>
	      </nav>
	    </div>
	  </header>
	  <div class="mdl-layout__drawer">
	    <span class="mdl-layout-title">Student</span>
	    <nav class="mdl-navigation">
	      <a class="mdl-navigation__link" href="../list.html">List</a>
	      <a class="mdl-navigation__link" href="../add.html">Add</a>
	    </nav>
	    <span class="mdl-layout-title">Offer (WIP)</span>
	    <nav class="mdl-navigation">
	      <a class="mdl-navigation__link" href="">List</a>
	      <a class="mdl-navigation__link" href="">Add</a>
	    </nav>
	  </div>
	  <main class="mdl-layout__content">
	    <div class="page-content">
	    	<!-- Your content goes here -->
	    	<h4>Modify student</h4>
			<form:form method="post" modelAttribute="student">
			
			<div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
				<form:label class="mdl-textfield__label" path="nif">NIF</form:label>
				<form:input class="mdl-textfield__input" path="nif"/>
			</div>
			<br>
			<div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
				<form:label class="mdl-textfield__label" path="name">Name</form:label>
				<form:input class="mdl-textfield__input" path="name"/>
			</div>
			<br>
			<div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
				<form:label class="mdl-textfield__label" path="email">Email</form:label>
				<form:input class="mdl-textfield__input" path="email"/>
			</div>
			<br>
			<div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
				<form:label class="mdl-textfield__label" path="userName">Username</form:label>
				<form:input class="mdl-textfield__input" path="userName"/>
			</div>
			<br>
			<div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
				<form:label class="mdl-textfield__label" path="passwd">Password</form:label>
				<form:input class="mdl-textfield__input" path="passwd"/>
			</div>
			<br>
			<input type="submit" class="btn btn-default" value="Modify student"/>
			
		</form:form>
	    </div>
	  </main>
	</div>
</body>
</html>