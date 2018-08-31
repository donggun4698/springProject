package com.sparkcw.goodteam.dao;

import com.sparkcw.goodteam.dto.Member;

public interface MemberDAO {
	public void insertMember(Member member);
	public void deleteAllMember();
}
