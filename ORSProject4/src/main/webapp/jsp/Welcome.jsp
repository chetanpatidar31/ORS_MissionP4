<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="icon" type="image/png" href="<%=ORSView.APP_CONTEXT%>/img/customLogo.jpg"
	sizes="16*16" />
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