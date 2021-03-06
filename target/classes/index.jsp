<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="fr">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="UTF-8">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
<script src="js/jquery-3.1.1.min.js">
	
</script>
<script src="js/bootstrap.min.js">
	
</script>
<script src="js/npm.js">
	
</script>
<c:if test="${newQ != null ? newQ == 1 ? true : false : false}">
	<script>
		function expandQuestions() {
			document.getElementById('survey${idSurvey}Qs').click();
			document.getElementById('gotoSurvey').click();
		}
	</script>
</c:if>
<title>Liste des Enquetes</title>
</head>
<body
	onload="${newQ != null ? newQ == 1 ? 'expandQuestions()' : '' : ''}">
	<div class="container">
		<nav class="navbar navbar-survey navbar-default navbar-fixed-top">
			<div class="container">
				<div class="navbar-header">
					<span class="brand">SurveyMaker</span>
				</div>
				<div class="navbar-left">
					<p class="navbar-text">Nombre total d'enquête(s):
						${surveys.size()}</p>
				</div>
				<p class="navbar-right">
					<a href="SurveyServlet?typeAction=create&typeSurvey=surveyPhone">
						<button type="submit" class="btn btn-primary navbar-btn">
							<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
							Enquête <span class="glyphicon glyphicon-earphone"
								aria-hidden="true"></span>
						</button>
					</a> <a
						href="SurveyServlet?typeAction=create&typeSurvey=surveyInternet">
						<button type="submit" class="btn btn-primary navbar-btn">
							<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
							Enquête <span class="glyphicon glyphicon-phone"
								aria-hidden="true"></span>
						</button>
					</a> <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
				</p>
			</div>
		</nav>

		<header>
			<br> <br> <br> <br>
		</header>
		<ol class="breadcrumb">
			<li><a href="index">Liste des enquêtes</a></li>
		</ol>

		<c:if test="${newQ != null ? newQ == 1 ? true : false : false}">
			<a id="gotoSurvey" href="#survey${idSurvey}"></a>
		</c:if>

		<h5>
			<form action="FilterServlet" method="post"
				class="form-inline text-center">
				<div class="form-group"></div>
				<div class="form-group">
					<label for="nameFilter">Nom</label> <input id="nameFilter"
						type="text" name="nameFilter" class="form-control"
						placeholder="contient le mot">
				</div>
				<div class="form-group">
					<label for="debut"><span
						class="glyphicon glyphicon-calendar" aria-hidden="true"></span>Début</label>
					<input id="debut" type="text" name="startDate" class="form-control"
						placeholder="jj/mm/aaaa">
				</div>
				<div class="form-group">
					<label for="fin"><span class="glyphicon glyphicon-calendar"
						aria-hidden="true"></span>Fin</label> <input id="fin" type="text"
						name="endDate" class="form-control" placeholder="jj/mm/aaaa">
					<button type="submit" class="btn btn-info">Filtrer</button>
				</div>

			</form>
		</h5>
		<h4 class="text-center">
			<c:if test="${nameFilter != null || nameFilter.equals('')}">
				<strong>Nom :</strong>
				<span class="label label-info">'${nameFilter}'</span>&nbsp;&nbsp;&nbsp;
			</c:if>
			<c:if test="${startDate != null || endDate != null}">
				<span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>
				<strong> :</strong>
			</c:if>
			<c:if test="${startDate != null}">
				<span class="glyphicon glyphicon-step-backward" aria-hidden="true"></span>
				<span class="label label-info">${startDate}</span>&nbsp;&nbsp;&nbsp;
			</c:if>
			<c:if test="${endDate != null}">
				<span class="glyphicon glyphicon-step-forward" aria-hidden="true"></span>
				<span class="label label-info">${endDate}</span>
			</c:if>
		</h4>
		<div class="row">
			
			<div class="col-md-12 col-sm-12 col-xs-12">
				<c:forEach var="survey" items="${surveys}">
					<div class="modal fade modal-delete-survey${survey.id} text-left"
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
											value="${survey.getClass().simpleName eq 'SurveyPhone' ? 'surveyPhone' : 'surveyInternet'}">
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
					<div class="panel panel-default panel-survey"
						id="survey${survey.id}">
						<div class="panel-heading">
							<div class="row">
								<div class="col-md-4 text-left">
									<h4>
										<strong>${survey.name}</strong>
									</h4>
									<c:choose>
										<c:when
											test="${survey.getClass().simpleName eq 'SurveyPhone'}">
											<span class="glyphicon glyphicon-earphone" aria-hidden="true"></span>
										</c:when>
										<c:otherwise>
											<span class="glyphicon glyphicon-phone" aria-hidden="true"></span>
										</c:otherwise>
									</c:choose>
									${survey.getClass().simpleName eq 'SurveyPhone' ? "Enquête téléphonique" : "Enquête internet"}
								</div>
								<div class="col-md-4 text-center">
									<em>Plannifi&eacute;e le:</em> <strong>${survey.formatDate}</strong>
								</div>
								<div class="col-md-4 text-right">
									<form action="SurveysServlet" method="post">
										<div class="btn-group" role="group"">
											<button class="btn btn-default" type="button"
												data-container="body" data-toggle="popover"
												data-placement="top"
												data-content="${survey.setCriteria.toString()}">
												Critères <span class="badge">${survey.setCriteria.size()}</span>
											</button>
											<button type="submit" name="typeAction" value="update"
												class="btn btn-warning">
												<span class="glyphicon glyphicon-edit" aria-hidden="true"></span>
											</button>
											<input type="hidden" name="typeSurvey"
												value="${survey.getClass().simpleName eq 'SurveyPhone' ? 'surveyPhone': 'surveyInternet'}">
											<input type="hidden" name="idSurvey" value="${survey.id}">
											<button type="button" class="btn btn-danger"
												data-toggle="modal"
												data-target=".modal-delete-survey${survey.id}">
												<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
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
							<c:if test="${survey.getClass().simpleName eq 'SurveyInternet'}">
								<h4 class="partner">
									<label>Sites partenaires :</label>
									<div class="well text-center">
										<c:forEach var="partner" items="${survey.lsPartnerSite}">
											<a href="${partner.url}" target="blank"><button type="button"
													class="btn btn-success">${partner.name}</button></a>
										</c:forEach>

										<a href="AddPartnerServlet?idSurvey=${survey.id}">
											<button type="button" class="btn btn-primary" name="idSurvey">
												<span class="glyphicon glyphicon-edit" aria-hidden="true"></span>
											</button>
										</a>
									</div>
								</h4>
								<div class="modal fade modal-addPartner${survey.id} text-left"
									tabindex="-1" role="dialog"
									aria-labelledby="addPartnerSmallModalLabel${survey.id}">
									<div class="modal-dialog" role="document">
										<div class="modal-content">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal"
													aria-label="Close">
													<span aria-hidden="true">&times;</span>
												</button>
												<h4 class="modal-title">Ajouter un site partenaire</h4>
											</div>
											<div class="modal-body">
												<h5>${survey.name}</h5>
												<form action="AddPartnerServlet" method="post">
													<div class="form-group">
														<label>Nom : </label><input type="text" name="name"
															class="form-control"
															placeholder="Entrez le nom du site partenaire" required>
													</div>
													<div class="form-group">
														<label> Url : </label><input type="text" name="url"
															class="form-control"
															placeholder="Entrez l'url du site partenaire" required>
														<br>
														<button type="submit" class="btn btn-default">Ajouter</button>
													</div>
												</form>
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

							</c:if>
							<div class="panel-group" role="tablist">
								<div class="panel panel-default panel-question">
									<a
										class="${newQ != null ? newQ == 1 ? idSurvey != null ? idSurvey != survey.id ? 'collapsed' : '' : '' : '' : '' }"
										role="button" data-toggle="collapse" id="survey${survey.id}Qs"
										href="#collapse${survey.id}"
										aria-expanded="${newQ != null ? newQ == 1 ? idSurvey != null ? idSurvey != survey.id ? 'false' : 'true' : 'true' : 'true' : 'true' }"
										aria-controls="collapse${survey.id}">
										<div class="panel-heading  text-center" role="tab"
											id="heading${survey.id}">
											<h4 class="panel-title">
												<span class="glyphicon glyphicon-list" aria-hidden="true"></span>
												Questions
											</h4>
										</div>
									</a>
									<div id="collapse${survey.id}" class="panel-collapse collapse"
										role="tabpanel"
										aria-expanded="${newQ != null ? newQ == 1 ? idSurvey != null ? idSurvey != survey.id ? 'false' : 'true' : 'true' : 'true' : 'true' }"
										aria-labelledby="heading${survey.id}">
										<div class="list-group">
											<c:forEach var="question" items="${survey.lsQuestion}">
												<a
													href="QuestionUpdateServlet?idSurvey=${survey.id}&idQuestion=${question.id}"
													class="list-group-item ${newQ != null ? newQ == 1 ? idNewQ != null ? question.id == idNewQ ? 'active' : '' : '' : '' : ''}">Question
													n°&nbsp;${survey.lsQuestion.indexOf(question) + 1}&nbsp;:&nbsp;${question.wording}
												</a>
											</c:forEach>
											<a href="QuestionAddServlet?idSurvey=${survey.id}"
												class="list-group-item list-group-item-info text-center">Ajouter
												une question</a>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="panel-footer">
							Nombre de questions : <strong>${survey.lsQuestion.size()}</strong>
						</div>
					</div>
				</c:forEach>
			</div>
			
		</div>
		<footer>
			<br> <br> <br> <br> <br> <br> <br>
			<br> <br> <br> <br> <br> <br> <br>
			<br> <br> <br> <br> <br> <br> <br>
			<br> <br> <br> <br> <br> <br> <br>
			<br> <br>
		</footer>
	</div>
</body>
</html>
