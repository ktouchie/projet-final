<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Manage Rooms</title>
        <link rel="stylesheet" href="/resources/css/style.css">
    </head>
    <body>
 	<jsp:include page="/WEB-INF/jsp/header.jsp"/>
 	<div class="page">
 		<div class="display" id="displayAddRoom" onclick="display('addRoom');">&#10010; ADD ROOM</div>
 		<div id="addRoom">
 			<p class="heading">ADD ROOM</p>
			<form method="get" action="addRoom">
				<table class="form">
					<tr>
						<td>Name</td>
						<td class="w150"><input type="text" name="name"></td>
					</tr>
					<tr>
						<td>Capacity</td>
						<td><input type="text" name="capacity"></td>
					</tr>
					<tr>
						<td></td>
						<td class="right"><input class="button right"  type="submit" value="Submit"></td>
					</tr>
				</table>
			</form>
		</div>
		<div class="section">
			<p class="heading">EXISTING ROOMS</p>
			<table class="room">
				<tr>
					<th class="w200">Room</th>
					<th class="w80">Max.</th>
					<th></th>
				</tr>
				<c:forEach items="${roomList}" var="room">
					<tr>
						<td>${room.name}</td>
						<td>${room.capacity}</td>
						<td>
							<c:if test="${room.enabled}">
								<form action="updateRoomStatus">
									<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
									<input name="id" value="${room.id}" type="hidden" />
									<input name="enabled" value="${!room.enabled}" type="hidden" />
									<input class="button" type="submit" value="Disable" />
								</form>
							</c:if>
							<c:if test="${!room.enabled}">
								<form action="updateRoomStatus">
									<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
									<input name="id" value="${room.id}" type="hidden" />
									<input name="enabled" value="${!room.enabled}" type="hidden" />
									<input class="button" type="submit" value="Enable" />
								</form>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	<script type="text/javascript">
		function display(id) {
			var e = document.getElementById(id);
			var f = document.getElementById("displayAddRoom");
			e.style.display = 'block';
			f.style.display = 'none';
		}
	</script>		
    </body>
</html>