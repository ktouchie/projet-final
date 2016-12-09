<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<security:authorize access="!isAuthenticated()">
			<title>Sign Up</title>
		</security:authorize>
		<security:authorize access="hasAuthority('ADMIN')">
			<title>Create and Manage Users</title>
		</security:authorize>
		<link rel="stylesheet" href="/resources/css/style.css">
	</head>
	<body>
	<jsp:include page="/WEB-INF/jsp/header.jsp"/>
	<div class="page">
		<security:authorize access="!isAuthenticated()">
				<p>Sign Up</p>
				<div>
					<form method="post" action="addUser" modelattribute="subscriber">
						<label for="nameInput">Name</label>
						<input type="text" path="name" id="nameInput"></input>
						<form:errors path="name" cssclass="error"></form:errors>
						<br/>
						
						<label for="surnameInput">Surname</label>
						<input type="text" path="surname" id="surnameInput"></input>
						<form:errors path="surname" cssclass="error"></form:errors>
						<br/>
						
						<form:label for="emailInput">Email</form:label>
						<input type="text" path="email" id="emailInput"></input>
						<form:errors path="email" cssclass="error"></form:errors>
						<br/>
						
						<form:label for="pwdInput">Password</form:label>
						<input type="text" path="password" id="pwdInput"></input>
						<form:errors path="password" cssclass="error"></form:errors>
						<br/>
						
						<input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}"></input>
						<p><input class="button" type="submit" value="Submit"></input></p>
					</form>
				</div>
			
		</security:authorize>

		<security:authorize access="hasAuthority('ADMIN')">
			<div class="display" id="displayAddUser" onclick="display('addUser');">&#10010; ADD NEW USER</div>
			<div id="addUser">
				<p class="heading">ADD NEW USER</p>
				<form method="get" action="addAnyUser">
					<table class="form">
						<tr>
							<td>Name</td>
							<td class="w200"><input type="text" name="name"></td>
							<td>Email</td>
							<td><input type="email" name="email"></td>
						</tr>
						<tr>
							<td>Surname</td>
							<td class="w200"><input type="text" name="surname"></td>
							<td>Password</td>
							<td><input type="password" name="password"></td>
						</tr>
						<tr>
							<td>Role</td>
							<td>
								<select name="role">
									<option value="USER">USER</option>
									<option value="ADMIN">ADMIN</option>
								</select>
							</td>
							<td></td>
							<td class="right"><input class="button submit" type="submit" value="Submit"></td>
						</tr>
					</table>
				</form>
			</div>
			<div class ="section">				
				<p class="heading">EXISTING USERS</p>
				<table class="user">
					<tr>
						<th class="w100">Name</th>
						<th class="w100">Surname</th>
						<th class="w200">Email</th>
						<th class="w80">Role</th>
						<th class="w80"></th>
						<th class="w80"></th>
					</tr>
					<c:forEach items="${userList}" var="user">
						<c:set var="enableStyle" value=""/>
						<c:if test="${!user.enabled}">
							<c:set var="enableStyle" value="opacity:.60;font-style:italic;"/>
						</c:if>
						<c:set var="adminStyle" value=""/>
						<c:if test="${user.role eq 'ADMIN'}">
							<c:set var="adminStyle" value="color:#990035;font-weight:bold;"/>
						</c:if>
						<tr>
							<td style="${enableStyle}${adminStyle}">${user.name}</td>
							<td style="${enableStyle}${adminStyle}">${user.surname}</td>
							<td style="${enableStyle}${adminStyle}">${user.email}</td>
							<td style="${enableStyle}${adminStyle}">${user.role}</td>
							<td>
								<form action="changeRole">
									<input name="userId" value="${user.id}" type="hidden" />
									<c:if test="${user.role eq 'ADMIN'}">
										<input name="userRole" value="USER" type="hidden" />
										<input class="button wide" type="submit" value="Make USER">
									</c:if>
									<c:if test="${user.role eq 'USER'}">
										<input name="userRole" value="ADMIN" type="hidden" />
										<input class="button wide" type="submit" value="Make ADMIN">
									</c:if>
								</form>
							</td>
							<td>
								<c:if test="${user.enabled}">
									<form action="updateUserStatus">
										<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
										<input name="id" value="${user.id}" type="hidden" />
										<input name="enabled" value="${!user.enabled}" type="hidden" />
										<input class="button enable" type="submit" value="Disable" />
									</form>
								</c:if>
								<c:if test="${!user.enabled}">
									<form action="updateUserStatus">
										<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
										<input name="id" value="${user.id}" type="hidden" />
										<input name="enabled" value="${!user.enabled}" type="hidden" />
										<input class="button enable" type="submit" value="Enable" />
									</form>
								</c:if>
							</td>
						</tr>
					</c:forEach>		
				</table>
			</div>
		</security:authorize>
	</div>
	<script type="text/javascript">
		function display(id) {
			var e = document.getElementById(id);
			var f = document.getElementById("displayAddUser");
			e.style.display = 'block';
			f.style.display = 'none';
		}
	</script>
	</body>
</html>