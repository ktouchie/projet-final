<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Manage Computers</title>
        <link rel="stylesheet" href="/resources/css/style.css">
    </head>
    <body>
    <jsp:include page="/WEB-INF/jsp/header.jsp"/>
    <div class="page">
 	<p>Computers:</p>		
		<c:forEach items="${computerList}" var="computer">
        	 ${computer.id}
        	 ${computer.brand}
        	 ${computer.serial}
        	 <form action="deleteComputer">
        	 	<input name="id" value="${computer.id}" type="hidden" />
        	 	<input type="submit" value="Delete" />
        	 </form>
        	
       	</c:forEach>
       	
      <form method="get" action="addComputer">
			<p>Add Computer</p>
		<div>
			Brand<input type="text" name="brand">
			Serial<input type="text" name="serial">
			<p><input type="submit" value="Submit"></p>
		</div>
		</form>
		</div>
		
    </body>
</html>