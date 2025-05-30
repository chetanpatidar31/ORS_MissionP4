<%@page import="in.co.rays.proj4.util.HTMLUtility"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.controller.MarksheetCtl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="icon" type="image/png" href="<%=ORSView.APP_CONTEXT%>/img/customLogo.jpg"
	sizes="16*16" />
<title>Marksheet View</title>
</head>
<body>
	<jsp:useBean id="bean" class="in.co.rays.proj4.bean.MarksheetBean"
		scope="request"></jsp:useBean>
	<%@ include file="Header.jsp"%>
	<div align="center">

		<form action="<%=ORSView.MARKSHEET_CTL%>" method="post">

			<%
				List l = (List) request.getAttribute("studentList");
			%>

			<div align="center">
				<%
					if (bean != null && bean.getId() > 0) {
				%>
				<h1>Update Marksheet</h1>
				<%
					} else {
				%>
				<h1>Add Marksheet</h1>
				<%
					}
				%>
				<H2>
					<font color="red"> <%=ServletUtility.getErrorMessage(request)%></font>
					<font color="green"> <%=ServletUtility.getSuccessMessage(request)%></font>
				</H2>
			</div>
			
				<input type="hidden" name="id" value="<%=bean.getId()%>">


				<table align="center">
					<tr>
						<th align="left">Rollno<span style="color: red">*</span> :
						</th>
						<td><input type="text" name="rollNo"
							placeholder="Enter RollNo" size="25"
							value="<%=DataUtility.getStringData(bean.getRollNo())%>"></td>
						<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("rollNo", request)%></font></td>
					</tr>

					<tr>
						<th style="padding: 3px"></th>
					</tr>

					<tr>
						<th align="left">Name <span style="color: red">*</span> :
						</th>
						
						<td><%=HTMLUtility.getList("studentId", String.valueOf(bean.getStudentId()), l) %></td>

						<td style="position: fixed"><font color="red"> <%=ServletUtility.getErrorMessage("studentId", request)%></font></td>

					</tr>

					<tr>
						<th style="padding: 3px"></th>
					</tr>

					<tr>
						<th align="left">Physics<span style="color: red">*</span> :
						</th>
						<td><input type="number" name="physics" maxlength="3"
							placeholder="Enter Physics Marks" style="width: 208px"
							value="<%=(DataUtility.getStringData(bean.getPhysics()).equals("0") ? ""
					: DataUtility.getStringData(bean.getPhysics()))%>">
						</td>

						<%--  <td><input  onkeypress="return isNumberKey(event)" type="text" name="physics" placeholder="Enter Physics Marks"  size="25"
                        value="<%=DataUtility.getStringData(bean.getPhysics())%>">
                        </td> --%>

						<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("physics", request)%></font></td>
					</tr>

					<tr>
						<th style="padding: 3px"></th>
					</tr>

					<tr>
						<th align="left">Chemistry<span style="color: red">*</span> :
						</th>
						<td><input type="number" name="chemistry" maxlength="3"
							placeholder="Enter Chemistry Marks" style="width: 208px"
							value="<%=(DataUtility.getStringData(bean.getChemistry()).equals("0") ? ""
					: DataUtility.getStringData(bean.getChemistry()))%>">
						</td>

						<%--   <td><input  onkeypress="return isNumberKey(event)" type="text" name="chemistry" placeholder="Enter Chemistry Marks"  size="25"
                        value="<%=DataUtility.getStringData(bean.getChemistry())%>">
                        </td> --%>
						<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("chemistry", request)%></font></td>
					</tr>

					<tr>
						<th style="padding: 3px"></th>
					</tr>

					<tr>

						<th align="left">Maths <span style="color: red">*</span> :
						</th>
						<td><input type="number" name="maths" maxlength="3"
							placeholder="Enter Maths Marks" style="width: 208px"
							value="<%=(DataUtility.getStringData(bean.getMaths()).equals("0") ? ""
					: DataUtility.getStringData(bean.getMaths()))%>">
						</td>
						<%--  <td><input  onkeypress="return isNumberKey(event)" type="text" name="maths" placeholder="Enter Maths Marks"  size="25"
                         value="<%=DataUtility.getStringData(bean.getMaths())%>">
                        </td> --%>
						<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("maths", request)%></font></td>

					</tr>

					<tr>
						<th style="padding: 3px"></th>
					</tr>

					<tr>
						<th></th>
						<%
							if (bean.getId() > 0 && bean != null) {
						%>
						<td>
							<%-- <input type="submit" name ="operation" style="width:80px;height:30px" value="<%=MarksheetCtl.OP_UPDATE%>">
          <input type="submit" name ="operation" style="width:80px;height:30px" value="<%=MarksheetCtl.OP_CANCEL%>"></td>
           --%> &nbsp; <input type="submit" name="operation"
							value="<%=MarksheetCtl.OP_UPDATE%>"> &nbsp; <input
							type="submit" name="operation"
							value="<%=MarksheetCtl.OP_CANCEL%>">
						</td>


						<%
							} else {
						%>

						<td colspan="2">
							<%-- <input type="submit" name="operation" style="width:80px;height:30px"  value="<%=MarksheetCtl.OP_SAVE%>"> 
   <input type="submit" name="operation" style="width:80px;height:30px "  value="<%=MarksheetCtl.OP_RESET%>"></td>
     --%> &nbsp; &emsp; <input type="submit" name="operation"
							value="<%=MarksheetCtl.OP_SAVE%>"> &nbsp;&nbsp; <input
							type="submit" name="operation" value="<%=MarksheetCtl.OP_RESET%>">
						</td>

						<%
							}
						%>
					</tr>
				</table></form>

	</div>
	<%@ include file="Footer.jsp"%>

</body>
</html>