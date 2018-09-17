package com.sparkcw.goodteam;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/*.xml" })
@Transactional
public class MemberDaoTest {
	private static final Logger logger = LoggerFactory.getLogger("MemberDAO TEST");
	@Autowired
	MemberDAO memberDAO;
	Member mem1;
	Member mem2;
	Member mem3;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	@Before
	public void setUp() {
		try {

			mem1 = new Member(213, "aaa11", "aaaa11", "홍길동", "길동닉", "111-1111-1111", "aaa@na.com", sdf.parse("2020-08-12"), "M");
			mem2 = new Member(12, "bbb22", "bbbb22", "이순신", "순신닉", "111-2222-2222", "bbb@na.com", sdf.parse("2020-06-05"), "M");
			mem3 = new Member(12, "ccc33", "cccc33", "유관순", "관순닉", "111-3333-3333", "ccc@na.com", sdf.parse("2020-09-06"), "F");
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void insertMember() {
		
		memberDAO.insertMember(mem1);
		memberDAO.insertMember(mem2);
		memberDAO.insertMember(mem3);
	}
	
	public void testdeleteMember() {
	
		memberDAO.insertMember(mem1);
	
	}
	
	
	public void updateMember() {
		mem1.setNickname("길똥닉");
		memberDAO.updateMember(mem1);
		assertThat(mem1.getNickname(), is(("길똥닉")));
	}
	 
	@Test
	public void selectMember() {
		Member selectMem = new Member();
		selectMem.setId("bb22");
		List<Member> newmem = memberDAO.selectMember(selectMem);
		logger.info(String.valueOf(newmem.get(0).getBirthday()));
//		assertThat(mem1.getName(), is(newmem.getName()));	
	}
	
	
	public void selectMemberId() {
		String members = memberDAO.selectMemberId("bb22");
		logger.info(members);
		
	}
	

	public void selectMemberNickname() {
		String members = memberDAO.selectMemberNickname("순신닉1");
		logger.info("nickName:"+members);
		
	}
	
	
	public void deleteMember() {
		List<Member> members = memberDAO.selectAllMember();
		memberDAO.deleteMember(members.get(0).getCode());
		members = memberDAO.selectAllMember();
		logger.info(String.valueOf("남은 수 ="+ (members.size())));
	}
}
