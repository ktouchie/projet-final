<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<title>Send in Messages</title>
</head>

<body>

   	<p>New Replies from support :</p>
   	<!--
   	<c:if test=""></c:if>
   	-->
		<table>
			<tr>
				<th><div></div></th>
				<th>Topic</th>
				<th>From</th>
				<th>At</th>
				<th></th>
				<th></th>
			</tr>
			
		<c:forEach items="${unopenedRepliedMessageList}" var="uMessage">
		<tr>
			<td><div id="logoUnreadMail"></</div></td>
			<td>${uMessage.title}</td>
			<td>${uMessage.user.name} ${uMessage.user.surname}</td>
			<td>${uMessage.creationDate}</td>
			<td>
				<form action="readMail">
					<input name="messageId" value="${uMessage.id}" type="hidden" />
					<input name="mailBoxSource" value="userInbox" type="hidden" />
					<input type="submit" value="Read" />
				</form>
			</td>
			<td>
				<form action="disableThread">
					<input name="messageId" value="${uMessage.id}" type="hidden" />
					<input name="mailBoxSource" value="userInbox" type="hidden" />
					<input type="submit" value="Delete" />
				</form>
			</td>
		</tr>
      	</c:forEach>
      	
	</table>

   	<p>Opened mails:</p>
		<table>
			<tr>
				<th><div></div></th>
				<th>Topic</th>
				<th>From</th>
				<th>At</th>
				<th></th>
				<th></th>
			</tr>
			
		<c:forEach items="${openedRepliedMessageList}" var="oMessage">
		<tr>
			<td><div id="logoUnreadMail"></</div></td>
			<td>${oMessage.title}</td>
			<td>${oMessage.user.name} ${oMessage.user.surname}</td>
			<td>${oMessage.creationDate}</td>
			<td>
				<form action="readMail">
					<input name="messageId" value="${oMessage.id}" type="hidden" />
					<input name="mailBoxSource" value="userInbox" type="hidden" />
					<input type="submit" value="Read" />
				</form>
			</td>
			<td>
				<form action="disableThread">
					<input name="messageId" value="${oMessage.id}" type="hidden" />
					<input name="mailBoxSource" value="userInbox" type="hidden" />
					<input type="submit" value="Delete" />
				</form>
			</td>
		</tr>
      	</c:forEach>
      	
	</table>
	
	<br/>
	<br/>
	<br/>
	<a href="/contact">Contact Us</a><a href="/userOutbox">Outbox</a>
	
</body>
</html>