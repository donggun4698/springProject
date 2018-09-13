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
		if($("#idFlag").val()=="N"){
				alert("올바른 아이디가 입력되지않았습니다.");
				return;
			}

		if($("#nicknameFlag").val()=="N"){
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
 	 			error : function(){
 		    	   alert("등록에 실패했습니다.");
 		       	}
			});
	});

	function showError(location , message){
		location.text(message);
		location.css("color","red");
		location.css("font-size","14px");
	}

	function showSuccess(location , message){
		location.text(message);
		location.css("color","green");
		location.css("font-size","14px");
	}
	
 function idCheck(){
		var msgLocation = $("#idCheckStatus");
		var id = $("#id").val();

		if(id ==""){
			showError(msgLocation , "아이디가 입력되지 않았습니다.");
			return false;
			}

		var chkPattern = /^[a-z0-9]{5,20}$/;
		if(!chkPattern.test(id)){
			showError(msgLocation, "사용자 ID는 5~20자 사이의 영문 소문자, 숫자만 사용가능합니다.");
			return false;
			}

		var formData = new Object();
		formData.id = id;
		var data = JSON.stringify(formData);
		
		$.ajax({
			url: "/check/member/id",
			dataType: "json",
			contentType :"application/json;charset=UTF-8",
			type:"post",
			data: data,
 			success : function(json){
 	 			if(json.result =="success"){
 	 				$("#idFlag").val("Y");
 	 				showSuccess(msgLocation,json.message);
 	 	 			}
 	 			else{
 	 				showError(msgLocation,json.message);
 	 	 			} 
				
 	 			},
 	 			error : function(){
 		    	   alert("중복 검사에 실패했습니다.");
 		       	}
			});
 	}
 
	$("#id").change(function(){
		$("#idFlag").val("N");
		idCheck();
	});      

	function nicknameCheck(){

		var msgLocation = $("#nicknameCheckStatus");
		var nickname = $("#nickname").val();

		if(nickname ==""){
			showError(msgLocation , "닉네임이 입력되지 않았습니다.");
			return false;
			}

		var chkPattern = /^[ㄱ-ㅎ가-힣a-zA-Z0-9]{2,10}$/;
		if(!chkPattern.test(nickname)){
			showError(msgLocation , "닉네임은 2~10자 사이의 한글, 숫자, 영문 대 소문자만 사용가능합니다. (공백,특수기호 사용불가)");
			return false;
			}

		var formData = new Object();
		formData.nickname = nickname;
		var data = JSON.stringify(formData);
		$.ajax({
			url: "/check/member/nickname",
			dataType: "json",
			contentType :"application/json;charset=UTF-8",
			type:"post",
			data: data,
 			success : function(json){
 	 			if(json.result =="success"){
 	 				$("#nicknameFlag").val("Y");
 	 				showSuccess(msgLocation,json.message);
 	 	 			}
 	 			else{
 	 				showError(msgLocation,json.message);
 	 	 			}
				         
 	 			},
 	 			error : function(){
 		    	   alert("중복 검사에 실패했습니다.");
 		       	}
			});
		} 

	$("#nickname").change(function(){
		$("#nicknameFlag").val("N");
		nicknameCheck();
	});

	function pwCheck(){

		var msgLocation = $("#pwCheckStatus");
		var pw = $("#pw").val();

		if(pw ==""){
			showError(msgLocation , "비밀번호가 입력되지 않았습니다.");
			return false;
			}

		var chkPattern =  /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,16}$/;
		if(!chkPattern.test(pw)){
			showError(msgLocation , "8~16자 영문 대 소문자, 숫자, 특수문자만 사용가능합니다.");
			return false;
			}

		var formData = new Object();
		formData.pw = pw;
		var data = JSON.stringify(formData);
		$.ajax({
			url: "/check/member/pw",
			dataType: "json",
			contentType :"application/json;charset=UTF-8",
			type:"post",
			data: data,
 			success : function(json){
 	 			if(json.result =="success"){
 	 				$("#pwFlag").val("Y");
 	 				showSuccess(msgLocation,json.message);
 	 	 			}
 	 			else{
 	 				showError(msgLocation,json.message);
 	 	 			}
				         
 	 			},
 	 			error : function(){
 		    	   alert("다시 시도해주세요");
 		       	}
			});
		} 

	$("#pw").change(function(){
		$("#pwFlag").val("N");
		$("#pwRecheckFlag").val("N");
		pwCheck();
	});
	
	function pwRecheck(){
		var msgLocation = $("#pwRecheckStatus");
		var pw = $("#pw").val();
		var pw2 = $("#pw2").val();
			
		if(pw2 == ""){
			showError(msgLocation , "비밀번호 재확인이 필요합니다.");
			$("#pwRecheckFlag").val("N");
			return false;
			}

		if(!(pw ==pw2)){
				pw2 = $("#pw2").val("");
				showError(msgLocation , "비밀번호가 일치하지 않습니다.");
				$("#pwRecheckFlag").val("N");
				return false;
			}
			showSuccess(msgLocation , "");
			$("#pwRecheckFlag").val("Y");
		}

	$("#pw2").change(function(){
		pwRecheck();
	});
	
	
});
</script>
<body>
<form method="post" id="join-form" >
		<table border="1" >
			<tr>
				<th><label for="id">아이디</label></th>
				<td><input type="text" name="id" id="id"  maxlength="20">
					<p id="idCheckStatus"></p>
					<input type="hidden" id="idFlag"  value="N"> </td>
			</tr>
			<tr>
				<th><label for="pw">비밀번호</label></th>
				<td><input type="password" name="pw" id="pw" maxlength="16">
				<p id="pwCheckStatus"></p>
				<input type="hidden" id="pwFlag"  value="N"></td>
				
			</tr>
			<tr>
				<th><label for="pw2">비밀번호 재확인</label></th>
				<td><input type="password" name="pw2" id="pw2" maxlength="16" >
				<p id="pwRecheckStatus"></p>
					<input type="hidden" id="pwRecheckFlag"  value="N"></td></td>
			</tr>
			<tr>
				<th><label for="name">이름</label></th>
				<td><input type="text" name="name" ></td>
			</tr>
			<tr>
				<th><label for="nickname">닉네임</label></th>
				<td><input type="text" name="nickname" id="nickname" maxlength="10" >
				<p id="nicknameCheckStatus"></p>
					<input type="hidden" id="nicknameFlag"  value="N"> </td>
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