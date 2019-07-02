package com.sparkcw.goodteam.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sparkcw.goodteam.dto.Member;
import com.sparkcw.goodteam.service.MemberService;




/**
 * Handles requests for the application home page.
 */
@RestController
public class RestfulController {
	
	private static final Logger logger = LoggerFactory.getLogger(RestfulController.class);
	
	@Autowired
	MemberService memberService;
	/**
	 * Simply selects the home view to render by returning its name.
	 */ 
	       
 
	@RequestMapping(value = "/club/intro", produces="application/json" ,method = RequestMethod.GET)
	public Member home1(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		Member us = new Member(213, "aa11", "11", "홍길동", "길동닉", "111-1111-1111", "aaa@na.com", LocalDate.parse("2020-08-12"), "M");
		return us;
	}

	@RequestMapping(value = "/member", produces="application/json" , method = RequestMethod.POST)
	public Map<String, Object> register(@RequestBody Member member) {
		Map<String, Object> result = new HashMap<String, Object>();
		result = memberService.registerMemberIdDuplicateCheck(member.getId());
		if(!result.containsValue("success")) {
			return result;
		}
		result = memberService.registerMemberPwCheck(member.getPw());
		if(!result.containsValue("success")) {
			return result;
		}
		result = memberService.registerMemberNameCheck(member.getName());
		if(!result.containsValue("success")) {
			return result;
		}
		result = memberService.registerMemberNicknameDuplicateCheck(member.getNickname());
		if(!result.containsValue("success")) {
			return result;
		}
		result = memberService.registerMemberPhoneCheck(member.getPhone());
		if(!result.containsValue("success")) {
			return result;
			
		}
		result = memberService.registerMemberEmailCheck(member.getEmail());
		if(!result.containsValue("success")) {
			return result;
		}
		result = memberService.registerMemberBirthdayCheck(member.getBirthday());
		if(!result.containsValue("success")) {
			return result;
		}
		result = memberService.registerMemberSexCheck(member.getSex());
		if(!result.containsValue("success")) {
			return result;
		}
		
		return memberService.registerMember(member);
	}
	
	@RequestMapping(value = "/check/member/nickname", produces="application/json" , method = RequestMethod.POST)
	public Map<String, Object> registerMemberNicknameDuplicateCheck(@RequestBody Member member) {
		
		return memberService.registerMemberNicknameDuplicateCheck(member.getNickname());
	}
	
	@RequestMapping(value = "/check/member/id", produces="application/json" , method = RequestMethod.POST)
	public Map<String, Object> registerMemberIdDuplicateCheck(@RequestBody Member member) {
		
		return memberService.registerMemberIdDuplicateCheck(member.getId());
	}
	
}
