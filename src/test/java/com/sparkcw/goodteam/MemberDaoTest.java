package com.sparkcw.goodteam;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.sql.Date;
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
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*.xml" })
@Transactional
public class MemberDaoTest {
	private static final Logger logger = LoggerFactory.getLogger("MemberDAO TEST");
	@Autowired
	MemberDAO memberDAO;
	Member mem1;
	Member mem2;
	
	
	
	@Before
	public void setUp() {
		mem1 = new Member(213, "aa11", "11", "홍길동", "길동닉", "111-1111-1111", "aaa@na.com", Date.valueOf("2020-08-12"), "M");
		mem2 = new Member(12, "bb22", "22", "이순신", "순신닉", "111-2222-2222", "bbb@na.com", Date.valueOf("2020-06-05"), "M");
	}
 
	@Test
	public void testdeleteMember() {
		memberDAO.deleteAllMember();
		memberDAO.insertMember(mem1);
		memberDAO.insertMember(mem2);
	}
	
	@Test
	public void updateMember() {
		mem1.setNickname("길똥닉");
		memberDAO.updateMember(mem1);
		assertThat(mem1.getNickname(), is(("길똥닉")));
	}
	 
	@Test
	public void selectMember() {
		Member selectMem = new Member();
		selectMem.setId("aa11");
		List<Member> newmem = memberDAO.selectMember(selectMem);
		logger.info(newmem.get(0).getId());
//		assertThat(mem1.getName(), is(newmem.getName()));	
	}
	
	@Test
	public void selectAllMember() {
		List<Member> members = memberDAO.selectAllMember();
		logger.info(members.toString());
		
	}
	
	@Test
	public void deleteMember() {
		List<Member> members = memberDAO.selectAllMember();
		memberDAO.deleteMember(members.get(0).getCode());
		members = memberDAO.selectAllMember();
		logger.info(String.valueOf("남은 수 ="+ (members.size())));
	}
}
