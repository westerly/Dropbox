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
	<input id="fieldLogin" type="checkbox">Login
	<input id="fieldId" type="checkbox">Id
	<input id="fieldTag" type="checkbox">Tag
	<input id="fieldFileName" type="checkbox">File name
	<input id="fieldName" type="checkbox">Name
	<input id="fieldSpaceName" type="checkbox">Space name

	<textarea id="searchResults"></textarea>
	<script>
		$("#launch").click(function() {
			var content = $("#searchText").val();
			$.ajax({
				type : "GET",
				url : "services/service/getFileByName/" + content,
				dataType : "text",
				success : function(xml) {
					if (xml != null) {
						$("#searchResults").text(xml);
						console.log(xml);
					}
				}
			});
		});
	</script>

</body>
</html>