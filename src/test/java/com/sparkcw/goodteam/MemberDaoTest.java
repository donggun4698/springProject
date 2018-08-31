package com.sparkcw.goodteam;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sparkcw.goodteam.dao.MemberDAO;
import com.sparkcw.goodteam.dto.Member;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*.xml" })
public class MemberDaoTest {

	@Autowired
	MemberDAO memberDAO;
	Member mem1;

	@Before
	public void setUp() {
		mem1 = new Member("1234", "aa11", "11", "홍길동", "길동닉", "111-1111-1111", "ddd@na.com", "2020-03-03", "M");
	}

	@Test
	public void testInsertMember() {
		memberDAO.insertMember(mem1);
		memberDAO.deleteAllMember();
	}

	@Test
	public void testdeleteMember() {
		memberDAO.deleteAllMember();
	}
}
