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
	public void deleteMember(int memberCode);
	
	/*
	 * 특정멤버의 정보를 수정합니다.
	 */
	public void updateMember(Member member);
	
//	/*
//	 * 특정멤버를 조회합니다.
//	 */
//	public List<Member> selectMember(Member member);
//	
	/*
	 * 전체멤버를 조회합니다.
	 */
	public List<Member> selectAllMember();
	
	
	/**
	 * DB에서 입력받은 ID를 가진 계정의 정보를 조회합니다.  
	 * @param id
	 * @return
	 */
	public Member selectMember(String id);
	
	/*
	 * 입력받은 ID가 DB에 존재하는지 조회합니다.
	 */
	public String selectMemberId(String id);
	
	/*
	 * 입력받은 닉네임이 DB에 존재하는지 조회합니다.
	 */
	public String selectMemberNickname(String nickname);
}
