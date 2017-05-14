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
										<button type="button" class="btn btn-warning"
											data-toggle="modal"
											data-target=".modal-delete-survey${survey.id}">
											<span class="glyphicon glyphicon-remove-sign"
												aria-hidden="true"></span>
										</button>
									</div>
								</form>

								<div
									class="modal fade modal-delete-survey${survey.id} text-left"
									tabindex="-1" role="dialog"
									aria-labelledby="mySmallModalLabel${survey.id}">
									<div class="modal-dialog" role="document">
										<div class="modal-content">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal"
													aria-label="Close">
													<span aria-hidden="true">&times;</span>
												</button>
												<h4 class="modal-title">Suppression de l'enquête</h4>
											</div>
											<div class="modal-body">
												<p>Voulez-vous vraiment supprimer ${survey.name}?</p>
											</div>
											<div class="modal-footer">
												<form action="SurveysServlet" method="post">
													<button type="submit" class="btn btn-default"
														name="typeAction" value="delete">Supprimer</button>
													<input type="hidden" name="idSurvey" value="${survey.id}">
													<input type="hidden" name="typeSurvey"
														value="${survey.getClass().simpleName eq 'SurveyPhone' ? "surveyPhone" : "surveyInternet"}">
													<button type="button" class="btn btn-primary"
														data-dismiss="modal">Annuler</button>
												</form>
											</div>
										</div>
										<!-- /.modal-content -->
									</div>
									<!-- /.modal-dialog -->
								</div>
								<!-- /.modal -->

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
											href="#collapse${survey.id}" aria-expanded="false"
											aria-controls="collapse${survey.id}"> Questions </a>
									</h4>
								</div>
								<div id="collapse${survey.id}" class="panel-collapse collapse"
									role="tabpanel" aria-labelledby="heading${survey.id}">
									<div class="list-group">
										<a href="#" data-toggle="modal"
											data-target=".modal-add-question${survey.id}"
											class="list-group-item list-group-item-info text-center">Ajouter
											une question</a>

										<div
											class="modal fade modal-add-question${survey.id} text-left"
											tabindex="-1" role="dialog"
											aria-labelledby="mySmallModalLabel${survey.id}">
											<div class="modal-dialog" role="document">
												<div class="modal-content">
													<div class="modal-header">
														<button type="button" class="close" data-dismiss="modal"
															aria-label="Close">
															<span aria-hidden="true">&times;</span>
														</button>
														<h4 class="modal-title">Ajouter une question</h4>
													</div>
													<div class="modal-body">
														<%@ include file="ajoutQuestion.jsp"%>
													</div>
													<div class="modal-footer">
														<button type="button" class="btn btn-primary"
															data-dismiss="modal">Annuler</button>
													</div>
												</div>
												<!-- /.modal-content -->
											</div>
											<!-- /.modal-dialog -->
										</div>
										<!-- /.modal -->
										<c:forEach var="question" items="${survey.lsQuestion}">
											<a href="#" data-toggle="modal"
												data-target=".modal-modify-question${question.id}"
												class="list-group-item">${question.wording} </a>
											<div
												class="modal fade modal-modify-question${question.id} text-left"
												tabindex="-1" role="dialog"
												aria-labelledby="mySmallModalLabel${question.id}">
												<div class="modal-dialog" role="document">
													<div class="modal-content">
														<div class="modal-header">
															<button type="button" class="close" data-dismiss="modal"
																aria-label="Close">
																<span aria-hidden="true">&times;</span>
															</button>
															<h4 class="modal-title">Modifier une question</h4>
														</div>
														<div class="modal-body">
															<%@ include file="modifieQuestion.jsp"%>
														</div>
														<div class="modal-footer">
															<button type="button" class="btn btn-primary"
																data-dismiss="modal">Annuler</button>
														</div>
													</div>
													<!-- /.modal-content -->
												</div>
												<!-- /.modal-dialog -->
											</div>
											<!-- /.modal -->
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
