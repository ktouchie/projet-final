<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
	<c:set var="url">${pageContext.request.requestURL}</c:set>
	<base href="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}/" />
	<title>Messages Inbox</title>
	<link rel="stylesheet" href="/resources/css/style.css">
	<script>var base = document.getElementsByTagName("base")[0].href;</script>
</head>

<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>
<div class="page">
	<c:if test="${not empty unopenedMessageList}">
	<p class="heading">NEW MESSAGES</p>
		<table class="message">
			<tr>
				<th><div></div></th>
				<th class="w200">From</th>
				<th class="w250">Subject</th>
				<th class="w150">Date</th>
				<th class="w80"></th>
				<th class="w80"></th>
			</tr>
			
		<c:forEach items="${unopenedMessageList}" var="uMessage">
		<tr>
			<td><div id="logoUnreadMail"></div></td>
			<td>${uMessage.user.name} ${uMessage.user.surname}</td>
			<td>${uMessage.title}</td>
			<td><fmt:formatDate type="both" timeStyle="short" dateStyle="short" value="${uMessage.creationDate}"/></td>
			<td>
				<form action="readMail">
					<input name="messageId" value="${uMessage.id}" type="hidden" />
					<input name="mailBoxSource" value="adminInbox" type="hidden" />
					<input class="button msg" type="submit" value="Read" />
				</form>
			</td>
			<td>
				<form action="disableThread">
					<input name="messageId" value="${uMessage.id}" type="hidden" />
					<input name="mailBoxSource" value="adminInbox" type="hidden" />
					<input class="button msg" type="submit" value="Delete" />
				</form>
			</td>
		</tr>
      	</c:forEach>      	
	</table>
	</c:if>
	<c:if test="${empty unopenedMessageList}">
		<p class="heading">You have no new messages</p>
	</c:if>
	<c:if test="${not empty openedMessageList}">
		<div class="section">
	   	<p class="heading">OLD MESSAGES</p>
			<table>
				<tr>
					<th><div></div></th>
					<th class="w200">From</th>
					<th class="w250">Subject</th>
					<th class="w150">Date</th>
					<th class="w80"></th>
					<th class="w80"></th>
				</tr>
				
			<c:forEach items="${openedMessageList}" var="oMessage">
			<tr>
				<td><div id="logoReadMail"></div></td>
				<td>${oMessage.user.name} ${oMessage.user.surname}</td>
				<td>${oMessage.title}</td>
				<td><fmt:formatDate type="both" timeStyle="short" dateStyle="short" value="${oMessage.creationDate}"/></td>
				<td>
					<form action="readMail">
						<input name="messageId" value="${oMessage.id}" type="hidden" />
						<input name="mailBoxSource" value="adminInbox" type="hidden" />
						<input class="button msg" type="submit" value="Read" />
					</form>
				</td>
				<td>
					<form action="disableThread">
						<input name="messageId" value="${oMessage.id}" type="hidden" />
						<input name="mailBoxSource" value="adminInbox" type="hidden" />
						<input class="button msg" type="submit" value="Delete" />
					</form>
				</td>
			</tr>
	      	</c:forEach>
	      	
		</table>
		</div>
		</c:if>
		<br/>
		<br/>
		<a class="button login" href="/adminOutbox">Outbox</a>
	</div>
</body>
</html>