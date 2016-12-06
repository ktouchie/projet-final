<html>
<head>
	<title>Change Password</title>
	<link rel="stylesheet" href="/resources/css/style.css">
</head>

<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>
<div class="page">
	<form action="/changePassword">
		Current Password:<input name="currentPasswordInput" type="password">
		New Password:<input name="newPassword" type="password">
		Confirm New Password:<input name="confirmPassword" type="password">
		<input type="submit" value="Submit">
	</form>
	<div>${success}</div>
	<div>${error}</div>
</div>