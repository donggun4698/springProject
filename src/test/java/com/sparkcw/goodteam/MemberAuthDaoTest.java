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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.sparkcw.goodteam.dao.MemberAuthDAO;
import com.sparkcw.goodteam.dao.MemberDAO;
import com.sparkcw.goodteam.dto.Auth;
import com.sparkcw.goodteam.dto.Member;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
@Transactional
public class MemberAuthDaoTest {

	private static final Logger logger = LoggerFactory.getLogger(MemberAuthDaoTest.class);
	
	@Autowired
	MemberAuthDAO memberAuthDAO;
	@Autowired
	MemberDAO memberDAO;
	
	@Test
	public void getMemberAuth() {
		Member mem = memberDAO.selectMember("bbb22");
		List<Auth> auth = memberAuthDAO.selectMemberAuthByMemCode(mem.getCode());
		List<String> list  = new ArrayList<String>() {{
			add("a");
			add("b");
			add("b");
			add("c");
			}
		};
		Set<String> set1 = new HashSet<String>();
		set1.addAll(list);
		List<String> list2 = new ArrayList<String>(set1);
		
		Set<String> authorities = sortAuthorities(set1);
		
	
//		logger.info(auth.get(0).getRole()+" / "+auth.get(0).getCode());
		Iterator<String> itr = authorities.iterator();
		while(itr.hasNext()){
			logger.info(itr.next());
		}
	}
	
	private static SortedSet<String> sortAuthorities(Collection<? extends String> authorities){
		Assert.notNull(authorities, "Cannot pass a null GrantedAuthority collection");
		SortedSet<String> sortedAuthorities = new TreeSet<String>(new AuthorityComparator());
		
		for(String grantedAuthority : authorities){
			Assert.notNull(grantedAuthority, "GrantedAuthority list cannot contain any null elements");
			sortedAuthorities.add(grantedAuthority);
		}
		
		return sortedAuthorities;
	}
	
	private static class AuthorityComparator implements Comparator<String>, Serializable{
		
		private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;
		
		@Override
		public int compare(String g1, String g2){
			if(g2 == null) {
				return -1;
			}
			
			if(g1 == null) {
				return 1;
			}
			
			return g1.compareTo(g2);
		}
	}
}
