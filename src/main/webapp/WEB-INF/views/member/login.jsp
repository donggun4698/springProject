<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>스파크</title>
</head>
<body>
	<h1>로그인</h1>
	
	<form method="post" action="j_spring_security_check">
		<table border="0" width="400px" cellspacing="0" cellpadding="0"
			class="joinData">
			<tr>
				<th><label for="id">아이디</label></th>
				<td><input  name="id" value="${id}" maxlength="20">
			</tr>
			<tr>
				<th><label for="pass">비밀번호</label></th>
				<td><input type="password" name="password" >
			</tr>
		
			<tr>
				<td colspan="2" align="center">
					<p class="btn">
						<input type="submit" name="submit" value="login">
						<a href="/main">홈으로</a>
					</p>
				</td>
			</tr>
			
			<c:if test="${not empty param.fail}">
				<tr>
				<td colspan="2" align="center">
					<font color="red">
						<p>not successful, try again</p>
						reason: ${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}
					</font>
					<c:remove scope="session" var="SPRING_SECURITY_LAST_EXCEPTION" />
				</td>
			</tr>
			</c:if>
		</table>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		<input type="hidden" name="loginRedirect" value="${param.loginRedirect}" />
		</form>
</body>
</html>