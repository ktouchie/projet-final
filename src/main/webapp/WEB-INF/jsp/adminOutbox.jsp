<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
	<c:set var="url">${pageContext.request.requestURL}</c:set>
	<base href="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}/" />
	<title>Messages Outbox</title>
	<link rel="stylesheet" href="resources/css/style.css">
	<script>var base = document.getElementsByTagName("base")[0].href;</script>
</head>

<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>
<div class="page">
	<c:if test="${not empty adminMessageRepliedList}">
   	<p class="heading">REPLIES</p>
 		<table class="message">
 			<tr>
 				<th><div></div></th>
				<th class="w200">From</th>
				<th class="w250">Subject</th>
				<th class="w150">Date</th>
				<th class="w80"></th>
				<th class="w80"></th>
 			</tr>
 			
			<c:forEach items="${adminMessageRepliedList}" var="message">
			<tr>
				<td><div id="logoReadMail"></div></td>
				<td>${message.user.name} ${message.user.surname}</td>
				<td>${message.title}</td>
				<td><fmt:formatDate type="both" timeStyle="short" dateStyle="short" value="${uMessage.creationDate}"/></td>
				<td>
					<form action="readMail">
						<input name="messageId" value="${message.id}" type="hidden" />
						<input name="mailBoxSource" value="adminOutbox" type="hidden" />
						<input class="button" type="submit" value="Read" />
					</form>
				</td>
				<td>
					<form action="disableThread">
						<input name="messageId" value="${message.id}" type="hidden" />
						<input name="mailBoxSource" value="adminOutbox" type="hidden" />
						<input class="button" type="submit" value="Delete" />
					</form>
				</td>
			</tr>
	      	</c:forEach>
	      	
		</table>
	</c:if>	
	<br/>
	<br/>
	<a class="button login" href="adminInbox">Inbox</a>
</div>
</body>
</html>