<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
	<c:set var="url">${pageContext.request.requestURL}</c:set>
	<base href="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}/" />
	<title>Message</title>
	<link rel="stylesheet" href="resources/css/style.css">
	<script>var base = document.getElementsByTagName("base")[0].href;</script>
</head>

<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>
<div class="page">
		<div class="msgtitle">
		<p style="font-weight:bold;">${messageRead.title}</p>
		</div>
		<br/>
		<div class="messageInfos">
		<p><fmt:formatDate type="both" timeStyle="short" dateStyle="short" value="${messageRead.creationDate}"/>, <span style="font-weight:bold;">${messageRead.user.name} ${messageRead.user.surname}</span> wrote:</p>
		</div>
		<div class="messageContent">
		<p>${messageRead.content}</p>
		</div>
 		<br/>
 		
		<c:forEach items="${replyList}" var="reply">
			<div class="replyInfos">
			<p><fmt:formatDate type="both" timeStyle="short" dateStyle="short" value="${reply.replyDate}"/>, <span style="font-weight:bold;">${reply.user.name} ${reply.user.surname}</span> wrote:</p>
			</div>
			<div class="replyContent">
			<p>${reply.content}</p>
			</div>
			<br/>
      	</c:forEach>
		
		<form method="post" action="addReply" id="replyMessage">
		<p>Add a reply :</p>
		<div>
			<textarea name="content" form="replyMessage" required cols="30" rows="10"></textarea>
			<input type="hidden" name="messageId" value="${messageRead.id}">
			<input name="mailBoxSource" value="${mailBoxSource}" type="hidden" />
			<input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
			<p><input class="button submit" type="submit" value="Reply"></p>
		</div>
		</form>
</div>		
</body>
</html>