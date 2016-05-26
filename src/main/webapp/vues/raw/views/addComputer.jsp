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
<style type="text/css">
.has-warning .control-label, .has-warning .help-block, .has-warning .form-control-feedback
	{
	color: #f39c12;
}
</style>
</head>
<body>

	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<c:choose>
					<c:when test="${param.language != null}">
						<a class="navbar-brand"
							href="/ComputerDatabaseMaven/dashboard?language=${param.language}"><spring:message
								code="add.name" text="App computer DB" /> </a>
					</c:when>
					<c:otherwise>
						<a class="navbar-brand" href="/ComputerDatabaseMaven/dashboard"><spring:message
								code="add.name" text="App computer DB" /> </a>
					</c:otherwise>
				</c:choose>

			</div>

			<ul class="nav navbar-nav navbar-right">
				<li><a href="?language=fr"><img
						src="vues/raw/fonts/France.png" alt="French"
						style="width: 25px; height: 25px;"></a></li>
				<li><a href="?language=en"><img
						src="vues/raw/fonts/UnitedKingdom.png" alt="UK"
						style="width: 25px; height: 25px;"></a></li>
			</ul>
		</div>
	</nav>

	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<c:if test="${added == 1}">
						<p id="compuAdded" class="alert alert-success"><spring:message code="add.added" text="Computer added !" /></p>
					</c:if>

					<h1><spring:message code="add.title" text="Add computer" /></h1>
					<form action="/ComputerDatabaseMaven/addComputer" method="POST">
						<fieldset>
							<div class="form-group has-error has-feedback">
								<label for="computerName"><spring:message code="add.cname" text="Computer name" /></label> 
								<input type="text" class="form-control control-label" id="computerName" name="computerName"	placeholder="<spring:message code="add.cname" text="Computer name" />" />
								<span class="glyphicon form-control-feedback" id="computerName"></span>
								<span class="glyphicon glyphicon-remove form-control-feedback" id="computerNamel"></span>
								<div id="errorName" class="alert alert-danger" role="alert">
									<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span> 
									<span class="sr-only"><spring:message code="add.error" text="Error" />:</span>
									<spring:message code="add.Fname" text="Enter a non empty name" />
								</div>
							</div>
							<div class="form-group has-warning has-feedback">
								<label for="introduced"><spring:message code="add.cintro" text="Introduced date" /></label> 
								<input type="date" class="form-control control-label" id="introduced" name="introduced" placeholder="<spring:message code="add.cintro" text="Introduced date" />" /> 
								<span class="glyphicon glyphicon-warning-sign form-control-feedback" id="introducedl"></span>
								<div id="errorIntro" class="alert alert-danger" role="alert" style="display: none;">
									<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span> 
									<span class="sr-only"><spring:message code="add.error" text="Error" />:</span>
									<spring:message code="add.Fintro1" text="Enter a valid or empty date" />
								</div>
								<div id="warnIntro" class="alert alert-warning" role="alert">
									<span class="glyphicon glyphicon-exclamation-sign"
										aria-hidden="true"></span> <span class="sr-only">Error:</span>
									<spring:message code="add.Fintro2" text="Introduced date is empty" />
								</div>
							</div>
							<div class="form-group has-warning has-feedback">
								<label for="discontinued"><spring:message code="add.cdisco" text="Discontinued date" /></label> 
								<input type="date" class="form-control control-label" id="discontinued" name="discontinued" placeholder="<spring:message code="add.cdisco" text="Discontinued date" />" /> 
								<span class="glyphicon glyphicon-warning-sign form-control-feedback" id="discontinuedl"></span>
								<div id="warnDisco" class="alert alert-warning" role="alert">
									<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span> 
									<span class="sr-only"><spring:message code="add.error" text="Error" />:</span>
									<spring:message code="add.Fdisco2" text="Discontinued date is empty" />
								</div>
								<div id="errorDisco" class="alert alert-danger" role="alert" style="display: none;">
									<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
									<span class="sr-only">Error:</span>
									<spring:message code="add.Fdisco1" text="Enter a valid or empty date" />
								</div>
							</div>
							<div class="form-group has-feedback has-warning" id="validatorSelect">
								<label for="companyId"><spring:message code="add.ccompany" text="Company" /></label> 
								<select class="form-control" id="companyId" name="companyId" >
									<option value="0">-- <spring:message code="add.FcompanyDefault" text="Empty" /> --</option>
									<c:forEach items="${companies}" var="compa">
										<option value="${compa.getId()}">${compa.getName()}</option>
									</c:forEach>
								</select>
							</div>
						</fieldset>
						<div class="actions pull-right">
							<input type="submit" value="<spring:message code="add.Badd" text="Add" />" class="btn btn-primary" id="valid"> <spring:message code="add.or" text="Or" /> <a href="/ComputerDatabaseMaven/dashboard" class="btn btn-default"><spring:message code="add.Bcancel" text="Cancel" /></a>
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>
	<script src="vues/raw/js/jquery.min.js"></script>
	<script src="vues/raw/js/bootstrap.min.js"></script>
	<script src="vues/raw/js/validation.js"></script>
</body>
</html>