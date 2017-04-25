<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>



	<div align="center">
		<h1>Enter Worklog</h1>
		<form:form action="insertWorklog.htm" method="post"
			modelAttribute="worklog">

			<table>
				<form:hidden path="userId" value="${user.userId}" />
				<form:hidden path="taskId" value="${task.taskId}" />
				<tr>
					<td>Log Start Time:</td>
					<td><form:input path="logStartTime" type="datetime-local" /></td>
				</tr>
				<tr>
					<td>Log End Time</td>
					<td><form:input path="logEndTime" type="datetime-local" /></td>
				</tr>
				<tr>
					<td>Log Description</td>
					<td><form:input path="logDescription" /></td>
				</tr>
				<td colspan="2" align="center"><input type="submit"
					value="Save"></td>
				</tr>
			</table>
		</form:form>
	</div>
</body>
</html>