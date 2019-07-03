package com.sparkcw.goodteam.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.sparkcw.goodteam.dao.MemberAuthDAO;
import com.sparkcw.goodteam.dto.Auth;
import com.sparkcw.goodteam.service.MemberAuthService;

@Service("memberAuthService")
public class MemberAuthServiceImpl implements MemberAuthService {

	@Autowired
	MemberAuthDAO memberAuthDAO;
	
	@Override
	public List<GrantedAuthority> getUserAuthorities(int memCode) {
		List<Auth> list = null;
		try {
				list = memberAuthDAO.selectMemberAuthByMemCode(memCode);
		}catch (DataAccessException e) {
			e.printStackTrace();
			return null;
		}
		
		List<GrantedAuthority> returnValues = new ArrayList<GrantedAuthority>();
		for(Auth auth : list) {
			returnValues.add(new SimpleGrantedAuthority(auth.getRole()));
		}
		
		return returnValues;
	}

}
