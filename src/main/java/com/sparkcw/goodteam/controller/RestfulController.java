package com.sparkcw.goodteam.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sparkcw.goodteam.dto.Member;




/**
 * Handles requests for the application home page.
 */
@RestController
public class RestfulController {
	
	private static final Logger logger = LoggerFactory.getLogger(RestfulController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */ 
	       
	
	@RequestMapping(value = "/club/intro", produces="application/json" ,method = RequestMethod.GET)
	public Member home1(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		Member us = new Member("aa11","11","홍길동");
		
		return us;
	}
	
}
