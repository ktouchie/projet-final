<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<title>Messages</title>
</head>

<body>

   	<p>New Mails :</p>
 		<table>
 			<tr>
 				<th><div></div></th>
 				<th>Topic</th>
 				<th>From</th>
 				<th>At</th>
 				<th></th>
 			</tr>
 			
			<c:forEach items="${unopenedMessageList}" var="uMessage">
			<tr>
				<td><div id="logoUnreadMail"></</div></td>
				<td>${uMessage.title}</td>
				<td>${uMessage.user.name} ${uMessage.user.surname}</td>
				<td>${uMessage.creationDate}</td>
				<td>
					<form action="readMail">
						<input name="messageId" value="${uMessage.id}" type="hidden" />
						<input type="submit" value="Read" />
					</form>
				</td>
			</tr>
	      	</c:forEach>
	      	
		</table>

   	<p>Read mails:</p>
 		<table>
 			<tr>
 				<th><div></div></th>
 				<th>Topic</th>
 				<th>From</th>
 				<th>At</th>
 				<th></th>
 			</tr>
 			
			<c:forEach items="${openedMessageList}" var="oMessage">
			<tr>
				<td><div id="logoUnreadMail"></</div></td>
				<td>${oMessage.title}</td>
				<td>${oMessage.user.name} ${oMessage.user.surname}</td>
				<td>${oMessage.creationDate}</td>
				<td>
					<form action="readMail">
						<input name="id" value="${oMessage.id}" type="hidden" />
						<input type="submit" value="Read" />
					</form>
				</td>
			</tr>
	      	</c:forEach>
	      	
		</table>

</body>
</html>