<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>JSP Page</title>
    </head>
    <body>
    	<p>Users:</p>
        <c:forEach items="${userList}" var="user">
        	 ${user.id}
        	 ${user.name}
        	 ${user.surname}
        	 ${user.email}
        	 <form action="deleteUser">
        	 	<input name="id" value="${user.id}" type="hidden" />
        	 	<input type="submit" value="Delete" />
        	 </form>
        	</br>
       	</c:forEach>
       	
      <form method="get" action="addUser">
			<p>Add User</p>
		<div>
			Name<input type="text" name="name"></br>
			Surname<input type="text" name="surname"></br>
			Email<input type="email" name="email"></br>
			Password<input type="text" name="password"></br>
			<p><input type="submit" value="Submit"></p></br>
		</div>
		</form>
		
    </body>
</html>