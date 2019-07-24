package com.sparkcw.goodteam.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

	private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationFailureHandler.class);
	
	/* 로그인 페이지 폼에 입력한 id를 가지고 오는 input 태그의 name 값*/ 
	private String formId; 
	/* 로그인에 실패한 이유를 보여줄 키값 */ 
	private String failMessage;
	/* 로그인 실패시 보여줄 기본URL*/ 
	private String defaultUrl;
	/* 로그인 성공시 이동할 URL */
	private String loginRedirect;
	
	private RequestCache requestCache = new HttpSessionRequestCache();
	
	public String getLoginRedirect() {
		return loginRedirect;
	}

	public void setLoginRedirect(String loginRedirect) {
		this.loginRedirect = loginRedirect;
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public String getFailMessage() {
		return failMessage;
	}

	public void setFailMessage(String failMessage) {
		this.failMessage = failMessage;
	}

	public String getDefaultUrl() {
		return defaultUrl;
	}

	public void setDefaultUrl(String defaultUrl) {
		this.defaultUrl = defaultUrl;
	}

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		String loginid = request.getParameter(formId);
		String redirect = request.getParameter(loginRedirect);

		request.setAttribute(formId, loginid);
		request.setAttribute(failMessage, exception.getMessage());
		request.setAttribute(loginRedirect, redirect);
		logger.info(request.getAttribute(loginRedirect).toString());
		request.getRequestDispatcher(defaultUrl).forward(request, response);
	}

}
