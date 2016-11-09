<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form action="/login" method="post">
	<input type="text"  name="email" placeholder="Email">
	<input type="password" name="password" placeholder="Password">
	<input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
	<input type="submit" value="Login">
</form>