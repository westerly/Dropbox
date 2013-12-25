<%
    session=request.getSession(false);
    if(session.getAttribute("user")==null)
    {
        response.sendRedirect("./Login");
    }

%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    
<%@ page import="java.util.ArrayList" %>
<%@ page import="beans.File" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome</title>
</head>
<body>

	This is the welcome page.
	<br/>
	You are connected with the login : ${sessionScope.user.login}
	
	<br/>
	Here is your space:
	<br/>
	
	<jsp:include page="/Space"></jsp:include>
	<%if(request.getAttribute("parentFolderId") != null){%>
		<!-- Display link to come back -->
	<%}%>
	
	<% //out.println(request.getAttribute("parentFiles"));%>
	<%
	
		ArrayList files = (ArrayList)request.getAttribute("parentFiles");
		for(int i=0; i < files.size();i++){
			File f = (File)files.get(i);
			out.println("<br/>" + f.getFileName());
		}
	
	%>

	


</body>
</html>