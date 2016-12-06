<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    
    <head>
        <title>Support</title>
        <link rel="stylesheet" href="/resources/css/style.css">
    </head>
    
    <body>
    <jsp:include page="/WEB-INF/jsp/header.jsp"/>
    <div class="page">
   	<p>Administrator Contact</p>
    
	<form method="post" action="addMessage" id="supportContact">
		<p>Please chose a relevant short title for your issue and describe your request :</p>
		<div>
			<br/>
			Title<input type="text" name="title" required>
			<br/>
			Request<textarea name="content" form="supportContact" required cols="30" rows="10"></textarea>
			<input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
			<p><input type="submit" value="Submit"></p>
		</div>
	</form>
    </div>
		
    </body>

</html>