<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration Page</title>
</head>
<body>
	<h3>Login Form</h3>
	<FONT color="blue"> </FONT>
	<form:form action="doregistration.htm" modelAttribute="registrationForm">
		<table>
			<tr>
				FirstName :
				<td><form:input path="fisrtName" /></td>
			</tr>
			<tr>
				LastName :
				<td><form:input path="lastName" /></td>
			</tr>
			<tr>
				Email :
				<td><form:input path="email" /></td>
			</tr>
			<tr>
				UserName :
				<td><form:input path="userName" /></td>
			</tr>

			<tr>
				Password :
				<td><form:password path="password" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="Submit" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>