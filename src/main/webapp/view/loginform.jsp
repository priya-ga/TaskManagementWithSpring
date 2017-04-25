<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>
</head>
<body>
	<h3>Login Form</h3>
	<FONT color="blue"> </FONT>
	<form:form action="dologin.htm" modelAttribute="loginForm">
		<table>

			<tr>
				UserName :
				<td><form:input path="userName" required="required" /></td>
			</tr>

			<tr>
				Password :
				<td><form:password path="password" required="required" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="Submit" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>