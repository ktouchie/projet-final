<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<title>Messages</title>
</head>

<body>

   	<p>Messages:</p>
 		<table>
 			<tr>
 				<th><div></div></th>
 				<th>Topic</th>
 				<th>From</th>
 				<th>At</th>
 			</tr>
 			
			<c:forEach items="${messageUnreadList}" var="message">
			<tr>
				<td><div id="logoUnreadMail"></</div></td>
				<td>${message.title}</td>
				<td>${message.user.name} ${message.user.surname}</td>
				<td>${message.creationDate}</td>
				<td>
					<form action="readMail">
						<input name="id" value="${message.id}" type="hidden" />
						<input type="submit" value="Read" />
					</form>
				</td>
			</tr>
	      	</c:forEach>
	      	
		</table>
		<div>${error}</div>
    
    <form method="get" action="addReservation">
    	<p>Select User:</p>
    	<select name="userId" required>
    		<option value="">Select a user</option>
			<c:forEach items="${userList}" var="user">
				<option value="${user.id}">${user.name}${user.surname}</option>
			</c:forEach>
		</select>
		
	    <p>Select Computer:</p>
    	<select name="computerId">
    		<option value="">Select a computer</option>
			<c:forEach items="${computerList}" var="computer">
				<option value="${computer.id}">${computer.brand}${computer.serial}</option>
			</c:forEach>
		</select>
					
	    <p>Select Room:</p>	
		<select name="roomId">
    		<option value="">Select a room</option>
			<c:forEach items="${roomList}" var="room">
				<option value="${room.id}">${room.name}${room.capacity}</option>
			</c:forEach>
		</select>
		
		<p>From:</p>
			<input id="startTimePicker" type="text" name="startTime" required>
		
		<p>To:</p>	
			<input id="endTimePicker" type="text" name="endTime" required>
		
		<input type="submit" value="Submit">

    </form>

</body>
</html>