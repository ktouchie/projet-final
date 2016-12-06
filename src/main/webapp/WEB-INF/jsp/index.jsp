<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<head>
<title>Simplon.co Reservations</title>
<link rel="stylesheet" href="/resources/css/style.css">
</head>
<body>
	<div class="top">
	<img src="/resources/images/simplonlogo.png" width="25%"/>
	<h1 id="title"> Reservations</h1>
	</div>
	<security:authorize access="isAuthenticated()">
		Logged in as: 
		<security:authentication property="principal.username" />
		<security:authorize access="hasAuthority('ADMIN')">
			(ADMIN)
		</security:authorize>
	</security:authorize>
    <div class="menu">
    	<ul class="nav nav-tabs nav-justified">
    		<security:authorize access="isAuthenticated()">
			<security:authorize access="hasAuthority('ADMIN')">
	    		<li><a class="button" href="/users">Users</a></li>
	    		<li><a class="button" href="/rooms">Rooms</a></li>
	    		<li>
	    			<a class="button" href="/adminInbox">Messages<c:if test="${alertMailOn}"> !!! </c:if></a>
	    		</li>
	    	</security:authorize>
	    	</security:authorize>
	    	<security:authorize access="isAuthenticated()">
	    		<security:authorize access="hasAuthority('USER')">
	    			<li> 
	    				<a class="button" href="/userInbox" >Support<c:if test="${alertMailOn}"> !!! </c:if></a>
		    		</li>
	    		</security:authorize>	
				<li><a class="button" href="/reservations">Reservations</a></li>
	    		<li><a class="button" href="/password">Change Password</a></li>
	    	</security:authorize>
				<li><a class="button" href="/planning">Planning</a></li>
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