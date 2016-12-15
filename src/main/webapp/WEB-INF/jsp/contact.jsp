<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
    
    <head>
		<c:set var="url">${pageContext.request.requestURL}</c:set>
		<base href="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}/" />
		<title>Support</title>
		<link rel="stylesheet" href="resources/css/style.css">
		<script>var base = document.getElementsByTagName("base")[0].href;</script>
    </head>
    
    <body>
    <jsp:include page="/WEB-INF/jsp/header.jsp"/>
	<div class="page">
		<p class="heading">CONTACT ADMINISTRATOR</p>
		<form method="post" action="addMessage" id="supportContact">
			<p>Please choose a relevant short title for your issue and describe your request :</p>
			<table>
				<tr>
					<td>Title</td>
					<td><input type="text" name="title" required></td>
				</tr>
				<tr>
					<td>Request</td>
					<input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
					<td><textarea name="content" form="supportContact" required cols="30" rows="10"></textarea></td>
				</tr>
				<tr>
					<td></td>
					<td class="right"><input class="button submit" type="submit" value="Submit"></td>
				</tr>
			</table>
		</form>
    </div>
		
    </body>

</html>