<%@ page import="java.util.List"%>
<%@ page import="com.excilys.model.Computer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="page"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

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
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="nav navbar-nav navbar-left">
			<c:choose>
					<c:when test="${param.language != null}">
						<a class="navbar-brand"
							href="/ComputerDatabaseMaven/dashboard?language=${param.language}">
					<spring:message code="dashboard.name" text="App computer DB" /></a>
					</c:when>
					<c:otherwise>
						<a class="navbar-brand" href="/ComputerDatabaseMaven/dashboard">
					<spring:message code="dashboard.name" text="App computer DB" /></a>
					</c:otherwise>
				</c:choose>
			</div>

			<ul class="nav navbar-nav navbar-right">
				<li><a href="?language=fr"><img src="vues/raw/fonts/France.png" alt="French"
						style="width: 25px; height: 25px;"></a></li>
				<li><a href="?language=en"><img src="vues/raw/fonts/UnitedKingdom.png" alt="UK"
						style="width: 25px; height: 25px;"></a></li>
			</ul>
			<div class="nav navbar-nav navbar-right">
				<div class="form-group">
					<button style="margin-top: 11%;" onclick="window.location.href ='/ComputerDatabaseMaven/logout'" type="submit" class="btn btn-primary btn-sm">
						<span class="glyphicon glyphicon-log-out"></span> Déconnexion
					</button>
				</div>
			</div>
		</div>
	</nav>
	<!-- Current Locale : ${pageContext.response.locale} -->
	<section id="main">
		<div class="container">

			<c:if test="${param.retourn == 1}">
				<p class="alert alert-success"><spring:message code="dashboard.edited" text="Computer edited !" /></p>
			</c:if>
			<c:if test="${param.retourn == 2}">
				<p class="alert alert-success"><spring:message code="dashboard.deleted" text="Computer(s) deleted !" /></p>
			</c:if>

			<h1 id="homeTitle">
				<c:out value="${nbComputers}" />
				<spring:message code="dashboard.found" text="Computer(s) found" />
			</h1>
			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm" action="#" method="GET" class="form-inline">

						<input type="search" id="searchbox" name="search"
							class="form-control" placeholder="<spring:message code="dashboard.Bshearch" text="Search name" />" required /> <input
							type="submit" id="searchsubmit" value="<spring:message code="dashboard.Bfilter" text="Filter by name" />"
							class="btn btn-primary" />
					</form>
				</div>
				<div class="pull-right">
					<c:choose>
						<c:when test="${param.language != null}">
							<a class="btn btn-success" id="addComputer"
								href="/ComputerDatabaseMaven/addComputerForm?language=${param.language}"><spring:message
									code="dashboard.Badd" text="Add Computer" /></a>

						</c:when>
						<c:otherwise>
							<a class="btn btn-success" id="addComputer"
								href="/ComputerDatabaseMaven/addComputerForm"><spring:message
									code="dashboard.Badd" text="Add Computer" /></a>

						</c:otherwise>
					</c:choose>

					<!--<a class="btn btn-default" id="editComputer" href="#"
						onclick="$.fn.toggleEditMode();"><spring:message code="dashboard.Bedit" text="Edit" /></a>-->
				</div>
			</div>
		</div>

		<form id="deleteForm" action="/ComputerDatabaseMaven/deleteComputer" method="POST">
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

						<c:choose>
							<c:when test="${param.orderby eq 'name;asc'}">
								<th><a
									href="${currentURL}${currentParams}orderby=name;desc"> <span
										class="glyphicon glyphicon glyphicon-sort"></span>
								</a><spring:message code="dashboard.cname" text="Computer name" /></th>
							</c:when>
							<c:otherwise>
								<th><a href="${currentURL}${currentParams}orderby=name;asc">
										<span class="glyphicon glyphicon glyphicon-sort"></span>
								</a><spring:message code="dashboard.cname" text="Computer name" /></th>
							</c:otherwise>
						</c:choose>

						<c:choose>
							<c:when test="${param.orderby eq 'intro;asc'}">
								<th><a
									href="${currentURL}${currentParams}orderby=intro;desc">
										<span class="glyphicon glyphicon glyphicon-sort"></span>
								</a><spring:message code="dashboard.cintro" text="Introduced date" /></th>
							</c:when>
							<c:otherwise>
								<th><a
									href="${currentURL}${currentParams}orderby=intro;asc">
										<span class="glyphicon glyphicon glyphicon-sort"></span>
								</a><spring:message code="dashboard.cintro" text="Introduced date" /></th>
							</c:otherwise>

						</c:choose>
						<c:choose>
							<c:when test="${param.orderby eq 'disco;asc'}">
								<th><a
									href="${currentURL}${currentParams}orderby=disco;desc">
										<span class="glyphicon glyphicon glyphicon-sort"></span>
								</a><spring:message code="dashboard.cdisco" text="Discontinued date" /></th>
							</c:when>
							<c:otherwise>
								<th><a
									href="${currentURL}${currentParams}orderby=disco;asc">
										<span class="glyphicon glyphicon glyphicon-sort"></span>
								</a><spring:message code="dashboard.cdisco" text="Discontinued date" /></th>
							</c:otherwise>
						</c:choose>


						<c:choose>
							<c:when test="${param.orderby eq 'company_id;asc'}">
								<th><a
									href="${currentURL}${currentParams}orderby=company_id;desc">
										<span class="glyphicon glyphicon glyphicon-sort"></span>
								</a><spring:message code="dashboard.ccompany" text="Company" /></th>
							</c:when>
							<c:otherwise>
								<th><a
									href="${currentURL}${currentParams}orderby=company_id;asc">
										<span class="glyphicon glyphicon glyphicon-sort"></span>
								</a><spring:message code="dashboard.ccompany" text="Company" /></th>
							</c:otherwise>
						</c:choose>


					</tr>
				</thead>
				<!-- Browse attribute computers -->
				<tbody id="results">
					<c:forEach items="${computers}" var="computer">
						<tr>
							<td class="editMode"><input type="checkbox" name="cb"
								class="cb" value="${computer.getId()}"
								id="${computer.getName().concat('_').concat('id')}"></td>
							<td>
							<c:choose>
								<c:when test="${param.language != null}">
									<a id="${computer.getName().concat('_').concat('name')}" href="/ComputerDatabaseMaven/editComputerForm?id=${computer.getId()}&language=${param.language}">${computer.getName()}</a>
								</c:when>
								<c:otherwise>
									<a id="${computer.getName().concat('_').concat('name')}" href="/ComputerDatabaseMaven/editComputerForm?id=${computer.getId()}">${computer.getName()}</a>
								</c:otherwise>
							</c:choose>
								</td>
							<td>
								<c:choose>
									<c:when test="${param.language == 'fr'}">
										<fmt:formatDate value="${computer.getIntro()}" var="stdDatum" type="date" pattern="dd-MM-yyyy" />
										${stdDatum}
									</c:when>
									<c:otherwise>
										<fmt:formatDate value="${computer.getIntro()}" var="stdDatum" type="date" pattern="yyyy-MM-dd" />
										${stdDatum}
									</c:otherwise>
								</c:choose>
								
							</td>
							<td>
								<c:choose>
									<c:when test="${param.language == 'fr'}">
										<fmt:formatDate value="${computer.getDisco()}" var="stdDatum" type="date" pattern="dd-MM-yyyy" />
										${stdDatum}
									</c:when>
									<c:otherwise>
										<fmt:formatDate value="${computer.getDisco()}" var="stdDatum" type="date" pattern="yyyy-MM-dd" />
										${stdDatum}
									</c:otherwise>
								</c:choose>
							</td>
							<td>${computer.getComp().getName()}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</section>
	<c:choose>
		<c:when test="${param.search == null}">
			<footer class="navbar-fixed-bottom">
				<div class="container text-center">
					<page:pagination pageCourante="${param.page}"
						nbComputers="${nbComputers}" lang="${param.language}" />

					<!-- Boutons taille des pages -->
					<div class="btn-group btn-group-sm pull-right" role="group">
						<c:choose>
							<c:when test="${param.language != null}">
								<button type="button"
									onclick="window.location.href ='/ComputerDatabaseMaven/dashboard?size=10&language=${param.language}';"
									class="btn btn-default">10</button>
							</c:when>
							<c:otherwise>
								<button type="button"
									onclick="window.location.href ='/ComputerDatabaseMaven/dashboard?size=10';"
									class="btn btn-default">10</button>
							</c:otherwise>
						</c:choose>

						<c:choose>
							<c:when test="${param.language != null}">
								<button type="button"
									onclick="window.location.href ='/ComputerDatabaseMaven/dashboard?size=50&language=${param.language}';"
									class="btn btn-default">50</button>
							</c:when>
							<c:otherwise>
								<button type="button"
									onclick="window.location.href ='/ComputerDatabaseMaven/dashboard?size=50';"
									class="btn btn-default">50</button>
							</c:otherwise>
						</c:choose>

						<c:choose>
							<c:when test="${param.language != null}">
								<button type="button"
									onclick="window.location.href ='/ComputerDatabaseMaven/dashboard?size=100&language=${param.language}';"
									class="btn btn-default">100</button>
							</c:when>
							<c:otherwise>
								<button type="button"
									onclick="window.location.href ='/ComputerDatabaseMaven/dashboard?size=100';"
									class="btn btn-default">100</button>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</footer>
		</c:when>
		<c:otherwise>

		</c:otherwise>
	</c:choose>

	<script src="vues/raw/js/jquery.min.js"></script>
	<script src="vues/raw/js/bootstrap.min.js"></script>
	<script src="vues/raw/js/dashboard.js"></script>
</body>
</html>