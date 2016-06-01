<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html xmlns:th="http://www.thymeleaf.org" xmlns:tiles="http://www.thymeleaf.org">
  <head>
    <title tiles:fragment="title">Login</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<!-- Bootstrap -->
	<link href="vues/raw/css/bootstrap.min.css" rel="stylesheet"
		media="screen">
	<link href="vues/raw/css/font-awesome.css" rel="stylesheet"
		media="screen">
	<link href="vues/raw/css/main.css" rel="stylesheet" media="screen">

</head>
  <body>
  <header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="/ComputerDatabaseMaven/dashboard">
				Application - Computer Database </a>
		</div>
	</header>
	<section id="main">
	<div class="container">
	<div class="row">
	    <div tiles:fragment="content">
	        <form name="f" th:action="@{/login}" method="post">      
	                 
	            <fieldset>
	            <legend>Please Login</legend> 
	            	<c:if test="${param.error ne null}">
						<div class="alert alert-danger">    
	                    	Invalid username and password.
	                	</div>
					</c:if>
	                <c:if test="${param.logout ne null}">
						<div th:if="${param.logout != null}" class="alert alert-info"> 
	                    	You have been logged out.
	                	</div>
					</c:if>
	                
					<div class="form-group">
		                <label for="username">Username</label>
		                <input type="text" id="username" name="username"/>       
	                </div> 
	                <div class="form-group">
		                <label for="password">Password</label>
		                <input type="password" id="password_default" name="password_default"/>    
		                <input type="hidden" id="password" name="password"/>    
	                </div> 
	                <div class="form-actions">
	                    <button id="cmd_valider" type="submit" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-log-in"></span>&nbsp; Log in</button>
	                </div>
	            </fieldset>
	        </form>
	    </div>
	    </div>
	    </div>
    </section>
    
	<script src="vues/raw/js/jquery.min.js"></script>
	<script src="vues/raw/js/bootstrap.min.js"></script>
	<script src="vues/raw/js/validation.js"></script>
	<script src="vues/raw/js/jquery.sha256.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			$("#cmd_valider").on("click", function(){
				var mdp = $("#password_default").val();
				var mdpcrypt = $.sha256(mdp);
				$("#password").val(mdpcrypt);
			});
		});
	</script>
  </body>
</html>