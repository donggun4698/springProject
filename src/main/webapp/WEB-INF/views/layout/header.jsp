<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery-3.3.1.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$("#test1").click(function(){
		 $.ajax({  
			 url: "/club/intro",
			dataType: "json",
			type:"GET",
 			success : function(a){
 	 			console.log(a.id);
				alert("성공");
 	 			},
 	 			error : function(result){
 		    	   alert("Upload Failed - Or Access Denied");
 		       	}
		});
	});

});


</script>
<body>
	<div id="header_wrapper">
		<div id="header"> 
			<a href="/main"><img
				src="${pageContext.request.contextPath}/resources/logo.png"
				alt="logo" class="logo"></a>
			<ul class="nav1">
				<li class="gnb1">
					<p class="gnb1_text">
						<a href="/club/intro">소개</a>
					</p>
				</li>
				<li class="gnb2">
					<p class="gnb2_text">
						<a href="#" id="test1">경기정보</a>
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
			<!-- 					로그인 -->
			<div class="login_wrapper">
				<div class="login_before">
					<a href="/member/login" class="login_before_text">로그인</a>
					<a href="/member/join" class="login_before_text">회원가입</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>