<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<body>
<div>
	<div class="logo">
	</div>
	<h1>Simplon.co Reservations</h1>
	<security:authorize access="isAuthenticated()">
		Logged in as: 
		<security:authentication property="principal.username" />
		<security:authorize access="hasAuthority('ADMIN')">
			(ADMIN)
		</security:authorize>
	</security:authorize>
    <div class="menu">
    	<ul class="nav nav-tabs nav-justified">
			<security:authorize access="hasAuthority('ADMIN')">
	    		<li><a href="/users">Users</a></li>
	    		<li><a href="/rooms">Rooms</a></li>
	    	</security:authorize>
	    	<security:authorize access="isAuthenticated()">
				<li><a href="/reservations">Reservations</a></li>
	    		<li><a href="/password">Change Password</a></li>
	    	</security:authorize>
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
</body>