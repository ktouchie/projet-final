<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>JSP Page</title>
    </head>
    <body>
    
    <form method="get" action="addReservation">
	    <p>Select Computer:</p>

	    	<select name="computers">
				<c:forEach items="${computerList}" var="computer">
					<option value="${computer.id}">${computer.brand}${computer.serial}</option>
				</c:forEach>
			</select>
			
			<select name="rooms">
				<c:forEach items="${roomList}" var="room">
					<option value="${room.id}">${room.name}${room.capacity}</option>
				</c:forEach>
			</select>

    </form>
    
		
    </body>
</html>