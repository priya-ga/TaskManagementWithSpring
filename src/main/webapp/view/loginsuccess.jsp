<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
 "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Success Page</title>
</head>
<body>
	<h3>
		Welcome
		<c:out value="${message.userName}" />
	</h3>


	<form method="post" action="task">
		<center>
			<table border="1" width="30%" cellpadding="5">
				<thead>
					<tr>
						<th colspan="2"><a
							href="${pageContext.request.contextPath}/task/getList.htm">Create
								Task</a></th>
					</tr>
				</thead>


			</table>
		</center>
	</form>
	<form method="post" action="task">
		<center>
			<table>
				<thead>
					<tr>
						<th colspan="2"><a
							href="${pageContext.request.contextPath}/task/getListForDetail.htm">View
								Task</a></th>
					</tr>
				</thead>
			</table>
		</center>

	</form>

	<table>
		<tr>
			<td><a href="${pageContext.request.contextPath}/logout/logout.htm">Log Out</a></td>
		</tr>
	</table>
</body>
</html>