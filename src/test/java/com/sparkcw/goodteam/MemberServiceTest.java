package com.sparkcw.goodteam;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.sql.Date;
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
	@Before
	public void setUp() {
		mem1 = new Member(213, "aaa11", "aaaa11", "홍길동", "길동닉", "111-1111-1111", "aaa@na.com", Date.valueOf("2020-08-12"), "M");
		mem2 = new Member(12, "bbb22", "bbbb22", "이순신", "순신닉", "111-2222-2222", "bbb@na.com", Date.valueOf("2020-06-05"), "M");
		mem3 = new Member(12, "ccc33", "cccc33", "유관순", "관순닉", "111-3333-3333", "ccc@na.com", Date.valueOf("2020-09-06"), "F");
	}
 

	
	public void getMemberTest() {
		Member testMem = new Member();
		//testMem.setId("bb22");
		//testMem.setNickname("길동");
		Map<String, Object> returnValue =  memberService.getMember(testMem);
		if(returnValue.get("result").equals("success")) {
			List<Member> newmem =  (List<Member>)returnValue.get("data");
			logger.info(String.valueOf(newmem.size()));
		}
	}
	
	
	public void registerMemberTest() {
		Member testMem = new Member(213, "cc33", "33", "유관순", "관순닉", "111-1111-3333", "ccc@na.com", Date.valueOf("2020-03-12"), "F");
		Map<String, Object> returnValue =  memberService.registerMember(testMem);
		printMember(); 
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
	
	@Test
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
