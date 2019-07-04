package com.sparkcw.goodteam.dao;

import java.util.List;

import com.sparkcw.goodteam.dto.Auth;

public interface AuthDAO {

	/**
	 * @param auth
	 * @return 
	 */
	public List<Auth> selectAuth();
	
	/**
	 * 권한을 등록합니다.
	 * @param auth
	 */
	public void insertAuth(Auth auth);
	
	/**
	 * 해당 권한을 수정합니다.
	 * @param auth
	 */
	public void updateAuth(Auth auth);
	
	/**
	 * 해당 권한을 삭제합니다.
	 * @param authCode
	 */
	public void deleteAuth(int authCode);
	
}
