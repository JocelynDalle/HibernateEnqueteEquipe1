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
<title>Modifier une question</title>
</head>
<body>
	<div class="container">
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
			<li class="active">Modifier question</li>
		</ol>
		<h1>Modifier question</h1>
		<h3>Pour l'enquête ${survey.name}</h3>
		<form action="QuestionUpdateServlet" method="post">
			<input name="idQuestion" type="hidden" value="${question.id}">
			<input name="idSurvey" type="hidden" value="${survey.id}"> <label>Libellé
				de la question</label> <br>
			<textarea class="form-control" rows="3" name="wording" required>${question.wording}</textarea>
			<button type="submit" name="typeAction"
				class="btn btn-sm btn-default" value="update">Modifier</button>
			<button type="button" class="btn btn-sm btn-danger"
				data-toggle="modal" data-target=".modal-delete-question">
				Supprimer</button>
		</form>

		<div class="modal fade modal-delete-question text-left" tabindex="-1"
			role="dialog" aria-labelledby="mySmallModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">Suppression de la question</h4>
					</div>
					<div class="modal-body">
						<p>
							Voulez-vous vraiment supprimer <strong>'${question.wording}'</strong>
							?
						</p>
					</div>
					<div class="modal-footer">
						<form action="QuestionUpdateServlet" method="post">
							<button type="submit" class="btn btn-default" name="typeAction"
								value="delete">Supprimer</button>
							<input name="idQuestion" type="hidden" value="${question.id}">
							<input name="idSurvey" type="hidden" value="${survey.id}">
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
		<footer>
			<br> <br> <br> <br> <br> <br> <br>
			<br> <br> <br> <br> <br> <br> <br>
			<br> <br> <br> <br> <br> <br> <br>
			<br> <br> <br> <br> <br> <br> <br>
			<br> <br> <br> <br> <br> <br> <br>
		</footer>
	</div>
</body>
</html>
