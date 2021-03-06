<%@ page import="java.sql.Timestamp"%>
<%@ page import="java.util.List"%>
<%@ page import="com.excilys.model.Company"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
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
	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<div class="label label-default pull-right">
						id:
						<c:out value="${id}" />
					</div>


					<h1>Edit Computer</h1>

					<form action="/ComputerDatabaseMaven/editComputer" method="POST">

						<input type="hidden" value="${id}" name="id" id="id"/>

						<fieldset>
							<div class="form-group">
								<label for="computerName">Computer name</label>
								<c:choose>
									<c:when test="${name != null}">
										<input type="text" class="form-control" id="computerName" name="computerName" placeholder="Computer name" value="${name}" />
									</c:when>
									<c:otherwise>
										<input type="text" class="form-control" id="computerName" name="computerName" placeholder="Computer name" />
									</c:otherwise>
								</c:choose>
							</div>
							<div class="form-group">
								<label for="introduced">Introduced date</label>
								<c:choose>
									<c:when test="${name != intro}">
										<input type="date" class="form-control" id="introduced" name="introduced" placeholder="Introduced date" value="${intro.toLocalDateTime().toLocalDate()}" />
									</c:when>
									<c:otherwise>
										<input type="date" class="form-control" id="introduced"	name="introduced" placeholder="Introduced date" />
									</c:otherwise>
								</c:choose>
							</div>
							<div class="form-group">
								<label for="discontinued">Discontinued date</label>
								<c:choose>
									<c:when test="${name != disco}">
										<input type="date" class="form-control" id="discontinued" name="discontinued" placeholder="Discontinued date" value="${disco.toLocalDateTime().toLocalDate()}" />
									</c:when>
									<c:otherwise>
										<input type="date" class="form-control" id="discontinued" name="discontinued" placeholder="Discontinued date" />
									</c:otherwise>
								</c:choose>
							</div>
							<div class="form-group">
								<label for="companyId">Company</label>
								 <select class="form-control" id="companyId" name="companyId">
									<option value="0">-- Empty --</option>
									<c:forEach items="${companies}" var="compa">
										<c:choose>
											<c:when test="${idCompa == compa.getId()}">
												<option value="${compa.getId()}" selected>${compa.getName()}</option>
											</c:when>
											<c:otherwise>
												<option value="${compa.getId()}">${compa.getName()}</option>
											</c:otherwise>
										</c:choose>

									</c:forEach>
								</select>
							</div>
						</fieldset>
						<div class="actions pull-right">
							<input type="submit" value="Edit" class="btn btn-primary">
							or <a href="/ComputerDatabaseMaven/dashboard" class="btn btn-default">Cancel</a>
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>
</body>
</html>