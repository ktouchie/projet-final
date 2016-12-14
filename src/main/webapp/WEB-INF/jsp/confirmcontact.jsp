<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
	<c:set var="url">${pageContext.request.requestURL}</c:set>
	<base href="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}/" />
	<title>Message Sent!</title>
	<link rel="stylesheet" href="/resources/css/style.css">
	<script>var base = document.getElementsByTagName("base")[0].href;</script>
</head>

<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>
<div class="page">
	<h3>Your request has been recorded correctly!</h3>
	<p>We will knock at you through our windows when we get answer</p>
	<br/>
</div>
</body>
</html>