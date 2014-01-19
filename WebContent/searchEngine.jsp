<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="service.services"%>
<%@ page import="java.util.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script lang="text/javascript">
	function daleMoreno() {
		var stringToSearch = document.getElementById("searchText");
		var fileActivated = document.getElementById("fieldFile");
		var idActivated = document.getElement.ById("fieldId").checked;
		var nameActivated = document.getElementById("fielName").checked;
		var tagActivated = document.getElementById("fieldTag").checked;
		var fileNameActivated = document.getElementById("fieldFileName").checked;
		var loginActivated = document.getElementById("fieldLogin").checked;
		var spacenameActivated = document.getElementById("fieldSpaceName").checked;

		if ((fileActivated && idActivated) == true) {
			var aux = <%services.getFilesById(%>stringToSearch<%);%>
	document.getElementById("heVistoLaLuz").innerHTML = aux;
		}

	}
</script>
<title>Search Page</title>
</head>
<body>
	<input id="searchText" type="text">
	<input id="fieldFile" type="checkbox">
	<input id="fieldId" type="checkbox">
	<input id="fieldTag" type="checkbox">
	<input id="fieldFileName" type="checkbox">
	<input id="fieldLogin" type="checkbox">
	<input id="fieldName" type="checkbox">
	<input id="fieldSpaceName" onclick="daleMoreno()" type="checkbox">

	<p id="heVistoLaLuz"></p>

</body>
</html>