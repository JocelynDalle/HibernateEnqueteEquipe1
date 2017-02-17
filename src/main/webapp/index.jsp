<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Liste des Enquetes</title>
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
	<a href="AddSurveyServlet?type=1">Ajouter une Enquete téléphonique</a>
	<br><a href="AddSurveyServlet?type=0">Ajouter une Enquête internet</a>
	<br>
	<p>Nombre total d'enquete(s): ${enquetes.size()}</p>
</body>
</html>
