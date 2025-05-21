<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User List View</title>
</head>
<body>
	<jsp:useBean id="bean" class="in.co.rays.proj4.bean.UserBean"
		scope="request"></jsp:useBean>

	<%@ include file="Header.jsp"%>
	<h1>User List</h1>
	<table border="1px" width="100%">

				<tr style="background-color: #3f4a2b; color: white;">

					<th>Delete</th>
					<th>Id</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>LoginId</th>
					<th>DOB</th>
					<th>Edit</th>

				</tr>

				<tr align="center" style="background-color: #c4c777">

					<td><input type="checkbox" value="<%=bean.getId()%>"
						name="ids"></td>
					<td><%=bean.getId()%></td>
					<td><%=bean.getFirstName()%></td>
					<td><%=bean.getLastName()%></td>
					<td><%=bean.getLogin()%></td>
					<td><%=bean.getDob()%></td>
					<td><a href="<%=ORSView.USER_LIST_CTL%>?id=<%=bean.getId()%>">edit</a></td>

				</tr>
				
			</table>
			<br>
			<table>
				<tr>
					<input type="submit" value="Delete" name="operation">
				</tr>
			</table>
	<%@ include file="Footer.jsp"%>
</body>
</html>