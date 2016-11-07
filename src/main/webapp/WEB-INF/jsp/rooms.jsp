<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Rooms</title>
    </head>
    <body>
 	
		<p>Rooms:</p>		
		<c:forEach items="${roomList}" var="room">
        	 ${room.id}
        	 ${room.name}
        	 ${room.capacity}
        	 <form action="deleteRoom">
        	 	<input name="id" value="${room.id}" type="hidden" />
        	 	<input type="submit" value="Delete" />
        	 </form>
        	</br>
       	</c:forEach>
       	
      <form method="get" action="addRoom">
			<p>Add Room</p>
		<div>
			Name<input type="text" name="name"></br>
			Capacity<input type="text" name="capacity"></br>
			<p><input type="submit" value="Submit"></p></br>
		</div>
		</form>
		
		
    </body>
</html>