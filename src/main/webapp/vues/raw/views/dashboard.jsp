<%@ page import="java.util.List"%>
<%@ page import="com.excilys.model.Computer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
			<%

			    if (request.getParameter("return") != null) {
			        Integer choix = Integer.parseInt(request.getParameter("return"));
			        if (choix == 1) {
			            out.println("<p class=\"alert alert-success\">Computer edited !</p>");
			        } else {
			            out.println("<p class=\"alert alert-success\">Computer(s) deleted !</p>");
			        }
			    }
			%>
			<h1 id="homeTitle">
				<% 
					String attribut = (String) request.getAttribute("nbComputers");
					out.println("<input type=\"hidden\" name=\"nbcompu\" value=\"" + (String) request.getAttribute("nbComputers") + "\">");
					out.println(attribut);
    			%>
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

		<form id="deleteForm" action="/ComputerDatabaseMaven/delete" method="POST">
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
					<% 

						List<Computer> computers = (List<Computer>) request.getAttribute("computers");
						for (Computer c : computers) {
    						out.println("<tr>");
							out.println("<td class=\"editMode\">");
                            out.println("<input type=\"checkbox\" name=\"cb\" class=\"cb\" value=\"" + c.getId() + "\">");
                       		out.println("</td>");
                        	out.println("<td>");
                            	out.println("<a href=\"/ComputerDatabaseMaven/edit?id=" + c.getId() + "\" onclick=\"\">"+ c.getName() +"</a>");
                        	out.println("</td>");
                        	if (c.getIntro() != null) {
                            	out.println("<td>" + c.getIntro().toLocalDateTime().toLocalDate() + "</td>");
                        	} else {
                            	out.println("<td> </td>");
                        	}
                        	if (c.getDisco() != null) {
                            	out.println("<td>" + c.getDisco().toLocalDateTime().toLocalDate() + "</td>");
                        	} else {
                            	out.println("<td> </td>");
                        	}
                        	if (c.getComp() != null) {
                            	out.println("<td>" + c.getComp().getName() + "</td>");
                        	} else {
                            	out.println("<td> </td>");
                        	}
                        	out.println("</tr>");
						}
    				%>

				</tbody>
			</table>
		</div>
	</section>
	<footer class="navbar-fixed-bottom">
		<div class="container text-center">
			<ul class="pagination">
				<c:if test="${param.page != null}">
					<c:if test="${param.page != 1}">
						<li><a
							href="/ComputerDatabaseMaven/dash?page=${param.page - 1}"
							aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
						</a></li>
					</c:if>
				</c:if>
				<c:if test="${param.page == null}">
					<c:forEach begin="1" end="5" var="i">
						<c:choose>
							<c:when test="${param.page eq i}">
								<li><a href="/ComputerDatabaseMaven/dash?page=${i}">${i}</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="/ComputerDatabaseMaven/dash?page=${i}">${i}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<li><a href="/ComputerDatabaseMaven/dash?page=2"
						aria-label="Next"> <span aria-hidden="true">&raquo;</span>
					</a></li>
				</c:if>

				<c:if test="${param.page == 1}">
					<c:forEach begin="1" end="${param.page + 4}" var="i">
						<c:choose>
							<c:when test="${param.page eq i}">
								<li><a href="/ComputerDatabaseMaven/dash?page=${i}">${i}</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="/ComputerDatabaseMaven/dash?page=${i}">${i}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<li><a
						href="/ComputerDatabaseMaven/dash?page=${param.page + 1}"
						aria-label="Next"> <span aria-hidden="true">&raquo;</span>
					</a></li>
				</c:if>

				<c:if test="${param.page == 2}">
					<c:forEach begin="1" end="${param.page + 3}" var="i">
						<c:choose>
							<c:when test="${param.page eq i}">
								<li><a href="/ComputerDatabaseMaven/dash?page=${i}">${i}</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="/ComputerDatabaseMaven/dash?page=${i}">${i}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<li><a
						href="/ComputerDatabaseMaven/dash?page=${param.page + 1}"
						aria-label="Next"> <span aria-hidden="true">&raquo;</span>
					</a></li>
				</c:if>

				<c:if test="${param.page > 2}">
					<c:forEach begin="${param.page - 2}" end="${param.page + 2}"
						var="i">
						<c:choose>
							<c:when test="${param.page eq i}">
								<li><a href="/ComputerDatabaseMaven/dash?page=${i}">${i}</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="/ComputerDatabaseMaven/dash?page=${i}">${i}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<li><a
						href="/ComputerDatabaseMaven/dash?page=${param.page + 1}"
						aria-label="Next"> <span aria-hidden="true">&raquo;</span>
					</a></li>
				</c:if>

			</ul>


			<div class="btn-group btn-group-sm pull-right" role="group">
				<c:choose>
					<c:when test="${param.page != null}">
						<button type="button"
							onclick="window.location.href ='/ComputerDatabaseMaven/dash?size=10&page=${param.page}';"
							class="btn btn-default">10</button>
					</c:when>
					<c:otherwise>
						<button type="button"
							onclick="window.location.href ='/ComputerDatabaseMaven/dash?size=10';"
							class="btn btn-default">10</button>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${param.page != null}">
						<button type="button"
							onclick="window.location.href ='/ComputerDatabaseMaven/dash?size=50&page=${param.page}';"
							class="btn btn-default">50</button>
					</c:when>
					<c:otherwise>
						<button type="button"
							onclick="window.location.href ='/ComputerDatabaseMaven/dash?size=50';"
							class="btn btn-default">50</button>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${param.page != null}">
						<button type="button"
							onclick="window.location.href ='/ComputerDatabaseMaven/dash?size=100&page=${param.page}';"
							class="btn btn-default">100</button>
					</c:when>
					<c:otherwise>
						<button type="button"
							onclick="window.location.href ='/ComputerDatabaseMaven/dash?size=100';"
							class="btn btn-default">100</button>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</footer>
	<script src="vues/raw/js/jquery.min.js"></script>
	<script src="vues/raw/js/bootstrap.min.js"></script>
	<script src="vues/raw/js/dashboard.js"></script>
</body>
</html>