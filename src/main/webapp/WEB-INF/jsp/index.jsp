<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<head>
	<c:set var="url">${pageContext.request.requestURL}</c:set>
	<base href="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}/" />
	<title>Simplon.co Reservations</title>
	<link rel="stylesheet" href="/resources/css/style.css">
	<script>var base = document.getElementsByTagName("base")[0].href;</script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>
</body>