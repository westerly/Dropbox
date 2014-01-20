<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="java.util.ArrayList"%>
<%@ page import="beans.Folder"%>
<%@ page import="beans.User"%>
<script type="text/javascript" src="jquery-1.10.2.min.js"></script>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>

	<div id="folder">
		
	</div>
	
	<div id="errorMessage">
	
	</div>
	
	<script>
	
		function getURLParameters(url){
	
		    var result = {};
		    var searchIndex = url.indexOf("?");
		    if (searchIndex == -1 ) return result;
		    var sPageURL = url.substring(searchIndex +1);
		    var sURLVariables = sPageURL.split('&');
		    for (var i = 0; i < sURLVariables.length; i++)
		    {       
		        var sParameterName = sURLVariables[i].split('=');      
		        result[sParameterName[0]] = sParameterName[1];
		    }
		    return result;
		}
		

		var currentUrl = $(location).attr('href');
		var params = new Array();
		params = getURLParameters(currentUrl);
		
		var parentFolderId = "";
		if(params["parentFolderId"] != undefined){
			parentFolderId = params["parentFolderId"];
		}
		
		foldername="";
		
		$.ajax( {
	        type: "GET",
	        url: "services/service/getFoldersFromParentId/" + parentFolderId,
	        dataType: "text",
	        success: function(xml) { 
	        	
	        	if(xml != null){
	        		//alert(xml);
	        		
		        	$(xml).find('folder').each( function(){
		        		folderName = $(this).find("folderName").text();
		        		console.log(window.foldername);
		        		var $html = "<p id=\""+folderName+"\"><a href=\"./welcome.jsp?parentFolderId="
							+ $(this).find("folderId").text() + "\">" + folderName + "</a>";
						$html += "<input type=\"button\" value=\"Delete\" name=\"" + folderName + "\"></p>";
						$("#folder").append($html);
			
		        	});
		        	
		        	
	    			$( ":button" ).click(function() {
	    				  //console.log($(this).get(0).name);
						  
						  params = getURLParameters(currentUrl);
						  var parent;
						  if(params["parentFolderId"] == undefined){
							  <%
		    				  User userCo = (User) request.getSession().getAttribute("user");
		    				  %>
							  parent = <%out.println("\"" + userCo.getNameSpace() + "\"");%>
							  //console.log(namespace);
						  }else{
							  parent = params["parentFolderId"];
						  }
						  
						  folderNameClicked = $(this).get(0).name;
						  $.ajax( {
						        type: "DELETE",
						        url: "services/service/deleteFolder/" + folderNameClicked + "/" + parent,
						        dataType: "JSON",
						        success: function(res) { 
						        	if(res["res"] == "true"){
						        		$("#"+folderNameClicked).remove();
						        	}else{
						        		$("#errorMessage").append(res["error"]);
						        	}
						        }
						  });      	
						      
						  
						  //console.log(parent);
						  
	    			});
		    	
		        	
	        	}else{
	        		$("#folder").append("<p>Problem when we tried to load your folders.</p>");
	        	}
	        }
	    }
	  );  
	
	
	</script>

	<%-- <%
		ArrayList<Folder> folders = (ArrayList) request.getAttribute("folders");
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
	%> --%>

</body>
</html>