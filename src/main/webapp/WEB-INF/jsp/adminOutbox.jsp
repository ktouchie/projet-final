<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<title>Messages Outbox</title>
	<link rel="stylesheet" href="/resources/css/style.css">
</head>

<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>
   	<p>Replied Requests :</p>
 		<table>
 			<tr>
 				<th><div></div></th>
 				<th>Topic</th>
 				<th>From</th>
 				<th>At</th>
 				<th></th>
 				<th></th>
 			</tr>
 			
			<c:forEach items="${adminMessageRepliedList}" var="message">
			<tr>
				<td><div id="logoReadMail"></</div></td>
				<td>${message.title}</td>
				<td>${message.user.name} ${message.user.surname}</td>
				<td>${message.creationDate}</td>
				<td>
					<form action="readMail">
						<input name="messageId" value="${message.id}" type="hidden" />
						<input name="mailBoxSource" value="adminOutbox" type="hidden" />
						<input type="submit" value="Read" />
					</form>
				</td>
				<td>
					<form action="disableThread">
						<input name="messageId" value="${message.id}" type="hidden" />
						<input name="mailBoxSource" value="adminOutbox" type="hidden" />
						<input type="submit" value="Delete" />
					</form>
				</td>
			</tr>
	      	</c:forEach>
	      	
		</table>
		
	<br/>
	<br/>
	<a href="/adminInbox">Inbox</a>
</body>
</html>