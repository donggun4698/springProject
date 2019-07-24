package com.sparkcw.goodteam.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.client.RestTemplate;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationSuccessHandler.class);
	
	/* 로그인 성공시 보여줄 기본URL*/ 
	private String defaultUrl;
	/* 로그인 성공시 이동할 URL */
	private String loginRedirect;
	/* spring security가  화면이동에 대해 정의한 인터페이스 */
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	/* 로그인 화면을 보여주기전에 사용차 요청을 저장 및 가져온다. */
	private RequestCache requestCache = new HttpSessionRequestCache();
	
	public String getDefaultUrl() {
		return defaultUrl;
	}

	public void setDefaultUrl(String defaultUrl) {
		this.defaultUrl = defaultUrl;
	}

	public String getLoginRedirect() {
		return loginRedirect;
	}

	public void setLoginRedirect(String loginRedirect) {
		this.loginRedirect = loginRedirect;
	}
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		clearAuthenticationAttributes(request);
		
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		String redirectUrl = request.getParameter(loginRedirect);
		
		if(StringUtils.isNotBlank(redirectUrl)){
			logger.info("1");
			redirectUrl(request, response);
		}else if(savedRequest != null){
			logger.info("2");
			savedRequestUrl(request, response);
		}else {
			logger.info("3");
			defaultUrl(request, response);
		}
	}

	/**
	 * 인증과정에서 생긴 오류를 저장한 세션을 비워준다.
	 * @param request
	 */
	private void clearAuthenticationAttributes(HttpServletRequest request){
		HttpSession session = request.getSession(false);
		
		if(session == null){
			return;
		}
		
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}
	
	/**
	 * 기본적으로 지정된 페이지로 이동한다.
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void defaultUrl(HttpServletRequest request, HttpServletResponse response) throws IOException {
		redirectStrategy.sendRedirect(request, response, defaultUrl);
	}
	
	/**
	 * spring security가 시스템적으로 로그인페이지를 띄우는 경우에는
	 * 이전페이지에 대한 URL과 헤더정보를 저장하는데 이를 통해 이전페이지로 이동한다.
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void savedRequestUrl(HttpServletRequest request, HttpServletResponse response) throws IOException {
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		String redirectUrl = savedRequest.getRedirectUrl();
		redirectStrategy.sendRedirect(request, response, redirectUrl);
	}
	
	/**
	 * 받아온 URL로 페이지를 이동시킵니다.
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void redirectUrl(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String redirectUrl = request.getParameter(loginRedirect);
		redirectStrategy.sendRedirect(request, response, redirectUrl);
	}
}
