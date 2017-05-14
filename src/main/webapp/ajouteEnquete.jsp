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
<title>Ajouter des Enquetes</title>
</head>
<body>
	<h1>Liste des Enquetes</h1>
	<br>
	<c:forEach var="enquete" items="${enquetes}">
		<h2>${enquete.nom}</h2>
		<div>Plannifi&eacute;e le: ${enquete.dateEnqueteString}</div>
	</c:forEach>
	<br>
	<br>
	<a href="AddSurveyServlet?type=1">Ajouter une Enquete téléphonique</a><a href="AddSurveyServlet?type=0">Ajouter une Enquete </a>
	<br>
	<p>Nombre total d'enquete(s): ${enquetes.size()}</p>
</body>
</html>
