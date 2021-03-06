<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" buffer="128kb"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>NET*Banque</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href='<c:url value="css/banque.css"/>' rel="stylesheet" type="text/css">
</head>

<body class="elbody">
	<form:form action="dologin.smvc" method="post" modelAttribute="loginBean">
		<table width="100%">
			<tr>
				<td align="right" valign="top"><img src="<c:url value="images/titre.jpg"/>" alt="" /></td>
				<td rowspan="2"><img src="<c:url value="images/image-femme.jpg"/>" alt="" /></td>
			</tr>
			<tr>
				<td>
					<table width="60%" border="1" align="center" bgcolor="lightgray">
						<tr>
							<td align="center">
								<table>
									<tr>
										<td colspan="2" align="left"><spring:hasBindErrors name="loginBean">
												<%-- Autre technique mais ne fonctionne pas avec les trous
													Fonctionne avec le bean ResourceBundleMessageSource 
													<ul>
														<c:forEach items="${errors.allErrors}" var="erreur">
															<li><spring:message code="${erreur.code}" /></li>
														</c:forEach>
													</ul>
												--%>
												<form:errors path="*"/>	
											</spring:hasBindErrors>
											<p class="eltitre1">Veuillez entrer votre nom d'utilisateur et votre mot de passe</p>
											<p>&nbsp;</p></td>
									</tr>
									<tr>
										<td class="ellibelle1">Nom d'utilisateur</td>
										<td><form:input path="login" /></td>
									</tr>
									<tr>
										<td colspan="2">&nbsp;</td>
									</tr>
									<tr>
										<td class="ellibelle1">Mot de passe</td>
										<td><form:password path="password" size="20" /></td>
									</tr>
									<tr>
										<td colspan="2" align="center">
											<p>&nbsp;</p> <a href="javascript:loginBean.submit()"> <img src="<c:url value="images/bouton-validez.gif"/>" width="98" height="33" border="0" alt="" />
										</a>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form:form>

</body>
</html>
