<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
	<title>Messages Outbox</title>
	<link rel="stylesheet" href="/resources/css/style.css">
</head>

<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>
<div class="page">
	<c:if test="${not empty messageFromUserList}">
   	<p class="heading">SENT REQUESTS</p>
 		<table class="message">
 			<tr>
 				<th><div></div></th>
				<th class="w200">From</th>
				<th class="w250">Subject</th>
				<th class="w150">Date</th>
				<th class="w80"></th>
				<th class="w80"></th>
 			</tr>
 			
			<c:forEach items="${messageFromUserList}" var="message">
			<tr>
				<td><div id="logoReadMail"></div></td>
				<td>${message.user.name} ${message.user.surname}</td>
				<td>${message.title}</td>
				<td><fmt:formatDate type="both" timeStyle="short" dateStyle="short" value="${message.creationDate}"/></td>
				<td>
					<form action="readMail">
						<input name="messageId" value="${message.id}" type="hidden" />
						<input name="mailBoxSource" value="userOutbox" type="hidden" />
						<input class="button" type="submit" value="Read" />
					</form>
				</td>
				<td>
					<form action="disableThread">
						<input name="messageId" value="${message.id}" type="hidden" />
						<input name="mailBoxSource" value="userOutbox" type="hidden" />
						<input class="button" type="submit" value="Delete" />
					</form>
				</td>
			</tr>
	      	</c:forEach>
	      	
		</table>
	</c:if>
	<br/>
	<br/>
	<a class="button login" href="/userInbox">Inbox</a>
</div>
</body>
</html>