<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery-3.3.1.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	jQuery.fn.serializeObject = function() {
	    var obj = null;
	    try {
	        if (this[0].tagName && this[0].tagName.toUpperCase() == "FORM") {
	            var arr = this.serializeArray();
	            if (arr) {
	                obj = {};
	                jQuery.each(arr, function() {
	                    obj[this.name] = this.value;
	                });
	            }
	        }
	    } catch (e) {
	        alert(e.message);
	    } finally {
	    }
	 
	    return obj;
	};

	$("#submitBt").click(function(){
		if($("#id_Check").val()=="N"){
				alert("올바른 아이디가 입력되지않았습니다.");
				return;
			}

		if($("#nickname_Check").val()=="N"){
			alert("올바른 닉네임이 입력되지않았습니다.");
			return;
		}
		
		var formData = $("#join-form").serializeObject();
		var data = JSON.stringify(formData);
		$.ajax({
			url: "/member",
			dataType: "json",
			contentType :"application/json;charset=UTF-8",
			type:"post",
			data: data,
 			success : function(json){
 	 			if(json.result =="success"){
				console.log(formData);
 	 				alert("가입성공");
 	 	 			}
 	 			else{
 	 				alert(json.message);
 	 	 			}
				
 	 			},
 	 			error : function(result){
 		    	   alert("등록에 실패했습니다.");
 		       	}
			});
	});

 function idCheck(){
		var formData = new Object();
		formData.id = $("#id").val();
		var data = JSON.stringify(formData);
		$.ajax({
			url: "/check/member/id",
			dataType: "json",
			contentType :"application/json;charset=UTF-8",
			type:"post",
			data: data,
 			success : function(json){
 	 			if(json.result =="success"){
 	 				$("#id_Check").val("Y");
 	 				$("#idCheckStatus").text(json.message);
 	 				$("#idCheckStatus").css("color","blue");
 	 	 			}
 	 			else{
 	 				$("#id_Check").val("N")
 	 				$("#idCheckStatus").text(json.message);
 	 				$("#idCheckStatus").css("color","red");
 	 	 			}
				
 	 			},
 	 			error : function(result){
 		    	   alert("중복 검사에 실패했습니다.");
 		       	}
			});
 	}
 
	$("#id").change(function(){
		idCheck();
	});      

	function nicknameCheck(){
		var formData = new Object();
		formData.nickname = $("#nickname").val();
		var data = JSON.stringify(formData);
		$.ajax({
			url: "/check/member/nickname",
			dataType: "json",
			contentType :"application/json;charset=UTF-8",
			type:"post",
			data: data,
 			success : function(json){
 	 			if(json.result =="success"){
 	 				$("#nickname_Check").val("Y");
 	 				$("#nicknameCheckStatus").text(json.message);
 	 				$("#nicknameCheckStatus").css("color","blue");
 	 	 			}
 	 			else{
 	 				$("#nickname_Check").val("N")
 	 				$("#nicknameCheckStatus").text(json.message);
 	 				$("#nicknameCheckStatus").css("color","red");
 	 	 			}
				         
 	 			},
 	 			error : function(result){
 		    	   alert("중복 검사에 실패했습니다.");
 		       	}
			});
		} 

	$("#nickname").change(function(){
		nicknameCheck();
	});
	
});
</script>
<body>
<form method="post" id="join-form" >
		<table border="1" >
			<tr>
				<th><label for="id">아이디</label></th>
				<td><input type="text" name="id" id="id"  maxlength="20">
					<p>사용자 ID는 3~20자 사이의 영문+숫자로 이루어져야 하며 영문으로 시작되어야 합니다.</p>
					<p id="idCheckStatus"></p>
					<input type="hidden" id="id_Check"  value="N"> </td>
			</tr>
			<tr>
				<th><label for="pw">비밀번호</label></th>
				<td><input type="password" name="pw" id="pw" class="textBox">
				<p>8~15영문숫자조합</p></td>

			</tr>
			<tr>
				<th><label for="name">이름</label></th>
				<td><input type="text" name="name" ></td>
			</tr>
			<tr>
				<th><label for="nickname">닉네임</label></th>
				<td><input type="text" name="nickname" id="nickname" >
				<p id="nicknameCheckStatus"></p>
					<input type="hidden" id="nickname_Check"  value="N"> </td>
			</tr>
			<tr>
				<th><label for="phone">휴대폰번호</label></th>
				<td><input type="text" name="phone"  ></td>
			</tr>
			<tr>
				<th><label for="email">이메일주소</label></th>
				<td><input type="text" name="email"  ></td>
			</tr>
			<tr>
				<th><label for="birthday">생일</label></th>
				<td><input type="text" name="birthday"></td>
			</tr>
			<tr>
				<th><label for="sex">성별</label></th>
				<td><input type="radio" name="sex" value="M" >남자 
					<input type="radio" name="sex" value="F" >여자</td>
			</tr>
			
			<tr>
				<td colspan="2" align="center">
					<p class="btn">
						<input type="button" id="submitBt"  value="회원가입"> 
						<input type="button" onclick="history.back()"  value="취소">
					</p>
				</td>
			</tr>
		</table>
<%-- 		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> --%>
	</form>
</body>
</html>