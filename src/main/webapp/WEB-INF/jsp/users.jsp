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
			<form method="get" action="addUser">
				<p>Sign Up</p>
				<div>
					Name<input type="text" name="name">
					Surname<input type="text" name="surname">
					Email<input type="email" name="email">
					Password<input type="password" name="password">
					<p><input type="submit" value="Submit"></p>
				</div>
			</form>
		</security:authorize>

		<security:authorize access="hasAuthority('ADMIN')">
			<table>
				<tr>
					<th></th>
					<th>Name</th>
					<th>Surname</th>
					<th>Email</th>
					<th>Role</th>
					<th></th>
					<th></th>
				</tr>
				<c:forEach items="${userList}" var="user">
					<tr>
						<td>${user.id}</td>
						<td>${user.name}</td>
						<td>${user.surname}</td>
						<td>${user.email}</td>
						<td>${user.role}</td>
						<td>
							<form action="changeRole">
								<input name="userId" value="${user.id}" type="hidden" />
								<c:if test="${user.role eq 'ADMIN'}">
									<input name="userRole" value="USER" type="hidden" />
									<input type="submit" value="Make USER">
								</c:if>
								<c:if test="${user.role eq 'USER'}">
									<input name="userRole" value="ADMIN" type="hidden" />
									<input type="submit" value="Make ADMIN">
								</c:if>
							</form>
						</td>
						<td>
							<c:if test="${user.enabled}">
								<form action="updateUserStatus">
									<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
									<input name="id" value="${user.id}" type="hidden" />
									<input name="enabled" value="${!user.enabled}" type="hidden" />
									<input type="submit" value="Disable" />
								</form>
							</c:if>
							<c:if test="${!user.enabled}">
								<form action="updateUserStatus">
									<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
									<input name="id" value="${user.id}" type="hidden" />
									<input name="enabled" value="${!user.enabled}" type="hidden" />
									<input type="submit" value="Enable" />
								</form>
							</c:if>
						</td>
					</tr>
				</c:forEach>		
		
			<form method="get" action="addAnyUser">
				<p>Add User</p>
				<div>
					Name<input type="text" name="name">
					Surname<input type="text" name="surname">
					Email<input type="email" name="email">
					Password<input type="password" name="password">
					Role<select name="role">
							<option value="USER">USER</option>
							<option value="ADMIN">ADMIN</option>
						</select>
					<input type="submit" value="Submit">
				</div>
			</form>
		</security:authorize>
	</div>
	</body>
</html>