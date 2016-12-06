<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<title>Message</title>
	<link rel="stylesheet" href="/resources/css/style.css">
</head>

<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>
		<div id="title">
		<p>${messageRead.title}</p>
		</div>
		<div id="messageInfos">
		<p>the ${messageRead.creationDate} from ${messageRead.user.name} ${messageRead.user.surname}</p>
		</div>
		<div id="messageContent">
		<p>${messageRead.content}</p>
		</div>
 		<br/>
 		
		<c:forEach items="${replyList}" var="reply">
			<div id="replyInfos">
			<p>the ${reply.replyDate} from ${reply.user.name} ${reply.user.surname}</p>
			</div>
			<div id="replyContent">
			<p>${reply.content}</p>
			</div>
			<br/>
      	</c:forEach>
		
		<form method="post" action="addReply" id="replyMessage">
		<p>Add a comment :</p>
		<div>
			<textarea name="content" form="replyMessage" required="true" cols="30" rows="10"></textarea>
			<input type="hidden" name="messageId" value="${messageRead.id}">
			<input name="mailBoxSource" value="${mailBoxSource}" type="hidden" />
			<input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
			<p><input type="submit" value="Reply"></p>
		</div>
		</form>
		
</body>
</html>