<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery-3.3.1.js"?v=<%=System.currentTimeMillis() %>></script>
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

	$(".nav li").mouseenter(function() {
	  	$(this).find(".menu-sub").show();
		});
		
	$(".nav li").mouseleave(function() {
		  $(this).find(".menu-sub").hide();
		});

	
});


</script>
<body>
	<div id="header_wrapper">
		<div id="header"> 
			<a href="/main"><img src="${pageContext.request.contextPath}/resources/logo.png" alt="logo" class="logo"></a>
			<ul class="nav">
				<li class="gnb_1">
					<a href="/club/intro">스파크</a>
					<div class="menu-sub" style="display : none">
				 <ul>
              		<li>
                		<a href="#" >서브1</a>            
              		</li>
              		<li>
                		<a href="#" >서브2</a>               
              		</li>
            	 </ul>
            	 </div>
				</li>
				<li class="gnb_2">
					<p class="gnb2_text">
						<a href="#" id="test1">경기정보</a>
					</p>
				</li>
				<li class="gnb_3">
					<a href="/test2" id="test2">기록</a>
				 <ul class="menu-sub" style="display : none">
              		<li>
                		<a href="" >서브1</a>            
              		</li>
              		<li>
                		<a href="#" >서브2</a>               
              		</li>
            	 </ul>
				</li>
				<li class="gnb4">
					<p class="gnb4_text">
						<a href="/test3">선수단</a>
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