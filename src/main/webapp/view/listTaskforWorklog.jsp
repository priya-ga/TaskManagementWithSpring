<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>Insert title here</title>
</head>
<body>
<div align="center">
			<table border="1" cellpadding="5">
				<caption>
				<h2>List of Tasks</h2>
				</caption>
    			<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
				<tr>			
					<th>ID</th>
					<th>Name</th>
					<th>Description</th>
					<th>Action</th>
				</tr>
					 <c:forEach var="task" items="${listTask}" varStatus="status">
                <tr>
                    <td>${status.index + 1}</td>
                    <td>${task.taskName}</td>
                    <td>${task.taskDescription}</td>
                    <td>
                        <a href="${contextPath}/task/getDetail.htm?id=${task.taskId}">View</a>                    
                    </td>
                             
                </tr>
                </c:forEach>     
			</table>
		</div>

			
</body>
</html>