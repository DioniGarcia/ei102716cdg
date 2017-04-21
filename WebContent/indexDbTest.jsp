<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<!--
    El siguiente indice no forma darte de la aplicación final,
    existe unicamente para asegurar el correcto funcionamiento 
    de la base de datos.
    Para llevar a cabo esta función permite, para cada tipo de datos contenido en la base de datos:
    	- Añadir uno nuevo elemento de dicho tipo a la base de datos
    	- Listar todos los elementos de dicho tipo, dando opción de editar y borrar cada uno de ellos
    	
    Los jsp relacionados con estas tareas se encuentran en "WEB-INF/jsp/db_test"
-->   
    
<html> 
  <head>
    <title>Skill Sharing - Index</title>
  </head>
  <body> 
    <ul>
      <li>Estudiante:
      <ul>
      	<li><a href="db_test/student/add.html" >Añadir Estudiante</a></li>
	  	<li><a href="db_test/student/list.html">Listar Estudiantes</a></li>
      </ul></li>
      <li>Ban:
      <ul>
      	<li><a href="db_test/ban/add.html" >Añadir Ban</a></li>
	  	<li><a href="db_test/ban/list.html">Listar Ban</a></li>
      </ul></li>
      <li>Administrador:
      <ul>
      	<li><a href="db_test/administrator/add.html" >Añadir Administrador</a></li>
	  	<li><a href="db_test/administrator/list.html">Listar Administradores</a></li>
      </ul></li>
      <li>Skill:
      <ul>
      	<li><a href="db_test/skill/add.html" >Añadir Skill</a></li>
	  	<li><a href="db_test/skill/list.html">Listar Skill</a></li>
      </ul></li>
      <li>Oferta:
      <ul>
      	<li><a href="db_test/offer/add.html" >Añadir Oferta</a></li>
	  	<li><a href="db_test/offer/list.html">Listar Oferta</a></li>
      </ul></li>
      
      <li>Request:
      <ul>
      	<li><a href="db_test/request/add.html" >Añadir Request</a></li>
	  	<li><a href="db_test/request/list.html">Listar Request</a></li>
      </ul></li>
      
      
      
      <li>Colaboracion:
      <ul>
      	<li><a href="db_test/collaboration/add.html" >Añadir Colaboracion</a></li>
	  	<li><a href="db_test/collaboration/list.html">Listar Colaboracion</a></li>
      </ul></li>  
      
      <li>
      <ul>
      </ul></li>  
      
      <li>LogIn:
      <ul>
      	<li><a href="login.html" >Página Login</a></li>
      </ul></li>  
    
    </ul>
  </body>
</html>