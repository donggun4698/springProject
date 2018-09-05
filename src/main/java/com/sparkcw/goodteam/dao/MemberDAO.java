package com.sparkcw.goodteam.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.sparkcw.goodteam.dto.Member;

public interface MemberDAO {
	/*
	 * 멤버를 추가.
	 */
	public void insertMember(Member member);
	
	/*
	 * 전체멤버를 삭제
	 */
	public void deleteAllMember();
	
	/*
	 * 특정멤버를 삭제합니다.
	 */
	public void deleteMember(String memberId);
	
	/*
	 * 특정멤버의 정보를 수정합니다.
	 */
	public void updateMember(Member member);
	
	/*
	 * 특정멤버를 조회합니다.
	 */
	public Member selectMember(String memberId);
	
	/*
	 * 전체멤버를 조회합니다.
	 */
	public List<Member> selectAllMember();
}
