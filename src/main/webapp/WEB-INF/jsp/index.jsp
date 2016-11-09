<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<div>
	<div class="logo">
	</div>
	<h1>Simplon.co Reservations</h1>
	<security:authorize access="isAuthenticated()">
		Logged in as: 
		<security:authentication property="principal.username" />
	</security:authorize>
    <div class="menu">
    	<ul class="nav nav-tabs nav-justified">
    	</ul>
    </div>
	<security:authorize access="!isAuthenticated()">
		<a href="/login">Login</a>
		<a href="/users">Sign Up</a>
	</security:authorize>
	<security:authorize access="isAuthenticated()">
		<a href="/logout">Logout</a>
	</security:authorize>
</div>