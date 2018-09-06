package com.sparkcw.goodteam.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.sparkcw.goodteam.dao.MemberDAO;
import com.sparkcw.goodteam.dto.Member;
import com.sparkcw.goodteam.service.MemberService;


@Service("memberService")
public class MemberServiceImpl implements MemberService {

	@Autowired 
	MemberDAO memberDAO;

	@Override
	public Map<String, Object> getAllMember() {
		Map<String, Object> returnValues  = new HashMap<String, Object>();
		try {
			List<Member> members = memberDAO.selectAllMember();
			returnValues.put("data", members);
			returnValues.put("result","success");
		}catch (DataAccessException e) {
			returnValues.put("result","fail");
			e.printStackTrace();
		}
		return returnValues;
	}
 
	@Override
	public Map<String, Object> getMember(Member member) {
		Map<String, Object> returnValues  = new HashMap<String, Object>();
		try {
			List<Member> members = memberDAO.selectMember(member);
			returnValues.put("data", members);
			returnValues.put("result","success");
		}catch (DataAccessException e) {
			returnValues.put("result","fail");
			returnValues.put("message","조회에 실패했습니다.");
			e.printStackTrace();
		}
		return returnValues;
	}

	@Override
	public Map<String, Object> registerMember(Member member) {
		Map<String, Object> returnValues  = new HashMap<String, Object>();
		try {
			memberDAO.insertMember(member);
			returnValues.put("result","success");
		}catch (DataAccessException e) {
			returnValues.put("result","fail");
			returnValues.put("message","등록에 실패했습니다.");
			e.printStackTrace();
		}
		return returnValues;
		
	}

	@Override
	public Map<String, Object> deleteMember(int memberCode) {
		Map<String, Object> returnValues  = new HashMap<String, Object>();
		try {
			memberDAO.deleteMember(memberCode);;
			returnValues.put("result","success");
		}catch (DataAccessException e) {
			returnValues.put("result","fail");
			returnValues.put("message","삭제에 실패했습니다.");
			e.printStackTrace();
		}
		return returnValues;
	}

	@Override
	public Map<String, Object> updateMember(Member member) {
		Map<String, Object> returnValues  = new HashMap<String, Object>();
		try {
			memberDAO.updateMember(member);
			returnValues.put("result","success");
		}catch (DataAccessException e) {
			returnValues.put("result","fail");
			returnValues.put("message","삭제에 실패했습니다.");
			e.printStackTrace();
		}
		return returnValues;
	}
	
	

}
