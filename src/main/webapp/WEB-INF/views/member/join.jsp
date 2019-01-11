<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/jquery-ui-1.11.4/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="/resources/css/join.css">
</head>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/jquery-3.3.1.js"></script>
	<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/jquery-ui-1.11.4/jquery-ui.js"></script>
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
				alert("아이디를 확인해주세요.");
				return;
			}
		if($("#pwFlag").val()=="N"){
			alert("비밀번호를 확인해주세요.");
			return;
		}
		if($("#pwRecheckFlag").val()=="N"){
			alert("비밀번호 재확인이 확실하지않습니다..");
			return;
		}
		if($("#nameFlag").val()=="N"){
			alert("이름을 확인해주세요.");
			return;
		}
		if($("#nicknameFlag").val()=="N"){
			alert("올바른 닉네임이 입력되지않았습니다.");
			return;
		}
		if($("#phoneFlag").val()=="N"){
			alert("휴대폰번호 확인이 필요합니다.");
			return;
		}
		if($("#emailFlag").val()=="N"){
			alert("이메일주소 확인이 필요합니다.");
			return;
		}
		if($("#birthdayFlag").val()=="N"){
			alert("생년월일의 확인이 필요합니다.");
			return;
		}
		if(!$("input:radio[name='sex']").is(":checked")){
			alert("성별체크의 확인이 필요합니다.");
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
 			success : function(data){
 	 			if(data.result =="success"){
 	 				alert("가입성공");
 	 				location.replace("/main");
 	 	 			}
 	 			else{
 	 				alert(data.message);
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
 			success : function(data){
 	 			if(data.result =="success"){
 	 				$("#idFlag").val("Y");
 	 				showSuccess(msgLocation,data.message);
 	 	 			}
 	 			else{
 	 				showError(msgLocation,data.message);
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
 			success : function(data){
 	 			if(data.result =="success"){
 	 				$("#nicknameFlag").val("Y");
 	 				showSuccess(msgLocation,data.message);
 	 	 			}
 	 			else{
 	 				showError(msgLocation,data.message);
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

		showSuccess(msgLocation , "");
		$("#pwFlag").val("Y");
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
			return false;
			}

		if(!(pw ==pw2)){
			pw2 = $("#pw2").val("");
			showError(msgLocation , "비밀번호가 일치하지 않습니다.");
			return false;
		}
		
			showSuccess(msgLocation , "");
			$("#pwRecheckFlag").val("Y");
		}

	$("#pw2").change(function(){
		$("#pwRecheckFlag").val("N");
		pwRecheck();
	});

	function nameCheck(){
		var msgLocation = $("#nameCheckStatus");
		var name = $("#name").val();
			
		if(name == ""){
			showError(msgLocation , "이름이 입력되지 않았습니다.");
			return false;
			}

		var chkPattern = /^[가-힣a-zA-Z]{0,40}$/;
		if(!chkPattern.test(name)){
			showError(msgLocation , "한글, 영문 대 소문자만 사용가능합니다. (공백,특수기호 사용불가)");
			return false;
			}
		
			showSuccess(msgLocation , "");
			$("#nameFlag").val("Y");
		}

	$("#name").change(function(){
		$("#nameFlag").val("N");
		nameCheck();
	});

	$("#birthday").datepicker({
        changeMonth: true,
        changeYear: true,
        minDate: '-100y', 
        maxDate: '0',
        nextText: '다음 달', 
        prevText: '이전 달', 
        yearRange: 'c-100:c+100', 
        dateFormat: "yy-mm-dd", 
        showAnim: "slide", 
        showMonthAfterYear: true ,  
        dayNamesMin: ['월', '화', '수', '목', '금', '토', '일'],
        monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'],
        onClose: function(date) {
             $("#birthday").val(date);
        }
   });
	
	function birthdayCheck(){
		var msgLocation = $("#birthdayCheckStatus");
		var birthday = $("#birthday").val();
		
		if(birthday == ""){
			showError(msgLocation , "생년월일이 입력되지 않았습니다.");
			return false;
			}

		var chkPattern = /^(1|2)[0-9]{3}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[0-1])$/;
		if(!(chkPattern.test(birthday))){
			showError(msgLocation , "생년월일을 다시 확인해주세요.");
			return false;
			}

        var today = dateFormat(new Date());
		var birthday_dateType = strToDate(birthday);
		var today_dateType = strToDate(today);

		var diff = (today_dateType.getTime() - birthday_dateType.getTime())/ (1000*60*60*24);
	
		if(diff < 0){
			showError(msgLocation , "미래에서 오셨습니까?");
			return false;
			}
		
		today_dateType.setYear(today_dateType.getYear() - 100);
		
		var minimumBirth = (birthday_dateType.getTime() - today_dateType.getTime())/ (1000*60*60*24);

		if(minimumBirth < 0){
			showError(msgLocation , "정말입니까?");
			return false;
			}

		if(leapYearCheck(birthday) == false){
			showError(msgLocation , "생년월일을 다시 확인해주세요.");
			return false;
			}

		$("#birthdayFlag").val("Y");
		showSuccess(msgLocation , "");
	}

	$("#birthday").change(function(){
		$("#birthdayFlag").val("N");
		birthdayCheck();
	});
	
	function dateFormat(date){
	    function change(num) {
	        num = String(num);
	        return num.length < 2 ? '0' + num : num;
	    }
	    return date.getFullYear() + '-' + change(date.getMonth()+1) + '-' + change(date.getDate());
	}

	function strToDate(date){
		 var result = date.split("-");
	     result[1] = (Number(result[1]) - 1) + "";
	     
	     return new Date(result[0], result[1], result[2]);
	   }

	function leapYearCheck(date){
	 	   var month= [0 , 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
		   var birthday = date.split("-");
		   var bir_yy = Number(birthday[0]);
		   var bir_mm = Number(birthday[1]);
		   var bir_dd = Number(birthday[2]);
		   var leapYear = false;
		   
			if((bir_yy % 4 == 0 && bir_yy % 100 !=0) || bir_yy % 400 == 0 ){
				leapYear = true;
			}
			
			if(leapYear == false){
				if(bir_dd > month[bir_mm]){
					return false;
				}
			} else {
				if(bir_mm == 2){
					if(bir_dd > 29){
						return false;
						}
					}
				else if(bir_dd > month[bir_mm]){
					return false;
				}
			}
	 }

	function emailCheck(){
			var msgLocation = $("#emailCheckStatus");
			var email = $("#email").val();
				
			if(email == ""){
				showError(msgLocation , "이메일 주소가 입력되지 않았습니다.");
				return false;
				}

			var chkPattern = /(^[\w-]+)@(([\w-]+\.)+)([a-zA-Z]{2,}$)/;
			if(!chkPattern.test(email)){
				showError(msgLocation , "이메일 주소를 다시 확인해주세요.");
				return false;
				}
			
				showSuccess(msgLocation , "");
				$("#emailFlag").val("Y");
			}

		$("#email").change(function(){
			$("#emailFlag").val("N");
			emailCheck();
		});

		function phoneCheck(){
			var msgLocation = $("#phoneCheckStatus");
			var phone = $("#phone").val();
				
			if(phone == ""){
				showError(msgLocation , "휴대전화번호가 입력되지 않았습니다.");
				return false;
				}

			var chkPattern = /^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$/;
			if(!chkPattern.test(phone)){
				showError(msgLocation , "휴대전화번호를 다시 확인해주세요.");
				return false;
				}
			
				phone = phone.replace(/-/g,"");
				showSuccess(msgLocation , "");
				$("#phoneFlag").val("Y");
				$("#phone").val(phone);
			}

		$("#phone").change(function(){
			$("#phoneFlag").val("N");
			phoneCheck();
		});
});
</script>
<body>
	<div style="width: 970px; margin: 0 auto;">
	<h3>회원가입</h3>
	<div class="join" style="margin: 0 auto;">
	<form method="post" id="join-form">
		<table id="join-table">
			<tr>
				<th><div class="join_title"><label for="id">아이디</label></div></th>
				<td><input type="text" name="id" id="id" maxlength="20">
					<p id="idCheckStatus"></p> <input type="hidden" id="idFlag"
					value="N"></td>
			</tr>
			<tr>
				<th><div class="join_title"><label for="pw">비밀번호</label></div></th>
				<td><input type="password" name="pw" id="pw" maxlength="16">
					<p id="pwCheckStatus"></p> <input type="hidden" id="pwFlag"
					value="N"></td>

			</tr>
			<tr>
				<th><div class="join_title"><label for="pw2">비밀번호 재확인</label></div></th>
				<td><input type="password" name="pw2" id="pw2" maxlength="16">
					<p id="pwRecheckStatus"></p> <input type="hidden"
					id="pwRecheckFlag" value="N"></td>
			</tr>
			<tr>
				<th><div class="join_title"><label for="name">이름</label></div></th>
				<td><input type="text" name="name" id="name" >
					<p id="nameCheckStatus"></p> <input type="hidden" id="nameFlag"
					value="N"></td>
			</tr>
			<tr>
				<th><div class="join_title"><label for="nickname">닉네임</label></div></th>
				<td><input type="text" name="nickname" id="nickname"
					maxlength="10">
					<p id="nicknameCheckStatus"></p> <input type="hidden"
					id="nicknameFlag" value="N"></td>
			</tr>
			<tr>
				<th><div class="join_title"><label for="phone">휴대폰번호</label></div></th>
				<td><input type="text" name="phone" id="phone">
				<p id="phoneCheckStatus"></p> <input type="hidden"
					id="phoneFlag" value="N"></td>
			</tr>
			<tr>
				<th><div class="join_title"><label for="email">이메일주소</label></div></th>
				<td><input type="text" name="email" id="email">
				<p id="emailCheckStatus"></p>
				<input type="hidden" id="emailFlag" value="N"></td>
				
			</tr>
			<tr>
				<th><div class="join_title"><label for="birthday">생년월일</label></div></th>
				<td><input type="text" name="birthday" id="birthday" value="">
				<p id="birthdayCheckStatus"></p>
				<input type="hidden" id="birthdayFlag" value="N">
				</td>
			</tr>
			<tr>
				<th><div class="join_title"><label for="sex">성별</label></div></th>
				<td><label for="M"><input type="radio" name="sex" id="M" value="M">남자</label> 
					<label for="F"><input type="radio" name="sex" id="F" value="F">여자</label> </td>
			</tr>

			<tr>
				<td colspan="2" align="center">
					<p class="btn">
						<input type="button" id="submitBt" value="회원가입"> <input
							type="button" onclick="history.back()" value="취소">
					</p>
				</td>
			</tr>
		</table>
		<%-- 		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> --%>
	</form>
	</div>
	</div>
</body>
</html>