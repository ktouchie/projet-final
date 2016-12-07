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
					<th class="w100">Room</th>
					<th>Capacity</th>
					<th class="w80"></th>
				</tr>
				<c:forEach items="${roomList}" var="room">
					<tr>
						<td>${room.name}</td>
						<td>${room.capacity}</td>
						<td>
							<form action="deleteRoom">
								<input name="id" value="${room.id}" type="hidden" />
								<input class="button" type="submit" value="Delete" />
							</form>
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