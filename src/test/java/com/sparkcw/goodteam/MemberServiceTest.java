package com.sparkcw.goodteam;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;

import com.sparkcw.goodteam.dao.MemberDAO;
import com.sparkcw.goodteam.dto.Member;
import com.sparkcw.goodteam.service.MemberService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*.xml" })
@Transactional
public class MemberServiceTest {
	private static final Logger logger = LoggerFactory.getLogger("MemberService TEST");
	@Autowired
	MemberService memberService;
	Member mem1;
	Member mem2;
	Member mem3;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	
	@Before
	public void setUp() {
	try {

		mem1 = new Member(213, "aaa11", "aaaa11", "홍길동", "길동닉", "111-1111-1111", "aaa@na.com", LocalDate.parse("2020-08-10"), "M");
		mem2 = new Member(12, "bbb22", "bbbb22", "이순신", "순신닉", "111-2222-2222", "bbb@na.com", LocalDate.parse("2020-06-05"), "M");
		mem3 = new Member(12, "ccc33", "cccc33", "유관순", "관순닉", "111-3333-3333", "ccc@na.com", LocalDate.parse("2020-09-06"), "F");
	
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	}
	
	
	public void insertMember() {
		
		memberService.registerMember(mem1);
		memberService.registerMember(mem2);
		memberService.registerMember(mem3);
	}

	
	public void getMemberTest() {
		Member testMem = new Member();
		//testMem.setId("bb22");
		//testMem.setNickname("길동");
		Map<String, Object> returnValue =  memberService.getMember(testMem);
		if(returnValue.get("result").equals("success")) {
			List<Member> newmem =  (List<Member>)returnValue.get("data");
			logger.info(String.valueOf(newmem.get(1).getName()+" / "+newmem.get(1).getBirthday()));
		}
	}
	
	
	public void registerMemberTest() {
		
	
	}
	
	
	public void deleteMemberTest() {
		Member testMem = new Member();
		testMem.setNickname("순신닉");
		Map<String, Object> returnValue =  memberService.getMember(testMem);
		if(returnValue.get("result").equals("success")) {
			List<Member> newmem =  (List<Member>)returnValue.get("data");
			memberService.deleteMember(newmem.get(0).getCode());
		}
		printMember();
	}
	
	
	public void updateMemberTest() {
		Member testMem = new Member();
		testMem.setId("bb22");
		Map<String, Object> returnValue =  memberService.getMember(testMem);
		List<Member> newmem =  (List<Member>)returnValue.get("data");
		testMem = newmem.get(0);
		testMem.setNickname("길똥닉");
		Map<String, Object> updateValue =  memberService.updateMember((testMem));
		if(updateValue.get("result").equals("success")) {
			printMember();
		}
	}
	
	
	public void idCheck() {
		//특수문자 체크
		String str1 = "dsa1133333";
		String pattern1 = "^[ㄱ-ㅎ가-힣a-zA-Z0-9]*$";
		// + 영문소문자만 사용가능
//		String pattern3 = "^[ㄱ-ㅎ가-힣a-z0-9]*$";
		String pattern3 = "^[ㄱ-ㅎ가-힣a-zA-Z0-9]{4,10}$";
		boolean chk1 = Pattern.matches(pattern3, str1);
		
		//영어 숫자 특수문자만 사용가능
		String str2 = "aaaaa3";
		String pattern2 = "^[a-zA-Z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?~`]+$";
		boolean chk2 = Pattern.matches(pattern2, str2);
		
		String chkPattern = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]$";
		
		logger.info("idcheck:  " + chk1 +" / "+ chk2);
	}
	
	
	public void birthdayMember() {
		
		Member member1 = new Member();
		
			try {
//				LocalDate localtime= LocalDate.parse("2001-02-29");
				member1.setBirthday(LocalDate.parse("2001-02-28"));	
				LocalDate localtime = LocalDate.now();
				System.out.println(localtime.getMonth());
				Map<String, Object> birth = memberService.registerMemberBirthdayCheck(member1.getBirthday());
				System.out.println(birth.get("result"));
				System.out.println(birth.get("message"));
			}catch (DateTimeParseException e) {
				System.out.println(member1.getBirthday());
				System.out.println(e.getMessage());
			}
			
	
				
	}
	
	
	
	public void phoneTest() {
		String wrongnumber = "010333334444";
		String number = "01011112222";
		Member member1 = new Member();
		member1.setPhone(number);
		Map<String, Object> result = memberService.registerMemberPhoneCheck(member1.getPhone());
		String returnValue =(String)result.get("result");
		System.out.println(returnValue + ": " + result.get("message"));
	}
	

	public void emailTest() {
		String wrongemail = "aaa@ba.c";
		String email= "aaaa@na.cc";
		Member member1 = new Member();
		member1.setEmail(email);
		Map<String, Object> result = memberService.registerMemberEmailCheck(member1.getEmail());
		String returnValue =(String)result.get("result");
		System.out.println(returnValue + ": " + result.get("message"));
	}
	
	

	public void sexTest() {
		String man = "M";
		String wrongsex= "f";
		Member member1 = new Member();
		member1.setSex(wrongsex);
		Map<String, Object> result = new HashMap<String, Object>(); 
		result = memberService.registerMemberSexCheck(member1.getSex());
		String returnValue =(String)result.get("result");
		System.out.println(result.get("result") + ": " + result.get("message"));
		
	}
	
	
	@Test
	public void registerTest() {
		Member member1 = new Member();
		Map<String, Object> result = new HashMap<String, Object>();
		String successSex = "M";
		String wrongSex= "f";
		String wrongNick= "순신닉";
		String successNick= "성공닉";
		
		member1.setSex(successSex);
		member1.setNickname(wrongNick);
		
		result = memberService.registerMemberSexCheck(member1.getSex());
		if(!result.containsValue("success")) {
		System.out.println("["+result.hashCode()+"]  "+result.get("result") + ": " + result.get("message"));
		}
		
		result = memberService.registerMemberNicknameDuplicateCheck(member1.getNickname());
		if(!result.containsValue("success")) {
			System.out.println("["+result.hashCode()+"]  "+result.get("result") + ": " + result.get("message"));
		}
	}
	public void printMember() {
		Map<String, Object> members =  memberService.getMember(null);
		if(members.get("result").equals("success")) {
			List<Member> newmem =  (List<Member>)members.get("data");
			for(int i=0; i < newmem.size();i++) {
				logger.info(String.valueOf(newmem.get(i).getName()+" / "+String.valueOf(newmem.get(i).getNickname()+"\t" )));
			}
		}
	}
}
