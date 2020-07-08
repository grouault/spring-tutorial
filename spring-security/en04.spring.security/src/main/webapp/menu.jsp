<%@page import="org.springframework.web.servlet.i18n.SessionLocaleResolver"%>
<%@page import="java.util.Locale"%>
<%@page import="org.springframework.web.servlet.support.RequestContextUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE HTML>
<html lang="fr">
	<head>
		<meta charset="utf-8">
		<title>Spring security : Menu</title>
	</head>
	
	<body>
		<h1>Bonjour <sec:authentication property="principal.username"/></h1>
		locale = <%= RequestContextUtils.getLocale(request) %>,
		locale (response) = <%= response.getLocale() %><%
		// Locale locale = new Locale("en","US");
		// response.setLocale(locale);
		%>
		locale2 = <%= request.getSession().getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME) %>
		<table style="width:100%;">
			<tr>
				<sec:authorize access="hasRole('ROLE_USER')">
				<td><a href="<c:url value="/user/pageA.jsp"/>">Page A : role user</a></td>
				</sec:authorize>
				<sec:authorize access="hasRole('ROLE_ADMIN')">
				<td><a href="<c:url value="/admin.smvc"/>">Page B : role admin</a></td>
				</sec:authorize>				
				<td>&nbsp;</td>
				<sec:authorize access="isFullyAuthenticated()">
				<td><a href="<c:url value="/logout"/>"><spring:message code="logout"  />-test</a></td>
				</sec:authorize>
				
				<%-- Ne pas gerer les droits a ce niveau, annoter les services metiers --%>
				<td><a href="<c:url value="/rest/user"/>">Click User</a></td>
				<td><a href="<c:url value="/rest/admin"/>">Click Admin</a></td>

			</tr>
		</table>
	</body>
</html>
