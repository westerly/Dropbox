<?xml version="1.0"?>
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<%@ page import="beans.SpaceData" %>
<%@ page import="Configuration.Configuration" %>
<%@ page import="Core.Controler" %>
<%@ page import="beans.User" %>
<%@ page import="org.apache.commons.fileupload.*, org.apache.commons.fileupload.servlet.ServletFileUpload, org.apache.commons.fileupload.disk.DiskFileItemFactory, org.apache.commons.io.FilenameUtils, java.util.*, java.io.File, java.lang.Exception" %>
<%-- <% response.setContentType("application/vnd.wap.xhtml+xml"); %> --%>

<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>File Upload</title>
  </head>

  <body>
    <hr/>
    <p>

<%
if (ServletFileUpload.isMultipartContent(request)){
  ServletFileUpload servletFileUpload = new ServletFileUpload(new DiskFileItemFactory());
  List fileItemsList = servletFileUpload.parseRequest(request);

  FileItem fileItem = null;

  Iterator it = fileItemsList.iterator();

  while (it.hasNext()){
    FileItem fileItemTemp = (FileItem)it.next();
    if (!fileItemTemp.isFormField()){
    	   fileItem = fileItemTemp;
    }
    
    if (fileItem!=null){
      String fileName = fileItem.getName();
%>

<b>Uploaded File Info:</b><br/>
Content type: <%= fileItem.getContentType() %><br/>
File name: <%= fileName %><br/>
File size: <%= fileItem.getSize() %><br/><br/>

<%
    /* Save the uploaded file if its size is greater than 0. */
    if (fileItem.getSize() > 0){
        fileName = FilenameUtils.getName(fileName);
      
      String dirName;
      
      if(request.getParameter("parentFolderId") != null){
			SpaceData spaceD = new SpaceData();
			dirName = Configuration.DATA_FOLDER + spaceD.getFullPathfromFolder(Integer.parseInt(request.getParameter("parentFolderId")));
		}else{
			// The parent is a nameSpace
			User userCo = (User)request.getSession().getAttribute("user");
			dirName = Configuration.DATA_FOLDER + userCo.getNameSpace() + "\\";
		}

      File saveTo = new File(dirName + fileName);
      try {
        fileItem.write(saveTo);
%>

<b>The uploaded file has been saved successfully.</b>

<%
		if(request.getParameter("parentFolderId") != null){
			Controler.createFile(fileName, request.getParameter("parentFolderId"));
		}else{
			// The parent is a nameSpace
			User userCo = (User)request.getSession().getAttribute("user");
			Controler.createFile(fileName, userCo.getNameSpace());
		}
      }catch (Exception e){
%>

<b>An error occurred when we tried to save the uploaded file.</b>
<br/>
<%
	      }
      		if(request.getParameter("parentFolderId") == null){
%>
			<a href="welcome.jsp">Back to Space</a>
<%
	      	}else{
	      		%>
	      	<a href="welcome.jsp?parentFolderId=<%out.println(request.getParameter("parentFolderId"));%>">Back to Space</a>	
	      		<%
	      	}
	    }
	  }
	}
}
%>

    </p>
  </body>
</html>