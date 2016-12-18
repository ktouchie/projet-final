<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
	<c:set var="url">${pageContext.request.requestURL}</c:set>
	<base href="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}/" />
	<title>Change Password</title>
	<link rel="stylesheet" href="resources/css/style.css">
	<script>var base = document.getElementsByTagName("base")[0].href;</script>
</head>

<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>
<div class="page">
	<form action="changePassword">
		<table class="form">
			<tr>
				<td>Current Password:</td>
				<td><input name="currentPasswordInput" type="password"></td>
				<td>${currentError}</td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td>New Password:</td>
				<td><input name="newPassword" type="password"></td>
				<td>
					<c:forEach items="${pwdErrors}" var="pwdError">
						${pwdError}. 
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td>Confirm New Password:</td>
				<td><input name="confirmPassword" type="password"></td>
				<td>${confirmationError}</td>
			</tr>
			<tr>
				<td></td>
				<td class="right"><input class="button submit" type="submit" value="Submit"></td>
				<td></td>
			</tr>
		</table>
	</form>
	<div>${success}</div>
</div>