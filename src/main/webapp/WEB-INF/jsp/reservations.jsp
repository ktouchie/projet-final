<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <title>Manage Reservations</title>
        <script type="text/javascript" src="/resources/js/lib/jquery-3.1.1.min.js"></script>
        <link rel="stylesheet" href="/resources/css/style.css" />
        <script type="text/javascript" src="/resources/js/main.js"></script>
    </head>
    <body>
    <jsp:include page="/WEB-INF/jsp/header.jsp"/>
    <div class="page">

	<div class="display" id="displayAddReservation" onclick="display('addReservation');">&#10010; ADD RESERVATION</div>
	<div id="addReservation">
		<p class="heading">ADD RESERVATION</p>
		<form method="get" action="addReservation">
			<table class="form">
				<security:authorize access="hasAuthority('ADMIN')">
					<tr><td>
						<select name="userId" required>
							<option value="">Select a user</option>
							<c:forEach items="${userList}" var="user">
								<option value="${user.id}">${user.name} ${user.surname}</option>
							</c:forEach>
						</select>
					</td></tr>
				</security:authorize>
				<security:authorize access="hasAuthority('USER')">
					<input name="userId" value="${currentUserId}" type="hidden" />
				</security:authorize>
				<tr><td>
				<select name="computerId">
					<option value="">Select a computer</option>
					<c:forEach items="${computerList}" var="computer">
						<option value="${computer.id}">${computer.brand} ${computer.serial}</option>
					</c:forEach>
				</select>
				</td></tr>
				<tr><td>
					<select name="roomId">
						<option value="">Select a room</option>
						<c:forEach items="${roomList}" var="room">
							<option value="${room.id}">${room.name} (${room.capacity})</option>
						</c:forEach>
					</select>
				</td></tr>
			</table>
			<br/>
			<table>
				<tr>
					<td>From</td>
					<td><input class ="datepicker" id="startTimePicker" type="text" name="startTime" required></td>
				</tr>
				<tr>
					<td>To</td>
					<td><input class ="datepicker" id="endTimePicker" type="text" name="endTime" required></td>
				</tr>
				<tr>
				<td></td>
				<td class="right"><input class="button submit" type="submit" value="Submit"></td>
			</table>
    	</form>
	</div>
	</div>
    
	<div class="section">
		<p class="heading">RESERVATIONS</p>
		<table class="reservation">
 			<tr>
 				<security:authorize access="hasAuthority('ADMIN')">
 					<th class="w200">User</th>
 				</security:authorize>
 				<th class="w200">Computer</th>
 				<th class="w150">Room</th>
 				<th class="w150">from</th>
 				<th class="w150">to</th>
 				<th class="w100"></th>
 			</tr>
 			
			<security:authorize access="hasAuthority('ADMIN')">
			<c:forEach items="${reservationList}" var="reservation">
			<tr>
				<td>${reservation.user.name} ${reservation.user.surname}</td>
				<td>${reservation.computer.brand}-${reservation.computer.serial}</td>
				<td>${reservation.room.name}</td>
				<td><fmt:formatDate type="both" timeStyle="short" dateStyle="short" value="${reservation.startTime}"/></td>
				<td><fmt:formatDate type="both" timeStyle="short" dateStyle="short" value="${reservation.endTime}"/></td>
				<td>
					<form action="deleteReservation">
						<input name="id" value="${reservation.id}" type="hidden" />
						<input class= "button submit" type="submit" value="Delete" />
					</form>
				</td>
			</tr>
	      	</c:forEach>
			</security:authorize>

			<security:authorize access="hasAuthority('USER')">
			<c:forEach items="${userReservationList}" var="reservation">
			<tr>
				<td>${reservation.computer.brand}-${reservation.computer.serial}</td>
				<td>${reservation.room.name}</td>
				<td><fmt:formatDate type="both" timeStyle="short" dateStyle="short" value="${reservation.startTime}"/></td>
				<td><fmt:formatDate type="both" timeStyle="short" dateStyle="short" value="${reservation.endTime}"/></td>
				<td>
					<form action="deleteReservation">
						<input name="id" value="${reservation.id}" type="hidden" />
						<input class= "button submit" type="submit" value="Delete" />
					</form>
				</td>
			</tr>
			</c:forEach>
			</security:authorize>

	      	
		</table>
		<div>${error}</div>
    </div>
	<script type="text/javascript">
		function display(id) {
			var e = document.getElementById(id);
			var f = document.getElementById("displayAddReservation");
			e.style.display = 'block';
			f.style.display = 'none';
		}
	</script>
    </body>
    <link rel="stylesheet" type="text/css" href="/resources/css/jquery.datetimepicker.css"/>
	<script src="/resources/js/lib/jquery-3.1.1.min.js"></script>
	<script src="/resources/js/jquery.datetimepicker.full.js"></script>
</html>