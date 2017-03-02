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
		<h3>${survey.name}</h3>
		<h4>
			<p>
				<label>Sites partenaires :</label>
				<c:forEach var="partner" items="${survey.lsPartnerSite}">
					<a href="${partner.url}"><button type="button"
							class="btn btn-warning">${partner.name}</button></a>
				</c:forEach>
			</p>
			<p>
			<label for="#drop">Ajouter un site partenaire à cette
					enquête :</label>
			<form action="PartnerSiteServlet" method="post" class="form-inline">
				 <select name="idPartner">
					<c:forEach var="partner" items="${partners}">
						<option value="${partner.id}"><span class="label label-info">${partner.name}</span>
							${partner.url}
						</option>
					</c:forEach>
				</select>
				<button type="submit" name="idSurvey" value="${survey.id}" class="btn btn-default">
					Ajouter
				</button>
			</form>
			</p>
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
						<h4 class="modal-title">Créer un nouveau site partenaire</h4>
					</div>
					<div class="modal-body">
						<form action="AddPartnerServlet" method="post">
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
								<button type="submit" name="idSurvey"
									value="${survey.id} class="btnbtn-default">Ajouter</button>
							</div>
						</form>
					</div>
					<div class="modal-footer">
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