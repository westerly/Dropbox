
<%
	session = request.getSession(false);
	if (session.getAttribute("user") == null) {
%>
<jsp:forward page="/Login" />
<!-- response.sendRedirect("./Login"); -->
<%
	}
%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.ArrayList"%>
<%@ page import="beans.File"%>
<%@ page import="beans.Folder"%>
<%@ page import="Core.Controler"%>
<%@ page import="beans.User"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="beans.Folder"%>
<script type="text/javascript" src="jquery-1.10.2.min.js"></script>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome</title>
</head>
<body>

	This is the welcome page.
	<br /> You are connected with the login : ${sessionScope.user.login}
	<!-- This line is used to test the searchEngine page -->
	<%-- <% response.sendRedirect("searchEngine.jsp"); %> --%>
	<br /> Here is your space:
	<br />

	<jsp:include page="/Space"></jsp:include>
	<jsp:include page="/folders.jsp"></jsp:include>
	<jsp:include page="/files.jsp"></jsp:include>


	<%
		if (request.getParameter("parentFolderId") != null) {
			int idParent = Controler.getParentFolderId(Integer
					.parseInt(request.getParameter("parentFolderId")));
			if (idParent != 0) {
				out.println("<br/><a href=\"./welcome.jsp?parentFolderId="
						+ idParent + "\"> Retour </a>");
			} else {
				out.println("<br/><a href=\"./welcome.jsp\"> Retour </a>");
			}
		}
	%>


	<%
		if (request.getAttribute("errorMessage") != null) {
	%>
	<p class="errorMessage">
		<%
			out.println(request.getAttribute("errorMessage"));
		%>
	</p>
	<%
		}
	%>


	<%
		if (request.getParameter("parentFolderId") != null) {
	%>
	<form
		action="upload.jsp?parentFolderId=<%out.println(request.getParameter("parentFolderId"));%>"
		method="post" enctype="multipart/form-data">
		<%
			} else {
		%>
		<form action="upload.jsp" method="post" enctype="multipart/form-data">
			<%
				}
			%>


			<p>
				<br /> Choose a file to upload to the server: <input name="myFile"
					type="file" />
					<input type="submit" /> 
					<br />
		
			</p>

			<hr />

		</form>
</body>
</html>