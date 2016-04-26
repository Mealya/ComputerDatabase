<%@ page import="java.util.List"%>
<%@ page import="com.excilys.model.Computer"%>
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

<script src="../js/jquery.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<script src="../js/dashboard.js"></script>
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
                		if (request.getAttribute("added") != null) {
                    		if ((int) request.getAttribute("added") == 1) {
                        		out.println("<p class=\"alert alert-success\">Computer edited !</p>");
                    		}
                		}
                	%>
			<h1 id="homeTitle">
				<% 
					String attribut = (String) request.getAttribute("nbComputers");
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

		<form id="deleteForm" action="#" method="POST">
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
                            	out.println("<a href=\"/ComputerDatabaseMaven/editComputer?id=" + c.getId() + "\" onclick=\"\">"+ c.getName() +"</a>");
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
				<li><a href="#" aria-label="Previous"> <span
						aria-hidden="true">&laquo;</span>
				</a></li>
				<li><a href="#">1</a></li>
				<li><a href="#">2</a></li>
				<li><a href="#">3</a></li>
				<li><a href="#">4</a></li>
				<li><a href="#">5</a></li>
				<li><a href="#" aria-label="Next"> <span aria-hidden="true">&raquo;</span>
				</a></li>
			</ul>

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
	</footer>

</body>
</html>