<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="fr">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="UTF-8">
<link rel="stylesheet" href="<c:url value="resources/css/bootstrap.min.css"/>">
<link rel="stylesheet" href="<c:url value="resources/css/style.css"/>">

<script src="<c:url value="resources/js/jquery-3.1.1.min.js"/>">
	
</script>
<script src="<c:url value="resources/js/bootstrap.min.js"/>">
	
</script>
<script src="<c:url value="resources/js/npm.js"/>">
	
</script>
<title>Barre de navigation</title>
</head>
<body>
	<nav class="navbar navbar-survey navbar-default navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<span class="brand">SurveyMaker</span>
			</div>
			<c:if test="${surveys != null}">
				<div class="navbar-left">
					<p class="navbar-text">Nombre total d'enquête(s):
						${surveys.size()}</p>
				</div>
			</c:if>
			<p class="navbar-right">
				<a href="SurveyServlet?typeAction=create&typeSurvey=surveyPhone">
					<button type="submit" class="btn btn-primary navbar-btn">
						<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
						Enquête <span class="glyphicon glyphicon-earphone"
							aria-hidden="true"></span>
					</button>
				</a> <a href="SurveyServlet?typeAction=create&typeSurvey=surveyInternet">
					<button type="submit" class="btn btn-primary navbar-btn">
						<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
						Enquête <span class="glyphicon glyphicon-phone" aria-hidden="true"></span>
					</button>
				</a> <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
			</p>
		</div>
	</nav>
	<header>
		<br> <br> <br> <br>
	</header>
	
	<footer>
		<br> <br> <br> <br> <br> <br> <br>
		<br> <br> <br> <br> <br> <br> <br>
		<br> <br> <br> <br> <br> <br> <br>
		<br> <br> <br> <br> <br> <br> <br>
		<br> <br>
	</footer>
</body>
</html>