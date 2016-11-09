<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<security:authorize access="!isAuthenticated()">
	<a href="/login">Login</a>
	<a href="/users">Sign Up</a>
</security:authorize>
<security:authorize access="isAuthenticated()">
	<a href="/logout">Logout</a>
</security:authorize>