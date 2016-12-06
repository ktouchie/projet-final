<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<div class="header">
	<div class="top">
		<img src="/resources/images/simplonlogo.png" width="25%"/>
		<h1 id="title"> Reservations</h1>
		<div id="login-links">
			<security:authorize access="!isAuthenticated()">
				<c:if test="${isLoginPage}">
					<a class="button login" href="/">&#9664; Back</a>
				</c:if>
				<c:if test="${not isLoginPage}">
					<a class="button login" href="/login">Login</a>
				</c:if>
				<a class="button login" href="/users">Sign Up</a>
			</security:authorize>
			<security:authorize access="isAuthenticated()">
				<a class="button login" href="/logout">Logout</a>
			</security:authorize>
		</div>
		<div id="principal">
		<security:authorize access="isAuthenticated()">
			<security:authentication property="principal.username" />
			<security:authorize access="hasAuthority('ADMIN')">
				(ADMIN)
			</security:authorize>
		</security:authorize>
		</div>


	</div>
    <div class="navbar clearfix">
    	<security:authorize access="isAuthenticated()">
			<security:authorize access="hasAuthority('ADMIN')">
	    		<a class="button nav" href="/users">Users</a>
	    		<a class="button nav" href="/rooms">Rooms</a>
	    		<a class="button nav" href="/adminInbox">Messages<c:if test="${alertMailOn}"> !!! </c:if></a>
	    	</security:authorize>
	    </security:authorize>
	    <security:authorize access="isAuthenticated()">
    		<security:authorize access="hasAuthority('USER')">
    			 <a class="button nav" href="/userInbox" >Support<c:if test="${alertMailOn}"> !!! </c:if></a>
	    	</security:authorize>	
			<a class="button nav" href="/reservations">Reservations</a>
    		<a class="button nav" id="password" href="/password">Change Password</a>
    	</security:authorize>
		<a class="button nav" href="/planning">Planning</a>
		<div class="navtitle">
			<c:if test="${isLoginPage}">LOGIN</c:if>
			<c:if test="${isComputersPage}">MANAGE COMPUTERS</c:if>
			<c:if test="${isMessageInbox}">MESSAGES INBOX</c:if>
			<c:if test="${isMessageOutbox}">MESSAGES OUTBOX</c:if>
			<c:if test="${isContactPage}">CONTACT US</c:if>
			<c:if test="${isMessagePage}">MESSAGE</c:if>
			<security:authorize access="hasAuthority('USER')">
				<c:if test="${isReservationsPage}">MY RESERVATIONS</c:if>
			</security:authorize>
			<security:authorize access="hasAuthority('ADMIN')">
				<c:if test="${isReservationsPage}">MANAGE RESERVATIONS</c:if>
			</security:authorize>
			<c:if test="${isPlanningPage}">RESERVATIONS AT A GLANCE</c:if>
			<c:if test="${isRoomsPage}">MANAGE ROOMS</c:if>
			<c:if test="${isPasswordPage}">CHANGE PASSWORD</c:if>
			<c:if test="${isUsersPage}">MANAGE USERS</c:if>
		</div>	
	</div>
</div>