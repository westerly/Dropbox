
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

	<%
		ArrayList folders = (ArrayList) request.getAttribute("folders");
		for (int i = 0; i < folders.size(); i++) {
			Folder fold = (Folder) folders.get(i);
	%>
	<form method="post" action="./Folder">
		<input type="hidden" name="parent"
			value=<%if (request.getParameter("parentFolderId") != null) {
					out.println(request.getParameter("parentFolderId"));
				} else {
					User userCo = (User) request.getSession().getAttribute(
							"user");
					out.println(userCo.getNameSpace());
				}%> />
		<input type="hidden" name="action" value="delete" /> <input
			type="hidden" name="folderName"
			value=<%out.println(fold.getFolderName());%> />
		<%
			out.println("<br/><a href=\"./welcome.jsp?parentFolderId="
						+ fold.getId() + "\">" + fold.getFolderName() + "</a>");
		%>
		<input type="submit" value="Delete" />
	</form>
	<%
		}
	%>

	<%
		ArrayList files = (ArrayList) request.getAttribute("files");
		for (int i = 0; i < files.size(); i++) {
			File f = (File) files.get(i);
			out.println("<br/>" + f.getFileName());
		}
	%>

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

	<form method="post" action="./Folder">
		<input type="hidden" name="parent"
			value=<%if (request.getParameter("parentFolderId") != null) {
				out.println(request.getParameter("parentFolderId"));
			} else {
				User userCo = (User) request.getSession().getAttribute("user");
				out.println(userCo.getNameSpace());
			}%> />
		<input type="hidden" name="action" value="create" /> New folder : <input
			type="text" name="folderName" /> <input type="submit" value="Create" />
	</form>


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

	<!-- 	 <form name="add-by-file" enctype="multipart/form-data" method="post" action="./Upload" target="target_upload">
	    <input type="file" name="file"/>
	    <input type="submit" value="Upload"/>
	</form>
	
	<iframe id="target_upload" name="target_upload" src=""></iframe>
	
	<script type="text/javascript">
		var terminateFileUpload = function() {
			// on récupère le contenu du fichier et on retourne un deck
			Account.generateDeckFromUploadFile({
				callback: function(deck) {
					document.write("File uploaded");
				}
			});
		}
	</script> -->

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
					type="file" /><br />
				<%--    <%
        	if(request.getParameter("parentFolderId") != null){
        %>
        	<input type="hidden" name="parentFolderId" value="<%out.println(request.getParameter("parentFolderId"));%>"/>
        <%
        	}
        %> --%>
			</p>

			<hr />

			<p>
				<input type="submit" /> <input type="reset" />
			</p>
		</form>
</body>
</html>