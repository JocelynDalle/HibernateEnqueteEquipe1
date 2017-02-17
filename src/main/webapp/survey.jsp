<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>    
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Enquête</title>
	</head>
	<body>
		<h1>Enquête</h1>
		<form method="POST" action="SurveyServlet">
			<input type=hidden name="id" value="${survey.id}">
			<label>Nom  :
				<input type="text" name="name" placeholder="Entrez le nom de l'enquête" value="${survey.name}" required>
			</label><br>
			<label> Prix :
				<input type="text" name="price" placeholder="Entrez le prix de l'enquête" value="${survey.price}" required>
			</label><br>
			<label> Date :
				<input type="date" name="date" required>
			</label><br>
			<c:if test="${typeSurvey eq 'surveyPhone'}">
				<label>Script :<br>
					 <textarea rows="4" cols="50">${survey.script}</textarea> 
				</label><br>
			</c:if>
			<c:if test="${typeSurvey eq 'surveyInternet'}">
				<label>Sites partenaires :
					<select>
						<c:forEach items="${partnerSites}" var="partnerSite">
							<option>heho</option>
							<option value="${partnerSite.id}">${partnerSite.name}</option>
						</c:forEach>					
					</select>
				</label>
			</c:if>
			<c:if test="${typeAction eq 'create'}">
				<input type="submit" name="create" value="Ajouter l'enquête">
			</c:if>
			<c:if test="${typeAction eq 'update'}">
				<input type="submit" name="update" value="Modifier l'enquête">
			</c:if>
		</form>		
	</body>
</html>