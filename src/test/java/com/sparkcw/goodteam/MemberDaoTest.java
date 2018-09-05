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
	
	@Autowired
	PlatformTransactionManager transactionManager;
	
	@Before
	public void setUp() {
		mem1 = new Member(1, "aa11", "11", "홍길동", "길동닉", "111-1111-1111", "aaa@na.com", Date.valueOf("2020-08-12"), "M");
		mem2 = new Member(2, "bb22", "22", "이순신", "순신닉", "111-2222-2222", "bbb@na.com", Date.valueOf("2020-06-05"), "M");
	}
 
	@Test
	public void testdeleteMember() {
		memberDAO.deleteAllMember();
		memberDAO.insertMember(mem1);
		memberDAO.insertMember(mem2);
		memberDAO.deleteAllMember();
	}
	
	@Test
	public void updateMember() {
		mem1.setNickname("길똥닉");
		memberDAO.updateMember(mem1);
		assertThat(mem1.getNickname(), is(("길똥닉")));
	}
	 
	@Test
	public void selectMember() {
		Member newmem = memberDAO.selectMember("aa11");
		assertThat(mem1.getName(), is(newmem.getName()));	
	}
	
	@Test
	public void selectAllMember() {
		List<Member> members = memberDAO.selectAllMember();
		logger.info(members.toString());
		
	}
	
	@Test
	public void deleteMember() {
		List<Member> members = memberDAO.selectAllMember();
		memberDAO.deleteMember("aa11");
		logger.info(String.valueOf("mem1="+(members.contains(mem1))+" / "+"mem2="+String.valueOf((members.contains(mem2)))));
	}
}
