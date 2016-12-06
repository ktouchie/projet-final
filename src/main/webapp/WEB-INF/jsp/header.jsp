<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

	<div class="top">
		<img id="logo" src="../../resources/images/simplonlogo.png" alt="Simplon Logo">
		<h1 id="title">RESERVATIONS</h1>
	</div>
	
	<div>
	<security:authorize access="isAuthenticated()">
		Logged in as: 
		<security:authentication property="principal.username" />
		<security:authorize access="hasAuthority('ADMIN')">
			(ADMIN)
		</security:authorize>
	</security:authorize>
	</div>
    <div class="menu">
    	<ul class="nav nav-tabs nav-left">
    		<security:authorize access="isAuthenticated()">
			<security:authorize access="hasAuthority('ADMIN')">
	    		<li><a class="button" href="/users">Users</a></li>
	    		<li><a class="button" href="/rooms">Rooms</a></li>
	    		<li>
	    			<a class="button" href="/adminInbox"><c:if test="${alertMailOn}">&#8226;</c:if> Messages<c:if test="${alertMailOn}"> &#8226;</c:if></a>
	    		</li>
	    	</security:authorize>
	    	</security:authorize>
	    	<security:authorize access="isAuthenticated()">
	    		<security:authorize access="hasAuthority('USER')">
	    			<li> 
	    				<a class="button" href="/userInbox" ><c:if test="${alertMailOn}">&#8226;</c:if> Support<c:if test="${alertMailOn}"> &#8226; </c:if></a>
		    		</li>
	    		</security:authorize>	
				<li><a class="button" href="/reservations">Reservations</a></li>
	    		<li><a class="button" href="/password">Change Password</a></li>
	    	</security:authorize>
				<li><a class="button" href="/tiles/planning">Planning</a></li>
		</ul>
	</div>
	<security:authorize access="!isAuthenticated()">
		<a href="/login">Login</a>
		<a href="/users">Sign Up</a>
	</security:authorize>
	<security:authorize access="isAuthenticated()">
		<a href="/logout">Logout</a>
	</security:authorize>
