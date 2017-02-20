<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="fr">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<script src="js/jquery-3.1.1.min.js">
</script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>
<title>Modifier une question</title>
</head>
<body>
	<div class="container-fluid">
		<h1>Modifier question</h1>
		<h3>Pour l'enquête ${survey.name}</h3>
		<form action="QuestionUpdateServlet"
			method="post">
			<input name="idQuestion" type="hidden" value="${question.id}">
			<label>Libellé de la question</label> <br>
			<textarea class="form-control" rows="3" name="wording" required>${question.wording}</textarea>
			<button type="submit">Modifier</button>

		</form>
	</div>
</body>
</html>
