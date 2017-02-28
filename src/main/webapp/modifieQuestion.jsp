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
			<input name="idSurvey" type="hidden" value="${survey.id}">
			<label>Libellé de la question</label> <br>
			<textarea class="form-control" rows="3" name="wording" required>${question.wording}</textarea>
			<button type="submit" name="typeAction" class="btn btn-small btn-danger" value="update">Modifier</button>
			<button type="button" class="btn btn-small btn-danger"
												data-toggle="modal"
												data-target=".modal-delete-question">
												Supprimer
											</button>
		</form>
		
		<div class="modal fade modal-delete-question text-left"
						tabindex="-1" role="dialog"
						aria-labelledby="mySmallModalLabel">
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
									<p>Voulez-vous vraiment supprimer <strong>'${question.wording}'</strong> ?</p>
								</div>
								<div class="modal-footer">
									<form action="QuestionUpdateServlet" method="post">
										<button type="submit" class="btn btn-default"
											name="typeAction" value="delete">Supprimer</button>
										<input name="idQuestion" type="hidden" value="${question.id}">
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
	</div>
</body>
</html>
