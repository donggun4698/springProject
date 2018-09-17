package com.sparkcw.goodteam.service;

import java.util.Date;
import java.util.Map;

import com.sparkcw.goodteam.dto.Member;

public interface MemberService {

	/*
	 * 전체멤버를 조회합니다.
	 */
	public Map<String, Object> getAllMember();
	
	/*
	 * 멤버를 조회합니다.
	 */
	public Map<String, Object> getMember(Member member);
	
	/*
	 * 멤버를 등록합니다.
	 */
	public Map<String, Object> registerMember(Member member);
	
	/*
	 * 특정멤버를 삭제합니다.
	 */
	public Map<String, Object> deleteMember(int memberCode);
	
	/*
	 * 특정멤버의 정보를 수정합니다.
	 */
	public Map<String, Object> updateMember(Member member);
	
	/*
	 * 입력받은 ID중복여부를 검사합니다.
	 */
	public Map<String, Object> registerMemberIdDuplicateCheck(String id);
		
	/*
	 * 입력받은 닉네임의 중복여부를 검사합니다.
	 */
	public Map<String, Object> registerMemberNicknameDuplicateCheck(String nickname);
	
	/*
	 * 입력받은 값이 비밀번호로 사용가능한지 검사합니다.
	 */
	public Map<String, Object> registerMemberPwCheck(String pw);
	
	/*
	 * 입력받은 값이 이름으로 적절한지 검사합니다.
	 */
	public Map<String, Object> registerMemberNameCheck(String name);
	
	/*
	 * 입력받은 생년월일이 적절한지 검사합니다.
	 */
	public Map<String, Object> registerMemberBirthdayCheck(String birthday);
	
}
