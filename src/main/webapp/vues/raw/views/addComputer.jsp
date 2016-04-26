<%@ page import="java.util.List" %>
<%@ page import="com.excilys.model.Company" %>
<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="vues/raw/css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="vues/raw/css/font-awesome.css" rel="stylesheet" media="screen">
<link href="vues/raw/css/main.css" rel="stylesheet" media="screen">
</head>
<body>
    <header class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <a class="navbar-brand" href="/ComputerDatabaseMaven/dash"> Application - Computer Database </a>
        </div>
    </header>

    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2 box">
                	<% 
                		if (request.getAttribute("added") != null) {
                    		if ((int) request.getAttribute("added") != 2)
                    		out.println("<p class=\"alert alert-success\">Computer added !</p>");
                		}
                	%>
                    <h1>Add Computer</h1>
                    <form action="/ComputerDatabaseMaven/add" method="POST">
                        <fieldset>
                            <div class="form-group">
                                <label for="computerName">Computer name</label>
                                <input type="text" class="form-control" id="computerName" name="computerName" placeholder="Computer name">
                            </div>
                            <div class="form-group">
                                <label for="introduced">Introduced date</label>
                                <input type="date" class="form-control" id="introduced" name="introduced" placeholder="Introduced date">
                            </div>
                            <div class="form-group">
                                <label for="discontinued">Discontinued date</label>
                                <input type="date" class="form-control" id="discontinued" name="discontinued" placeholder="Discontinued date">
                            </div>
                            <div class="form-group">
                                <label for="companyId">Company</label>
                                <select class="form-control" id="companyId" name="companyId" >
                               		<option value="0">-- Empty --</option>
                                	<%
                                		List<Company> companies = (List<Company>) request.getAttribute("companies");
                                        for (Company c : companies) {
                                            out.println("<option value=" + c.getId() + ">" + c.getName() + "</option>");
                                        }
                                	
                                	%>
                                    
                                </select>
                            </div>                  
                        </fieldset>
                        <div class="actions pull-right">
                            <input type="submit" value="Add" class="btn btn-primary">
                            or
                            <a href="/ComputerDatabaseMaven/dash" class="btn btn-default">Cancel</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
</body>
</html>