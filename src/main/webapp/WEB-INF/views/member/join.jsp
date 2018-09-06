<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<form method="post" action="users">
		<table border="1" >
			<tr>
				<th><label for="id">아이디</label></th>
				<td><input name="id" id="id"  maxlength="20">
			</tr>
			<tr>
				<th><label for="pass">비밀번호</label></th>
				<td><input type="password" name="password" id="pass" class="textBox">
				<p>8~15영문숫자조합</p></td>

			</tr>
			<tr>
				<th><label for="name">이름</label></th>
				<td><input name="name"  class="textBox"></td>
			</tr>
			<tr>
				<th><label for="email">이메일주소</label></th>
				<td><input name="email"   class="textBox"></td>
			</tr>
			
			
			<tr>
				<td colspan="2" align="center">
					<p class="btn">
						<input type="submit" class="check" value="회원가입"> 
						<input type="reset" class="check"  value="취소">
					</p>
				</td>
			</tr>
		</table>
<%-- 		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> --%>
	</form>
</body>
</html>