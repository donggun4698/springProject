package com.sparkcw.goodteam;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.sparkcw.goodteam.dao.AuthDAO;
import com.sparkcw.goodteam.dao.MemberAuthDAO;
import com.sparkcw.goodteam.dao.MemberDAO;
import com.sparkcw.goodteam.dto.Auth;
import com.sparkcw.goodteam.dto.Member;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
@Transactional
public class AuthDaoTest {

	private static final Logger logger = LoggerFactory.getLogger(AuthDaoTest.class);
	
	@Autowired
	MemberAuthDAO memberAuthDAO;
	@Autowired
	MemberDAO memberDAO;
	@Autowired
	AuthDAO authDAO;
	

	public void selectAuth() {
		List<Auth> auth = authDAO.selectAuth();
		Iterator<Auth> itr = auth.iterator();
		
		while(itr.hasNext()){
			logger.info(itr.next().getRole());
		}
	}
	
	@Rollback
	@Test
	public void insertAuth() {
		Auth auth = new Auth();
		auth.setRole("ROLE_MANAGER2");
		authDAO.insertAuth(auth);
		selectAuth();
	}
	
	@Rollback
	@Test
	public void updateAuth() {
		Auth auth = new Auth();
		auth.setCode(1);
		auth.setRole("ROLE_GOD");
		authDAO.updateAuth(auth);
		selectAuth();
	}
	
	@Rollback
	@Test
	public void deleteAuth() {
		Auth auth = new Auth();
		auth.setCode(4);
		auth.setRole("ROLE_MANAGER2");
		authDAO.insertAuth(auth);
		selectAuth();
		authDAO.deleteAuth(auth.getCode());
		selectAuth();
	}
	
}
