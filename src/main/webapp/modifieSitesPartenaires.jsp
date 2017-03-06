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
<script>
	$(document).ready(function() {
		$("#createBtn").click(function() {
			$("#newPS").submit();
		});
	});
</script>
<title>Modifier la liste des sites partenaires</title>
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
			<li class="active">Sites partenaires</li>
		</ol>

		<c:if test="${message != null}">

			<div class="alert alert-danger alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<strong>Création impossible!</strong> Le nom ou l'url du site existe
				déjà
			</div>

		</c:if>

		<h3>${survey.name}</h3>

		<h5>
			<br>
			<p>
				<label>Sites partenaires associés à cette enquête :</label>
			<table class="table table-condensed">
				<thead>
					<tr>
						<th>Id</th>
						<th>Nom</th>
						<th>Adresse</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="partner" items="${survey.lsPartnerSite}">

						<tr>
							<td>${partner.id}</td>
							<td><a href="${partner.url}"><span
									class="label label-success">${partner.name}</span></a></td>
							<td><a href="${partner.url}">${partner.url}</a></td>
							<td><button type="button" class="btn btn-xs btn-default"
									data-toggle="modal"
									data-target=".modal-delete-partner${partner.id}">
									<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
								</button></td>
						</tr>

						<div
							class="modal fade modal-delete-partner${partner.id} text-left"
							tabindex="-1" role="dialog"
							aria-labelledby="mySmallModalLabel${partner.id}">
							<div class="modal-dialog" role="document">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
										<h4 class="modal-title">${survey.name}</h4>
									</div>
									<div class="modal-body">
										<p>
											Voulez-vous vraiment supprimer <span
												class="label label-success">${partner.name}</span> de cette
											enquête?
										</p>
									</div>
									<div class="modal-footer">
										<form action="AddPartnerServlet" method="post">
											<button type="submit" class="btn btn-default"
												name="typeAction" value="delete">Supprimer</button>
											<input type="hidden" name="idSurvey" value="${survey.id}">
											<input type="hidden" name="idPartner" value="${partner.id}">
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

					</c:forEach>
				</tbody>
			</table>
			</p>

			<br>
			<p>
				<label for="#drop">Ajouter un site partenaire :</label>
			<form action="AddPartnerServlet" method="post" class="form-inline">
				<c:if test="${partners != null}">
					<input type="hidden" name="typeAction" value="add">
					<table class="table table-condensed">
						<thead>
							<tr>
								<th>Id</th>
								<th>Nom</th>
								<th>Adresse</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<input type="hidden" name="idSurvey" value="${survey.id}">
							<c:forEach var="partner" items="${partners}">
								<tr>
									<td>${partner.id}</td>
									<td><a href="${partner.url}"><span
											class="label label-primary">${partner.name}</span></a></td>
									<td><a href="${partner.url}">${partner.url}</a></td>
									<td><button type="submit" name="idPartner"
											value="${partner.id}" class="btn btn-sm btn-default">Ajouter</button></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:if>
				<button type="button" class="btn btn-primary" data-toggle="modal"
					data-target="#modal-addPartner${survey.id}" data-whatever="@mdo">Créer
					un nouveau site partenaire</button>
			</form>
			</p>
		</h5>

		<div class="modal fade text-left" id="modal-addPartner${survey.id}"
			tabindex="-1" role="dialog"
			aria-labelledby="addPartnerSmallModalLabel${survey.id}">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">Créer un nouveau site partenaire</h4>
					</div>
					<div class="modal-body">
						<form id="newPS" action="AddPartnerServlet" method="post">
							<input type="hidden" name="fromModifiePartnerSite" value="1">
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
							</div>
							<input type="hidden" name="idSurvey" value="${survey.id}">
						</form>
					</div>
					<div class="modal-footer">
						<button id="createBtn" form="newPS" type="submit"
							class="btn btn-default">Créer</button>
						<button type="button" class="btn btn-primary" data-dismiss="modal">Annuler</button>
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
			<br> <br>
		</footer>
	</div>
</body>
</html>