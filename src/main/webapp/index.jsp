<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
	<a href="ajouteEnquete">Ajouter une Enquete</a>
	<br>
	<p>Nombre total d'enquete(s): ${enquetes.size()}</p>
</body>
</html>
