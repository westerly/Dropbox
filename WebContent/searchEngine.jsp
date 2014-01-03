<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Page</title>
</head>
<body>
	<form method="get" action="./SearchEngine">
		Search in Data Base: <input type="search" name="searchBox"> <select name="searchMode">
			<option value="ALL">All</option>
			<option value="NAME">Name</option>
			<option value="TAG">Tag</option>
			<option value="ID">Id</option>
		</select> <input type="submit">
	</form>
</body>
</html>