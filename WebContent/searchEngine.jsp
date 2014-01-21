<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="service.services"%>
<%@ page import="java.util.*"%>
<script type="text/javascript" src="jquery-1.10.2.min.js"></script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Page</title>
</head>
<body>
	<input id="searchText" type="text" />
	<button id="launch">Search</button>
	<input id="fieldFile" type="checkbox">File
	<input id="fieldFolder" type="checkbox">Folder
	<input id="fieldParent" type="checkbox">Parent
	<input id="fieldSpace" type="checkbox">Space
	<input id="fieldLogin" type="checkbox">Login
	<input id="fieldId" type="checkbox">Id
	<input id="fieldName" type="checkbox">Name
	<br>

	<textarea id="searchResults" cols="50" rows="15"></textarea>
	<script>
	$(document).ready(function(){
		$("#launch")
				.click(
						function() {
							var content = $("#searchText").val();
							var searchMode = "";
							var login = "";
							var finished = false;
							if (($("#fieldFile").prop('checked') && $(
									"#fieldId").prop('checked')) == true && finished == false && !$("#fieldParent").prop('checked')) {
								searchMode = "getFileById";
								finished = true;
							}
							
							else if (($("#fieldFile").prop('checked') && $(
									"#fieldName").prop('checked')) == true && finished == false && !$("#fieldParent").prop('checked')
									&& !$("#fieldLogin").prop('checked')) {
								searchMode = "getFileByName";
								finished = true;
							}
							
							else if (($("#fieldFile").prop('checked')
									&& $("#fieldId").prop('checked') && $(
									"#fieldParent").prop('checked')) == true && finished == false) {
								searchMode = "getFilesFromParentId";
								finished = true;
							} 
							else if (($("#fieldFile").prop('checked') && $("#fieldParent").prop('checked') 
									&& $("#fieldSpace").prop('checked')) == true && finished == false){
								searchMode = "getFilesFromNamespaceParent";
								finished = true;
							}
							else if (($("#fieldFolder").prop('checked') && $("#fieldParent").prop('checked') 
									&& $("#fieldSpace").prop('checked')) == true && finished == false){
								searchMode = "getParentsFoldersFromNameSpace";
								finished = true;
							}
							else if (($("#fieldFile").prop('checked') && $("#fieldParent").prop('checked') 
									&& $("#fieldId").prop('checked')) == true && finished == false){
								searchMode = "getFilesFromParentId";
								finished = true;
							}
							else if (($("#fieldFolder").prop('checked') && $("#fieldParent").prop('checked') 
									&& $("#fieldId").prop('checked')) == true && finished == false){
								searchMode = "getParentFoldersFromFile";
								finished = true;
							}
							else if (($("#fieldFile").prop('checked')
									&& $("#fieldName").prop('checked') && $(
									"#fieldLogin").prop('checked')) == true && finished == false) {
								var data = content.split("/");
								content = data[0];
								login ="/"+ data[1];
								searchMode = "getFilesByNameAndUser";
								finished = true;
							}
							else {
								searchMode = "";
								$("#searchResults")
										.text(
												"Your search doesn't make any sense, try again");
							}
							$.ajax({
								type : "GET",
								url : "services/service/" + searchMode + "/"
										+ content + login,
								dataType : "text",
								success : function(xml) {
									if (xml != null) {
										$("#searchResults").text(xml);
										console.log(xml);
									}
								}
							});
						});
	});
	</script>

</body>
</html>