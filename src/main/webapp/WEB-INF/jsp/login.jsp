<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<title>Login</title>
<link rel="stylesheet" href="/resources/css/style.css">
<jsp:include page="/WEB-INF/jsp/header.jsp"/>
<div class="page">
	<form action="/login" method="post">
		<input type="text"  name="email" placeholder="Email">
		<input type="password" name="password" placeholder="Password">
		<input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
		<input type="submit" value="Login">
	</form>
</div>