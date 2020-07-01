<%@page import="org.springframework.web.servlet.support.RequestContextUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE HTML>
<html lang="fr">
	<head>
		<meta charset="utf-8">
		<title>Spring security : Login</title>
	</head>
	
	<body>
		<h1>Spring Security 4+</h1>
		locale = <%= RequestContextUtils.getLocale(request) %>,
		locale (response) = <%= response.getLocale() %>
		<c:if test="${!empty param.error}">
			<p>Problème d'authentification. </p>
			<c:out value="${SPRING_SECURITY_LAST_EXCEPTION}"/>
			<p>Revenir sur <a href="<c:url value="/login.jsp"/>">Login</a></p>
		</c:if>
			
		<c:if test="${empty param.error}">
			<form action="<c:url value="/login"/>" method="post">
				<table style="width:100%;">
					<tr>
						<td><spring:message code="login.label.name" /></td>
						<td><input type="text" size="20" name="username" /></td>
					</tr>
					<tr>
						<td>Mot de passe</td>
						<td><input type="password" size="20" name="password" /></td>
					</tr>
					<tr>
						<td>Se souvenir de moi</td>
						<td><input type="checkbox" name="remember-me" /></td>
					</tr>						
					<tr>
						<td colspan="2"><input type="submit" value="Go" /></td>
					</tr>				
				</table>
			</form>
		</c:if>
	</body>
</html>
