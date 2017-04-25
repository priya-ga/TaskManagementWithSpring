<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Task Detail</title>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<table>
		<tr>
			Task Name:
			<c:out value="${task.taskId}" />
		</tr>
		</br>
		<tr>
			Task Description:
			<c:out value="${task.taskDescription}" />
		</tr>
	</table>



	<div align="center">
		<tr>
			<center>
				<td>WorkLog</td>
			</center>
		</tr>
		<table border="1" cellpadding="5">

			<caption>
				<h2>List of UserTasks</h2>
			</caption>
			<thread>
			<tr>
				<th>User Name</th>
				<th>Log Start Time</th>
				<th>Log End Time</th>
				<th>Log Description</th>
				<th>Total Duration</th>

			</tr>

			</thread>
			<tbody>
				<c:forEach var="usertask" items="${userTaskDTOList}">
					<tr>
						<td><c:out value="${usertask.userName}" /></td>
						<td><c:out value="${usertask.logStartTime}" /></td>
						<td><c:out value="${usertask.logEndTime}" /></td>
						<td><c:out value="${usertask.logDescription}" /></td>
						<td><c:out value="${usertask.totalDuration}" /></td>

					</tr>
				</c:forEach>
			</tbody>


		</table>


		<table>
			<tr>
				<td><a
					href="${contextPath}/task/addWorklog.htm?id=${task.taskId}">Add
						Worklog</a></td>

			</tr>


		</table>


	</div>


</body>
</html>