<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<title>Send out Messages</title>
</head>

<body>

   	<p>Requests History :</p>
 		<table>
 			<tr>
 				<th><div></div></th>
 				<th>Topic</th>
 				<th>From</th>
 				<th>At</th>
 				<th></th>
 				<th></th>
 			</tr>
 			
			<c:forEach items="${messageFromUserList}" var="message">
			<tr>
				<td><div id="logoReadMail"></</div></td>
				<td>${message.title}</td>
				<td>${message.user.name} ${message.user.surname}</td>
				<td>${message.creationDate}</td>
				<td>
					<form action="readMail">
						<input name="messageId" value="${message.id}" type="hidden" />
						<input name="mailBoxSource" value="userOutbox" type="hidden" />
						<input type="submit" value="Read" />
					</form>
				</td>
				<td>
					<form action="disableThread">
						<input name="messageId" value="${message.id}" type="hidden" />
						<input name="mailBoxSource" value="userOutbox" type="hidden" />
						<input type="submit" value="Delete" />
					</form>
				</td>
			</tr>
	      	</c:forEach>
	      	
		</table>
	
	<br/>
	<br/>
	<a href="/userInbox">Inbox</a>

</body>
</html>