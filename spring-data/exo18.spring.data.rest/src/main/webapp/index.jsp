<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- On declare le fait que l'on va utiliser la taglib JSTL --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"/>
<title>Page d'exemple Spring en Web Services</title>
</head>
<body>
	<h2>Exo Spring Data Rest</h2>

	<h2>Lister Comptes</h2>
	<ul>
		<h3>findOne</h3>
		<li>
			<c:url value="/rest/comptes/12" var="url03"/>
			<a href="${url03}">
				<c:out value="${pageContext.request.scheme}" />://<c:out value="${pageContext.request.serverName}" />:<c:out value="${pageContext.request.serverPort}" />${url03}
			</a>
		</li>
		<h3>findAllBelongToUserId</h3>	
		<li>
			<c:url value="/rest/comptes/search/findAllBelongToUserId" var="url04">
				<c:param name="aUserId" value="1"/>
			</c:url>			
			<a href="${url04}">
				<c:out value="${pageContext.request.scheme}" />://<c:out value="${pageContext.request.serverName}" />:<c:out value="${pageContext.request.serverPort}" />${url04}
			</a>
		</li>
		<li>
			<c:url value="/rest/comptes/search/findAllBelongToUserId" var="url05" />
			<form action="${url05}" method="get">
				Id User: <input type="text" name="aUserId" /><br /> 
				<input type="submit" value="Go" />
			</form>
		</li>
	</ul>
	
	
	<h2>Lister Operations</h2>
	<ul>
		<li>
			<c:url value="/rest/operations/search/findAllBelongToCompteId" var="url06">
				<c:param name="aCptId" value="15"/>
			</c:url>			
			<a href="${url06}">
				<c:out value="${pageContext.request.scheme}" />://<c:out value="${pageContext.request.serverName}" />:<c:out value="${pageContext.request.serverPort}" />${url06}
			</a>
		</li>
		<li>
			<c:url value="/rest/operations/search/selectCriteria" var="url07" />
			<form action="${url07}" method="get">
				Compte Id: <input type="text" name="unCompteId" value="15"/><br /> 
				Date Debut (yyyy/MM/dd): <input type="text" name="unDebut" value="2015-02-10" /><br /> 
				Date Fin (yyyy/MM/dd): <input type="text" name="uneFin" value="2015-02-15"/><br /> 
				<input type="submit" value="Go" />
			</form>
		</li>
	</ul>
</body>
</html>