<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    
    <head>
        <title>Support</title>
    </head>
    
    <body>
   	<p>Administrator Contact</p>
    
	<form method="get" action="addMessage" id="supportContact">
		<p>Please chose a relevant short title for your issue and describe your request :</p>
		<div>
			<p>Select User:</p>
    		<select name="userId" required>
    			<option value="">Select a user</option>
				<c:forEach items="${userList}" var="user">
					<option value="${user.id}">${user.name}${user.surname}</option>
				</c:forEach>
			</select>
			<br/>
			Title<input type="text" name="title" required="true">
			<br/>
			Request<input type="text" name="content" required="true">
			<!--
			Request<textarea name="content" form="supportContact" required="true" cols="30" rows="10"></textarea>
			-->
			<p><input type="submit" value="Submit"></p>
		</div>
	</form>
    
		
    </body>

</html>