<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome Page</title>
</head>
<body>
	<form action="<%=ORSView.WELCOME_CTL%>">
		<%@ include file="Header.jsp" %>
		<br>
		<h1 align="Center">
			<font size="10px" color="red">Welcome to ORS</font>
		</h1>
	</form>

	<%@ include file="Footer.jsp"%>
</body>
</html>