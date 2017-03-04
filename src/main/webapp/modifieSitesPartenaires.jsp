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
<title>Modifier la liste des sites partenaires</title>
</head>
<body>
	<div class="container">
		<ol class="breadcrumb">
			<li><a href="index">Enquêtes</a></li>
			<li class="active">Sites partenaires</li>
		</ol>
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
							<td><a href="${partner.url}"><span class="label label-success">${partner.name}</span></a></td>
							<td><a href="${partner.url}">${partner.url}</a></td>
							<td><button type="button" class="btn btn-xs btn-default"
									data-toggle="modal"
									data-target=".modal-delete-partner${partner.id}">
									<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
								</button></td>
						</tr>
						
						<div class="modal fade modal-delete-partner${partner.id} text-left"
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
										<p>Voulez-vous vraiment supprimer <span class="label label-success">${partner.name}</span> de cette enquête?</p>
									</div>
									<div class="modal-footer">
										<form action="#" method="post">
											<button type="submit" class="btn btn-default"
												name="typeAction" value="delete">Supprimer</button>
											<input type="hidden" name="idSurvey" value="${survey.id}">
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
					<select class="form-control" name="idPartner">
						<c:forEach var="partner" items="${partners}">
							<option value="${partner.id}">${partner.name}&nbsp;:
								${partner.url}</option>
						</c:forEach>
					</select>
					<button type="submit" name="idSurvey" value="${survey.id}"
						class="btn btn-default">Ajouter</button>
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
						</form>
					</div>
					<div class="modal-footer">
						<button form="newPS" type="submit" name="idSurvey"
							value="${survey.id}" class="btn btn-default">Créer</button>
						<button type="button" class="btn btn-primary" data-dismiss="modal">Annuler</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		<!-- /.modal -->

	</div>
</body>
</html>