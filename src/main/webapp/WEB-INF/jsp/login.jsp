<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<head>
	<c:set var="url">${pageContext.request.requestURL}</c:set>
	<base href="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}/" />
	<title>Login</title>
	<link rel="stylesheet" href="resources/css/style.css">
	<script>var base = document.getElementsByTagName("base")[0].href;</script>
</head>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>
<div class="page">
	<c:if test="${not empty param['error']}">
		<div style="color:red; font-weight:bold">
			<p>Oops! Did you enter the correct Email and Password?</p>
			<p>...because at least one of them ain't right!</p>
		</font>
	</c:if>
	<form action="login" method="post">
		<input type="text"  name="email" placeholder="Email">
		<input type="password" name="password" placeholder="Password">
		<input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
		<input type="submit" value="Login">
	</form>
</div>