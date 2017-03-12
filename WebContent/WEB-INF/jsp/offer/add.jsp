<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>New offer</title>
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.indigo-pink.min.css">
<script defer src="https://code.getmdl.io/1.3.0/material.min.js"></script>
<!--getmdl-select-->   
<link rel="stylesheet" href="https://cdn.rawgit.com/CreativeIT/getmdl-select/master/getmdl-select.min.css">
<script defer src="https://cdn.rawgit.com/CreativeIT/getmdl-select/master/getmdl-select.min.js"></script>
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
	        <a class="mdl-navigation__link" href="">Hello</a>
	      </nav>
	    </div>
	  </header>
	  <div class="mdl-layout__drawer">
	    <span class="mdl-layout-title">Student</span>
	    <nav class="mdl-navigation">
	      <a class="mdl-navigation__link" href="../student/list.html">List</a>
	      <a class="mdl-navigation__link" href="../student/add.html">Add</a>
	    </nav>
	    <span class="mdl-layout-title">Offer</span>
	    <nav class="mdl-navigation">
	      <a class="mdl-navigation__link" href="add.html">List</a>
	      <a class="mdl-navigation__link" href="">Add</a>
	    </nav>
	    <span class="mdl-layout-title">Skill</span>
	    <nav class="mdl-navigation">
	      <a class="mdl-navigation__link" href="../skill/list.html">List</a>
	      <a class="mdl-navigation__link" href="../skill/add.html">Add</a>
	    </nav>
	  </div>
	  <main class="mdl-layout__content">
	    <div class="page-content">
	    	<!-- Your content goes here -->
	    	<h4>New student</h4>
			<form:form method="post" modelAttribute="offer">
			
			<div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
				<form:label class="mdl-textfield__label" path="id">ID</form:label>
				<form:input class="mdl-textfield__input" path="id"/>
			</div>
			<br>
			<div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
				<form:label class="mdl-textfield__label" path="startDate">Start Date</form:label>
				<form:input class="mdl-textfield__input" pattern="(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\d\d" path="startDate"/>
				<span class="mdl-textfield__error">Invalid date, format: DD/MM/YYYY</span>
			</div>
			<br>
			<div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
				<form:label class="mdl-textfield__label" path="endDate">End Date</form:label>
				<form:input class="mdl-textfield__input" pattern="(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\d\d" path="endDate"/>
				<span class="mdl-textfield__error">Invalid date, format: DD/MM/YYYY</span>
			</div>
			<br>
			<div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
				<form:label class="mdl-textfield__label" path="description">Description</form:label>
				<form:input class="mdl-textfield__input" path="description"/>
				<span class="mdl-textfield__error">Invalid email</span>
			</div>
			<br>
			<form:select path="nif" items="${nifs}"/>
			<br>
			<div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label getmdl-select getmdl-select">
            <form:input class="mdl-textfield__input" type="text" id="sample1" value="1234F" tabIndex="-1" path="nif"></form:input>
            <form:label for="sample1" class="mdl-textfield__label" path="nif">NIF</form:label>
            <ul for="sample1" class="mdl-menu mdl-menu--bottom-left mdl-js-menu">
            	<c:forEach items="${nifs}" var="nif">
            		<li class="mdl-menu__item">${nif }</li>	
            	</c:forEach>
            </ul>
        	</div>
			<br>
			<div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label getmdl-select getmdl-select">
            <form:input class="mdl-textfield__input" type="text" id="sample2" value="0" tabIndex="-1" path="skillId"></form:input>
            <form:label for="sample2" class="mdl-textfield__label" path="skillId">Skill ID</form:label>
            <ul for="sample2" class="mdl-menu mdl-menu--bottom-left mdl-js-menu">
            	<c:forEach items="${skills}" var="skill">
            		<li class="mdl-menu__item">${skill }</li>
            	</c:forEach>
            </ul>
        	</div>
        	<br>
			<input type="submit" class="btn btn-default" value="Add student"/>
			
		</form:form>
	    </div>
	  </main>
	</div>
</body>
</html>