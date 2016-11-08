<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Reservations</title>
        <script type="text/javascript" src="/resources/js/lib/jquery-3.1.1.min.js"></script>
        <link rel="stylesheet" href="/resources/css/style.css" />
        <script type="text/javascript" src="/resources/js/main.js"></script>
    </head>
    <body>
   	<p>Reservations:</p>
 		<table>
 			<tr>
 				<th>User</th>
 				<th>Computer</th>
 				<th>Room</th>
 				<th>from</th>
 				<th>to</th>
 				<th></th>
 			</tr>
 			
			<c:forEach items="${reservationList}" var="reservation">
				<tr>
					<td>${reservation.user.name} ${reservation.user.surname}</td>
					<td>${reservation.computer.brand} - ${reservation.computer.serial}</td>
					<td>${reservation.room.name}</td>
	       	 		<td>${reservation.startTime}</td>
	       	 		<td>${reservation.endTime}</td>
					<td>
						<form action="deleteReservation">
			       	 		<input name="id" value="${reservation.id}" type="hidden" />
			       	 		<input type="submit" value="Delete" />
			       	 	</form>
					</td>
				</tr>
	      	</c:forEach>
	      	
		</table>
    
    <form method="get" action="addReservation">
    	<p>Select User:</p>
    	<select name="userId">
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
			<input id="startTimePicker" type="text" name="startTime">
		
		<p>To:</p>	
			<input id="endTimePicker" type="text" name="endTime">
		
		<input type="submit" value="Submit">

    </form>
    
		
    </body>
    <link rel="stylesheet" type="text/css" href="/resources/css/jquery.datetimepicker.css"/ >
	<script src="/resources/js/lib/jquery-3.1.1.min.js"></script>
	<script src="/resources/js/jquery.datetimepicker.full.js"></script>
</html>