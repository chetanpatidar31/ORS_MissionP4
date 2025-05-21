<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.controller.LoginCtl"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="in.co.rays.proj4.controller.ORSView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
</head>
<body>
	<jsp:useBean id="bean" class="in.co.rays.proj4.bean.UserBean"
		scope="request"></jsp:useBean>

	<%@ include file="Header.jsp"%>

	<h1 align="center">Login</h1>

	<form action="<%=ORSView.LOGIN_CTL%>" method="post">
		<div align="center">

			<span style="color: red"><%=ServletUtility.getErrorMessage(request)%></span>
			<span style="color: green"><%=ServletUtility.getSuccessMessage(request)%></span>

			<table>
				<tr>
					<th>Login</th>
					<td><input type="email" name="login" value="<%=DataUtility.getStringData(bean.getLogin()) %>"
						placeholder="enter your email"> <font color="red"><%=ServletUtility.getErrorMessage("login", request)%></font>
					</td>
				</tr>
				<tr>
					<th>Password</th>
					<td><input type="password" name="password" value="<%=DataUtility.getStringData(bean.getPassword()) %>"
						placeholder="enter your password"> <font color="red"><%=ServletUtility.getErrorMessage("password", request)%></font>
					</td>
				</tr>
				<tr>
					<th></th>
					<td><input type="submit" name="operation"
						value="<%=LoginCtl.OP_SIGN_IN%>"> &nbsp; <input
						type="submit" name="operation" value="<%=LoginCtl.OP_SIGN_UP%>">
					</td>
				</tr>
			</table>
		</div>
	</form>

	<%@ include file="Footer.jsp"%>
</body>
</html>