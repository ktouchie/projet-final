<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<title>Reservations at a Glance</title>
		<script type="text/javascript" src="/resources/js/lib/jquery-3.1.1.min.js"></script>
		<script type="text/javascript" src="/resources/js/main.js"></script>
		<link rel="stylesheet" href="/resources/css/style.css">
	</head>
	<body>
	<jsp:include page="/WEB-INF/jsp/header.jsp"/>
		<form method="get" action="updateCalendar">
			<select name="computerId">
				<option value="">Select a computer</option>
				<c:forEach items="${computerList}" var="computer">
					<option value="${computer.id}">${computer.brand}${computer.serial}</option>
				</c:forEach>
			</select>
			<select name="roomId">
				<option value="">Select a room</option>
				<c:forEach items="${roomList}" var="room">
					<option value="${room.id}">${room.name}${room.capacity}</option>
				</c:forEach>
			</select>
			<select name="selectedMonth">
				<option value="">Select month</option>
				<option value="0">January</option>
				<option value="1">February</option>
				<option value="2">March</option>
				<option value="3">April</option>
				<option value="4">May</option>
				<option value="5">June</option>
				<option value="6">July</option>
				<option value="7">August</option>
				<option value="8">September</option>
				<option value="9">October</option>
				<option value="10">November</option>
				<option value="11">December</option>
			</select>
			<select name="selectedYear">
				<option value="">Select year</option>
				<c:forEach var="i" begin="${thisYear}" end="${thisYear+1}">
					<option value="${i}">${i}</option>
				</c:forEach>
			</select>
			<input type="submit" value="View Reservations">
		</form>
		
		<h1>${months[currentMonth]} ${currentYear}</h1>

		<table>
			<tr>
				<th></th>
				<c:forEach var="i" begin="1" end="${maxDays}">
				<th><c:out value="${i}"/></th>
				</c:forEach>
			</tr>
			<c:forEach var="i" begin="9" end="17">
			<tr>
				<td><c:out value="${i}"/>h - <c:out value="${i+1}"/>h</td>
				<c:forEach var="j" begin="1" end="${maxDays}">					
					<c:set var="id" value="start${i} day${j}"/>
					<c:set var="reserved" value="background-color:#fff"/>
					<c:if test="${resClasses.contains(id) }">
						<c:set var="reserved" value="background-color:#d8dfe5"/>
					</c:if>



					
					<td id="start${i} day${j}" style="${reserved}" ></td>
				</c:forEach>
			</tr>
			</c:forEach>
		</table>


</html>