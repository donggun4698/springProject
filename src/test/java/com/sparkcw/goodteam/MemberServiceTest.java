package com.sparkcw.goodteam;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
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

		mem1 = new Member(213, "aaa11", "aaaa11", "홍길동", "길동닉", "111-1111-1111", "aaa@na.com", sdf.parse("2020-08-40"), "M");
		mem2 = new Member(12, "bbb22", "bbbb22", "이순신", "순신닉", "111-2222-2222", "bbb@na.com", sdf.parse("2020-06-05"), "M");
		mem3 = new Member(12, "ccc33", "cccc33", "유관순", "관순닉", "111-3333-3333", "ccc@na.com", sdf.parse("2020-09-06"), "F");
	
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	}
	
//	@Test
//	@Rollback(false)
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
			logger.info(String.valueOf(newmem.get(1).getBirthday()));
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
	
	@Test
	public void birthdayMember() {
		
			sdf.setLenient(false);
			
			LocalDate localtime= LocalDate.parse("2000-02-30");
			System.out.println(localtime);
			Member member1 = new Member();
//			member1.setBirthday(date1);
//			member1.setName("왕건");
			System.out.println(member1.getBirthday());
//			Map<String, Object> birthday1 = memberService.registerMemberBirthdayCheck("1997-02-29");
//			if(birthday1.get("result") != "success") {
//				System.out.println(birthday1.get("message"));	
//			} else System.out.println(birthday1.get("result"));
			
			
		
				
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
