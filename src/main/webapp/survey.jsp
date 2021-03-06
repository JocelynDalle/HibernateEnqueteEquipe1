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
<title>Enquête</title>
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
			<li class="active">Enquête</li>
		</ol>

		<!-- début code -->

		<h1>Enquête</h1>
		<form method="POST" action="SurveyServlet">
			<input type=hidden name="id" value="${survey.id}"> <label>Nom
				: <input type="text" name="name"
				placeholder="Entrez le nom de l'enquête" value="${survey.name}"
				required class="form-control">
			</label><br> <label> Prix : <input type="number" name="price"
				class="form-control" placeholder="Entrez le prix de l'enquête"
				value="${survey.price}" required>
			</label><br> <label> Date : <input type="date" name="date"
				value="${survey.formatDate}" required class="form-control">
			</label><br>
			<c:if test="${typeSurvey eq 'surveyPhone'}">
				<label>Script :<br> <textarea name="script" rows="4"
						cols="50" class="form-control">${survey.script}</textarea>
				</label>
				<br>
			</c:if>
			<c:if test="${typeSurvey eq 'surveyInternet'}">
				<label>Sites partenaires : <select name="idsPartnerSite"
					class="form-control">
						<c:forEach var="partner" items="${partners}">
							<option value="${partner.id}">${partner.name}</option>
						</c:forEach>

				</select>
				</label>
			</c:if>
			<c:if test="${typeAction eq 'create'}">
				<input type="submit" name="create" value="Ajouter l'enquête"
					class="btn btn-default">
			</c:if>
			<c:if test="${typeAction eq 'update'}">
				<input type="submit" name="update" value="Modifier l'enquête"
					class="btn btn-default">
			</c:if>
		</form>

		<!-- fin code -->

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