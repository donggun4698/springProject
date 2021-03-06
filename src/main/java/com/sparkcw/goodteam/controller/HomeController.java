package com.sparkcw.goodteam.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	  
private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	
	@RequestMapping(value = {"/main", "/home"}, method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		return "main";
	} 
	   
	@RequestMapping(value = {"/test2"}, method = RequestMethod.GET)
	public String test2(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		return "test2";
	} 
	
	@RequestMapping(value = {"/test3"}, method = RequestMethod.GET)
	public String test3(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		return "test3";
	} 
//	@RequestMapping(value = "/club/intro", method = RequestMethod.GET)
//	public String home1(Locale locale, Model model) {
//		logger.info("Welcome home! The client locale is {}.", locale);
//		
//		Date date = new Date();
//		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
//		
//		String formattedDate = dateFormat.format(date);
//		
//		model.addAttribute("serverTime", formattedDate );
//		 
//		return "include/home";
//	}
	
	/*
	 * 회원가입 페이지로 이동합니다.
	 */
	@RequestMapping(value="/member/join", method = RequestMethod.GET)
	public String join() {
		return "join";
	}
	
	/*
	 * 로그인 페이지로 이동합니다.
	 */
	@RequestMapping(value="/member/login" )
	public String login() {
//		String redirect = request.getParameter("loginRedirect");
//		if(StringUtils.isEmpty(redirect)) {
//			redirect = request.getHeader("referer");
//			logger.info(redirect);
//			request.setAttribute("loginRedirect", redirect);
//		}
		
		return "login";
	}
}
