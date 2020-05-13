<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML>
<html lang="fr">
	<head>
		<meta charset="utf-8">
		<title>Spring security : Login</title>
	</head>
	
	<body>

		<c:if test="${!empty param.erreur}">
			<p>Problème d'authentification. </p>
			<c:out value="${SPRING_SECURITY_LAST_EXCEPTION}"/>
		</c:if>
		
		
		<form action="<c:url value="/login"/>" method="post">
			<table style="width:100%;">
				<tr>
					<td>Nom d'utilisateur</td>
					<td><input type="text" size="20" name="username" /></td>
				</tr>
				<tr>
					<td>Mot de passe</td>
					<td><input type="password" size="20" name="password" /></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="Go" /></td>
				</tr>
			</table>
		</form>
	</body>
</html>
