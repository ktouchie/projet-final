<div>
	<form action="/changePassword">
		Current Password:<input name="currentPasswordInput" type="password">
		New Password:<input name="newPassword" type="password">
		Confirm New Password:<input name="confirmPassword" type="password">
		<input type="submit" value="Submit">
	</form>
	<div>${success}</div>
	<div>${error}</div>
</div>