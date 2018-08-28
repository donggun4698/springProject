<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
		<div id="header-wrapper">
			<div id="header">
				<a href="home"><img alt="logo" class="logo" src="<c:url value="/resources/logo.png"/>">
					</a>
				<!--상단내비   -->
				<nav id="nav">
					<ul>
						<li class="gnb1">
							<p class="gnb1_text">
								<a href="club/intro">소개</a>
							</p>
						</li>
						<li class="gnb2">
							<p class="gnb2_text">
								<a href="home">경기정보</a>
							</p>
						</li>
						<li class="gnb3">
							<p class="gnb3_text">
								<a href="#">기록</a>
							</p>
						</li>
						<li class="gnb4">
							<p class="gnb4_text">
								<a href="#">선수단</a>
							</p>
						</li>
						<li class="gnb5">
							<p class="gnb5_text">
								<a href="#">팬</a>
							</p>
						</li>
						<li class="gnb6">
							<p class="gnb6_text">
								<a href="#">미디어</a>
							</p>
						</li>
					</ul>
				</nav>
				<!--상단내비   -->
			</div>
		</div>

		<div id="container-wrapper">
			<div class="contents-wrpper">
				<div id="banner">
				</div>
			</div>
		</div>
		
		<!--footer   -->
		<div id="footer-wrapper">
			<div id="footer">
				<img src="<c:url value="/resources/logo3.png"/>" alt="logo" class="logo2">
				<div class="copyright">
					<address>
						<span>
							Copyright © 2018. ChangWon Spark All rights reserved.	
						</span>
					</address>
				</div>
			</div>
		</div>
		<!--footer   -->

	</div>
</body>
</html>