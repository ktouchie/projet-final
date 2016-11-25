<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<title>Planning</title>
		<script type="text/javascript" src="/resources/js/lib/jquery-3.1.1.min.js"></script>
		<link rel="stylesheet" href="/resources/css/style.css" />
		<script type="text/javascript" src="/resources/js/main.js"></script>
	</head>
	<body>
		<h1>${currentMonthName}</h1>

		<table>
			<tr>
				<th></th>
				<c:forEach var="i" begin="1" end="${maxDays}">
				<th><c:out value="${i}"/></th>
				</c:forEach>
			</tr>
			<c:forEach var = "i" begin="9" end="17">
			<tr>
				<td><c:out value="${i}"/>h - <c:out value="${i+1}"/>h</td>
				<c:forEach var="j" begin="1" end="${maxDays}">					
					<c:set var="id" value="start${i} day${j}"/>
					<c:set var="reserved" value="background-color:#fff"/>
					<c:forEach items="${resClasses}" var="res">
						<c:set var="resId" value="${res}"/>
							<c:if test="${id eq resId}">
									<c:set var="reserved" value="background-color:#d8dfe5"/>
							</c:if>						
					</c:forEach>
					<td id="start${i} day${j}" style="${reserved}" ></td>
				</c:forEach>
			</tr>
			</c:forEach>
		</table>
		<c:forEach items="${resClasses}" var="res">
			${res}
		</c:forEach>

</html>