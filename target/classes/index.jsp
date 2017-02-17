<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>
<title>Liste des Enquetes</title>
</head>
<body>
	<div class="container-fluid">
		<h1>Liste des Enquetes</h1>
		<br>
		<c:forEach var="survey" items="${surveys}">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h2>${survey.name}</h2>
				</div>
				<div class="panel-body">
					<c:choose>
						<c:when test="${survey.getClass().simpleName eq 'SurveyPhone'}">
			Enquête téléphonique
			<br>
							<div class="list-group">
								<a href="QuestionUpdateServlet" class="list-group-item disabled"> Cras justo
									odio </a> <a href="#" class="list-group-item">Dapibus ac
									facilisis in</a> <a href="#" class="list-group-item">Morbi leo
									risus</a> <a href="#" class="list-group-item">Porta ac
									consectetur ac</a> <a href="#" class="list-group-item">Vestibulum
									at eros</a>
							</div>
							<br>
							<a
								href="SurveyServlet?typeAction=update&typeSurvey=surveyPhone&idSurvey=${survey.id }"></a>
							<button class="btn btn-success">Modifier enquête</button>
							<br>
						</c:when>
						<c:otherwise>
			Enquête internet
			<a
								href="SurveyServlet?typeAction=update&typeSurvey=surveyInternet&idSurvey=${survey.id }"></a>
							<button class="btn btn-success">Modifier enquête</button>
						</c:otherwise>
					</c:choose>
				</div>
				<div class="panel-footer">Plannifi&eacute;e le: ${survey.date}
				</div>
			</div>
		</c:forEach>
		<br> <br> <a
			href="SurveyServlet?typeSurvey=surveyPhone&typeAction=create">Ajouter
			une Enquête téléphonique</a> <br> <a
			href="SurveyServlet?typeSurvey=surveyInternet&typeAction=create">Ajouter
			une Enquête internet</a> <br>
		<p>Nombre total d'enquete(s): ${surveys.size()}</p>
	</div>
</body>
</html>
