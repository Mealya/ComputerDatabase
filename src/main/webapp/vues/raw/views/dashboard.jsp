<%@ page import="java.util.List"%>
<%@ page import="com.excilys.model.Computer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="page" %>

<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
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
			<a class="navbar-brand" href="/ComputerDatabaseMaven/dash">
				Application - Computer Database </a>
		</div>
	</header>

	<section id="main">
		<div class="container">
			 
					<c:if test="${param.retourn == 1}">
						<p class="alert alert-success">Computer edited !</p>
					</c:if>
					<c:if test="${param.retourn == 2}">
						<p class="alert alert-success">Computer(s) deleted !</p>
					</c:if>
				 
			<h1 id="homeTitle">
				<c:out value="${nbComputers}" />
				Computers found
			</h1>
			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm" action="#" method="GET" class="form-inline">

						<input type="search" id="searchbox" name="search"
							class="form-control" placeholder="Search name" required /> <input
							type="submit" id="searchsubmit" value="Filter by name"
							class="btn btn-primary" />
					</form>
				</div>
				<div class="pull-right">
					<a class="btn btn-success" id="addComputer"
						href="/ComputerDatabaseMaven/add">Add Computer</a> <a
						class="btn btn-default" id="editComputer" href="#"
						onclick="$.fn.toggleEditMode();">Edit</a>
				</div>
			</div>
		</div>

		<form id="deleteForm" action="/ComputerDatabaseMaven/delete"
			method="POST">
			<input type="hidden" name="selection" value="">
		</form>

		<div class="container" style="margin-top: 10px;">
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<!-- Variable declarations for passing labels as parameters -->
						<!-- Table header for Computer Name -->

						<th class="editMode" style="width: 60px; height: 22px;"><input
							type="checkbox" id="selectall" /> <span
							style="vertical-align: top;"> - <a href="#"
								id="deleteSelected" onclick="$.fn.deleteSelected();"> <i
									class="fa fa-trash-o fa-lg"></i>
							</a>
						</span></th>
						<th>Computer name</th>
						<th>Introduced date</th>
						<!-- Table header for Discontinued Date -->
						<th>Discontinued date</th>
						<!-- Table header for Company -->
						<th>Company</th>

					</tr>
				</thead>
				<!-- Browse attribute computers -->
				<tbody id="results">
					<c:forEach items="${computers}" var="computer">
						<tr>
							<td class="editMode"><input type="checkbox" name="cb"
								class="cb" value="${computer.getId()}"></td>
							<td><a href="/ComputerDatabaseMaven/edit?id=${computer.getId()}" onclick="">${computer.getName()}</a></td>
							<td>${computer.getIntro().toLocalDateTime().toLocalDate()}</td>
							<td>${computer.getDisco().toLocalDateTime().toLocalDate()}</td>
							<td>${computer.getComp().getName()}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</section>
	<footer class="navbar-fixed-bottom">
		<div class="container text-center">
			<page:pagination pageCourante="${param.page}" nbComputers="${nbComputers}" />
				 
			<!-- Boutons taille des pages -->
			<div class="btn-group btn-group-sm pull-right" role="group">
				<button type="button"
					onclick="window.location.href ='/ComputerDatabaseMaven/dash?size=10';"
					class="btn btn-default">10</button>

				<button type="button"
					onclick="window.location.href ='/ComputerDatabaseMaven/dash?size=50';"
					class="btn btn-default">50</button>

				<button type="button"
					onclick="window.location.href ='/ComputerDatabaseMaven/dash?size=100';"
					class="btn btn-default">100</button>

			</div>
		</div>
	</footer>
	<script src="vues/raw/js/jquery.min.js"></script>
	<script src="vues/raw/js/bootstrap.min.js"></script>
	<script src="vues/raw/js/dashboard.js"></script>
</body>
</html>