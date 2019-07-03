package com.sparkcw.goodteam.service;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

public interface MemberAuthService {

	
	/**
	 * DB에서  해당 유저의 권한 목록을 조회합니다.
	 * @param memCode
	 * @return 유저의 권한목록(Only 권한명)
	 */
	public List<GrantedAuthority> getUserAuthorities(int memCode);
}
