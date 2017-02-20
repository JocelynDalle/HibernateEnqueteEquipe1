<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="fr">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<script src="js/jquery-3.1.1.min.js">
</script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>
<title>Liste des Enquetes</title>
</head>
<body>
	<div class="container-fluid">
		<nav class="navbar navbar-default navbar-fixed-bottom">
			<p class="navbar-right">
				<a href="SurveyServlet?typeAction=create&typeSurvey=surveyPhone">
					<button type="submit" class="btn btn-primary navbar-btn">
						<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
						Enquête téléphonique
					</button>
				</a> <a href="SurveyServlet?typeAction=create&typeSurvey=surveyInternet">
					<button type="submit" class="btn btn-primary navbar-btn">
						<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
						Enquête internet
					</button>
				</a> <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
			</p>
			<p class="navbar-text">Nombre total d'enquête(s):
				${surveys.size()}</p>
		</nav>
		<div class="container-fluid">
			<h1>Liste des Enquetes</h1>
			<c:forEach var="survey" items="${surveys}">
				<div class="panel panel-default">
					<div class="panel-heading">
						<div class="row">
							<div class="col-md-4 text-left">
								<h4>
									<strong>${survey.name}</strong>
								</h4>
								<c:choose>
									<c:when test="${survey.getClass().simpleName eq 'SurveyPhone'}">
										<span class="glyphicon glyphicon-earphone" aria-hidden="true"></span>
									</c:when>
									<c:otherwise>
										<span class="glyphicon glyphicon-phone" aria-hidden="true"></span>
									</c:otherwise>
								</c:choose>
								${survey.getClass().simpleName eq 'SurveyPhone' ? "Enquête téléphonique" : "Enquête internet"}
							</div>
							<div class="col-md-4 text-center">
								<em>Plannifi&eacute;e le:</em> <strong>${survey.date}</strong>
							</div>
							<div class="col-md-4 text-right">
								<form action="SurveysServlet" method="post">
									<div class="btn-group" role="group"">
										<button type="submit" name="typeAction" value="update"
											class="btn btn-success">Modifier l'enquête</button>
										<input type="hidden" name="typeSurvey"
											value="${survey.getClass().simpleName eq 'SurveyPhone' ? "surveyPhone" : "surveyInternet"}">
										<input type="hidden" name="idSurvey" value="${survey.id}">
										<button type="submit" name="typeAction" value="delete"
											class="btn btn-warning">
											<span class="glyphicon glyphicon-remove-sign"
												aria-hidden="true"></span>
										</button>
									</div>
								</form>
								<br> prix <strong>${survey.price}</strong> € HT
							</div>
						</div>
					</div>
					<div class="panel-body">
						<c:if test="${survey.getClass().simpleName eq 'SurveyPhone'}">
							<blockquote>
								<p>${survey.script}</p>
								<footer>
									<span class="glyphicon glyphicon-bullhorn" aria-hidden="true"></span><cite
										title="Source Title"> Accroche téléphonique</cite>
								</footer>

							</blockquote>
						</c:if>
						<div class="panel-group" role="tablist">
							<div class="panel panel-default">
								<div class="panel-heading text-center" role="tab"
									id="heading${survey.id}">
									<h4 class="panel-title">
										<span class="glyphicon glyphicon-list" aria-hidden="true"></span>
										<a class="collapsed" role="button" data-toggle="collapse"
											href="#collapse${survey.id}"
											aria-expanded="false" aria-controls="collapse${survey.id}">
											Questions </a>
									</h4>
								</div>
								<div id="collapse${survey.id}"
									class="panel-collapse collapse" role="tabpanel"
									aria-labelledby="heading${survey.id}">
									<div class="list-group">
										<a href="QuestionAddServlet?idSurvey=${survey.id}"
											class="list-group-item list-group-item-info text-center">Ajouter une
											question</a>
										<c:forEach var="question" items="${survey.lsQuestion}">
											<a href="QuestionUpdateServlet?idQuestion=${question.id}"
												class="list-group-item">${question.wording} </a>
										</c:forEach>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
			<br> <br>

		</div>
	</div>
</body>
</html>
