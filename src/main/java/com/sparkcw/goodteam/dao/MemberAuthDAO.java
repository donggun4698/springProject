package com.sparkcw.goodteam.dao;

import java.util.List;

import com.sparkcw.goodteam.dto.Auth;

public interface MemberAuthDAO {
	
	/**
	 * DB에서  해당 유저의 권한 목록을 조회합니다.
	 * @param memCode
	 * @return 유저의 권한목록
	 */
	public List<Auth> selectMemberAuthByMemCode(int memCode);
	

}
