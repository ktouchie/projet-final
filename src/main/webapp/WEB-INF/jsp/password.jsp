<html>
<head>
	<title>Change Password</title>
	<link rel="stylesheet" href="/resources/css/style.css">
</head>

<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>
<div class="page">
	<form action="/changePassword">
		<table class="form">
			<tr>
				<td>Current Password:</td>
				<td><input name="currentPasswordInput" type="password"></td>
			</tr>
			<tr>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td>New Password:</td>
				<td><input name="newPassword" type="password"></td>
			</tr>
			<tr>
				<td>Confirm New Password:</td>
				<td><input name="confirmPassword" type="password"></td>
			</tr>
			<tr>
				<td></td>
				<td class="right"><input class="button submit" type="submit" value="Submit"></td>
			</tr>
		</table>
	</form>
	<div>${success}</div>
	<div>${error}</div>
</div>