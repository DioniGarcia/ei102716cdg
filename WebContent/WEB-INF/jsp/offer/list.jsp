<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Manage Students</title>
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
	  <main style="overflow-x: auto;" class="mdl-layout__content">
	    <div class="page-content">
	    	<!-- Your content goes here -->
	    	<h4>List students</h4>
			<table class="mdl-data-table mdl-js-data-table">
				<thead>
					<tr>
						<th class="mdl-data-table__cell--non-numeric">id</th>
						<th class="mdl-data-table__cell--non-numeric">startDate</th>
						<th class="mdl-data-table__cell--non-numeric">endDate</th>
						<th class="mdl-data-table__cell--non-numeric">description</th>
						<th class="mdl-data-table__cell--non-numeric">nif</th>
						<th class="mdl-data-table__cell--non-numeric">skill_id</th>
						<th class="mdl-data-table__cell--non-numeric">Actions</th>
					</tr>	
				</thead>
				<tbody>	
				<c:forEach items="${offers}" var="offer">
					<tr>
						<td class="mdl-data-table__cell--non-numeric">${offer.id }</td>
						<td class="mdl-data-table__cell--non-numeric">${offer.startDate }</td>
						<td class="mdl-data-table__cell--non-numeric">${offer.endDate }</td>
						<td class="mdl-data-table__cell--non-numeric">${offer.description }</td>
						<td class="mdl-data-table__cell--non-numeric">${offer.nif }</td>
						<td class="mdl-data-table__cell--non-numeric">${offer.skillId }</td>
						<td>
							<a style="display:inline-block;" href="update/${ offer.id }.html">
								<i class="material-icons">mode_edit</i>
							</a>
							<a style="display:inline-block;" href="delete/${ offer.id }.html">
								<i class="material-icons">delete</i>
							</a>
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>			
			<a href="add.html">
				<button style="position: fixed; bottom: 24px; right: 24px;" class="mdl-button mdl-js-button mdl-button--fab mdl-button--colored" aria-label="Add a category">
  					<i class="material-icons">add</i>
				</button>
			</a>
	    </div>
	  </main>
	
</body>
</html>