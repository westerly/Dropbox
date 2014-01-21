<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="beans.User"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="beans.File"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<body>

	<div id="file">
		
	</div>
	
	<div id="errorMessageFile">
	
	</div>
	
	<iframe id="downloadFrame" style="display:none"></iframe>


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
	        url: "services/service/getFilesFromParentId/" + parentFolderId,
	        dataType: "text",
	        success: function(xml) { 
	        	console.log(xml);
	        	if(xml != null){
		        	$(xml).find('file').each( function(){
		        		console.log(xml);
		        		fileName = $(this).find("fileName").text();
		        		var fileId = $(this).find("id").text();
		        		var $html = "<p id=\""+fileId+"\">" + fileName;
		        		$html += "  <input type=\"button\" class=\"fileButtonDownload\" value=\"Download\" name=\"" + fileName + "\">";
		        		$html += "  <input type=\"button\" class=\"fileButtonDelete\" value=\"Delete\" name=\"" + fileName + "\"></p>";
		        		//$html += "  <input type=\"button\" class=\"fileButtonChange\" value=\"Modify\" name=\"" + fileId + "\"></p>";
						$("#file").append($html);
		        	});
		        	
		        	$( ".fileButtonDownload" ).click(function() {
		        		
		        	  var currentUrl = $(location).attr('href');
		           	  var params = new Array();
		        	  params = getURLParameters(currentUrl);
		        		
		        	  var parentFolderId = "";
		        	  if(params["parentFolderId"] != undefined){
		        			parentFolderId = params["parentFolderId"];
		        	  }
						  
					  fileNameClicked = $(this).get(0).name;
					  
					  var iframe = document.getElementById("downloadFrame");
					  iframe .src = "services/service/download/" + fileNameClicked + "/" + parentFolderId;    	
						  
	    			});
		        	
		        	$( ".fileButtonDelete" ).click(function() {
						  
						  params = getURLParameters(currentUrl);
						  var parent;
						  if(params["parentFolderId"] == undefined){
							  <%
		    				  User userCo = (User) request.getSession().getAttribute("user");
		    				  %>
							  parent = <%out.println("\"" + userCo.getNameSpace() + "\"");%>
						  }else{
							  parent = params["parentFolderId"];
						  }
						  
						  fileNameClicked = $(this).get(0).name;
						  $.ajax( {
						        type: "DELETE",
						        url: "services/service/deleteFile/" + fileNameClicked + "/" + parent,
						        dataType: "JSON",
						        success: function(file) { 
						        	if(file != null){
						        		$("#"+file["Id"]).remove();
						        	}else{
						        		$("#errorMessageFile").append("Problem when we tried to remove your file.");
						        	}
						        }
						  });      	
						  
	    			});
		        	
		        	$( ".fileButtonChange" ).click(function() {
		        	/* 	var JsonFile = {"Id":$(this).get(0).name,
		        						"fileName":"test",
		        						"id_folder_parent":"",
		        						"name_space_parent":"",
		        						"size":""
		        						}; */
		        		
		        		var JsonFile = {"res":"true",
        								"error":"test"
        						};
						
		        		console.log($(this).get(0).name);
		        		//var jsonData = JSON.parse( JSONObject );
		        		
			        	$.ajax({ url: "services/service/changeFile", 
		        			 type: "POST", 
		        			 contentType: 'application/json', // type of data
		        			 //data: JSON.stringify(JsonFile), // make JSON string
		        			 data: '<?xml version="1.0" encoding="UTF-8"?><JaxBool><res>true</res><error>Erreur de merde</error></JaxBool>',
		        			 dataType: "application/xml",
		        			 success: function(file){ 
		        				
		        				console.log(file);
					        	/* if(file != null){
					        		$("#"+file["id"]).remove();
					        	}else{
					        		$("#errorMessageFile").append("Problem when we tried to remove your file.");
					        	} */
						     }
			        	});
		        	});
		        	
		        	
	        	}else{
	        		$("#file").append("<p>Problem when we tried to load your files.</p>");
	        	}
	        }
	   });	 
	
</script>

</body>
</html>