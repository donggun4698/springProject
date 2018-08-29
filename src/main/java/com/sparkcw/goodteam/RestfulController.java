package com.sparkcw.goodteam;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;




/**
 * Handles requests for the application home page.
 */
@RestController
public class RestfulController {
	
	private static final Logger logger = LoggerFactory.getLogger(RestfulController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */ 
	       
	
	@RequestMapping(value = "/club/intro",consumes="application/json" ,method = RequestMethod.GET)
	public UserDto home1(Locale locale, Model model) {
		logger.info("이동");
		UserDto us = new UserDto("ad","p1212w","role.s");
	
		return us;
	}
	
}
