<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %> --%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="initial-scale=1, width=device-width" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/normalize.css" />">
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/header.css" />">
</head>
<body>
	<div id="wrapper">
		<tiles:insertAttribute name="header" />

		<tiles:insertAttribute name="body" />

		<tiles:insertAttribute name="footer" />
	</div>
</body>
</html>