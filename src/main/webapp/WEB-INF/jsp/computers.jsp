<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
	<head>
		<c:set var="url">${pageContext.request.requestURL}</c:set>
		<base href="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}/" />
		<title>Manage Computers</title>
		<link rel="stylesheet" href="resources/css/style.css">
		<script>var base = document.getElementsByTagName("base")[0].href;</script>
	</head>
	<body>
	<jsp:include page="/WEB-INF/jsp/header.jsp"/>
	<div class="page">
		<div class="display" id="displayAddComputer" onclick="display('addComputer');">&#10010; ADD COMPUTER</div>
		<div id="addComputer">
			<p class="heading">ADD COMPUTER</p>
			<form method="get" action="addComputer">
				<table class="form">
					<tr>
						<td>Brand</td>
						<td class="w150"><input type="text" name="brand"></td>
					</tr>
					<tr>
						<td>Serial</td>
						<td class="w150"><input type="text" name="serial"></td>
					</tr>
					<tr>
						<td></td>
						<td class="right"><input class="button submit"  type="submit" value="Submit"></td>
					</tr>
				</table>
			</form>
		</div>
		<div class="section">
			<p class="heading">EXISTING COMPUTERS</p>
			<table class="computer">
				<tr>
					<th class="w100">Brand</th>
					<th class="w150">Serial</th>
					<th></th>
				</tr>
				<c:forEach items="${computerList}" var="computer">
					<c:set var="enableStyle" value=""/>
					<c:if test="${!computer.enabled}">
						<c:set var="enableStyle" value="opacity:.60;font-style:italic;"/>
					</c:if>
					<tr>
						<td style="${enableStyle}">${computer.brand}</td>
						<td style="${enableStyle}">${computer.serial}</td>
						<td>
							<c:if test="${computer.enabled}">
								<form action="updateComputerStatus">
									<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
									<input name="id" value="${computer.id}" type="hidden" />
									<input name="enabled" value="${!computer.enabled}" type="hidden" />
									<input class="button enable" type="submit" value="Disable" />
								</form>
							</c:if>
							<c:if test="${!computer.enabled}">
								<form action="updateComputerStatus">
									<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
									<input name="id" value="${computer.id}" type="hidden" />
									<input name="enabled" value="${!computer.enabled}" type="hidden" />
									<input class="button enable" type="submit" value="Enable" />
								</form>
							</c:if>
						</td>
				</c:forEach>
			</table>
		</div>
	</div>
	<script type="text/javascript">
		function display(id) {
			var e = document.getElementById(id);
			var f = document.getElementById("displayAddComputer");
			e.style.display = 'block';
			f.style.display = 'none';
		}
	</script>
    </body>
</html>