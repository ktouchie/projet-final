<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>JSP Page</title>
    </head>
    <body>
 	<p>Computers:</p>		
		<c:forEach items="${computerList}" var="computer">
        	 ${computer.id}
        	 ${computer.brand}
        	 ${computer.serial}
        	 <form action="deleteComputer">
        	 	<input name="id" value="${computer.id}" type="hidden" />
        	 	<input type="submit" value="Delete" />
        	 </form>
        	</br>
       	</c:forEach>
       	
      <form method="get" action="addComputer">
			<p>Add Computer</p>
		<div>
			Brand<input type="text" name="brand"></br>
			Serial<input type="text" name="serial"></br>
			<p><input type="submit" value="Submit"></p></br>
		</div>
		</form>
		
    </body>
</html>