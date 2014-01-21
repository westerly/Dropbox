<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="beans.User"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>

	<div id="folder">
		
	</div>
	
	<div id="errorMessageFolder">
	
	</div>
	
	New folder : <input type="text" name="folderName" /> <input type="button" id="createFolder" value="Create" />
	
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
						$html += "<input type=\"button\" class=\"folderButton\" value=\"Delete\" name=\"" + folderName + "\"></p>";
						$("#folder").append($html);
			
		        	});
		        	
		        	
	    			$( ".folderButton" ).click(function() {
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
						        		$("#errorMessageFolder").append(res["error"]);
						        	}
						        }
						  });      	
						  
	    			});
	    			
	        	}else{
	        		$("#folder").append("<p>Problem when we tried to load your folders.</p>");
	        	}
	        }
	    }
	  );
		
	$( "#createFolder" ).click(function() {
		
		$.ajax( {
	        type: "PUT",
	        url: "services/service/createFolder/" + $( "input[name='folderName']" ).val() + "/" + parentFolderId,
	        dataType: "JSON",
	        success: function(fold) { 
	        	console.log(fold);
	        	if(fold != null){
	        		var folderName = $( "input[name='folderName']" ).val();
	        		var $html = "<p id=\""+folderName+"\"><a href=\"./welcome.jsp?parentFolderId="
					+ fold["folderId"] + "\">" + folderName + "</a>";
					$html += "<input type=\"button\" class=\"folderButton\" value=\"Delete\" name=\"" + folderName + "\"></p>";
	        		$("#folder").append($html);
	        	}else{
	        		$("#errorMessageFolder").append("Problem when we tried to create your folder.");
	        	}
	        }
		});
	});
		
	
	</script>
	

</body>
</html>