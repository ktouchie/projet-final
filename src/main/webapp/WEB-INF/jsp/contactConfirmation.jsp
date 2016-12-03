<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
	<title>Request sent confirmation</title>
</head>

<body>
	<h3>Your request has been recorded correctly!</h3>
	<p>We will knock at you through our windows when we get answer</p>
	<br/>
	<a href="/index">Home</a>
	<security:authorize access="isAuthenticated()">
		<a href="/logout">Logout</a>
	</security:authorize>
</body>
</html>