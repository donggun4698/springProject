<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<title>CW Spark</title>
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