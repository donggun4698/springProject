package com.sparkcw.goodteam.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.sparkcw.goodteam.dao.MemberDAO;
import com.sparkcw.goodteam.dto.Member;
import com.sparkcw.goodteam.exception.InvalidValueException;
import com.sparkcw.goodteam.exception.ValueBlankException;
import com.sparkcw.goodteam.exception.ValueDuplicateException;
import com.sparkcw.goodteam.service.MemberService;

@Service("memberService")
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberDAO memberDAO;

	@Override
	public Map<String, Object> getAllMember() {
		Map<String, Object> returnValues = new HashMap<String, Object>();
		try {
			List<Member> members = memberDAO.selectAllMember();
			returnValues.put("data", members);
			returnValues.put("result", "success");
		} catch (DataAccessException e) {
			returnValues.put("result", "fail");
			e.printStackTrace();
		}
		return returnValues;
	}

	@Override
	public Map<String, Object> getMember(Member member) {
		Map<String, Object> returnValues = new HashMap<String, Object>();
		try {
			List<Member> members = memberDAO.selectMember(member);
			returnValues.put("data", members);
			returnValues.put("result", "success");
		} catch (DataAccessException e) {
			returnValues.put("result", "fail");
			returnValues.put("message", "조회에 실패했습니다.");
			e.printStackTrace();
		}
		return returnValues;
	}

	@Override
	public Map<String, Object> registerMember(Member member) {
		Map<String, Object> returnValues = new HashMap<String, Object>();
		try {
			memberDAO.insertMember(member);
			returnValues.put("result", "success");
		} catch (DataAccessException e) {
			returnValues.put("result", "fail");
			returnValues.put("message", "등록에 실패했습니다.");
			e.printStackTrace();
		}
		return returnValues;

	}

	@Override
	public Map<String, Object> deleteMember(int memberCode) {
		Map<String, Object> returnValues = new HashMap<String, Object>();
		try {
			memberDAO.deleteMember(memberCode);
			;
			returnValues.put("result", "success");
		} catch (DataAccessException e) {
			returnValues.put("result", "fail");
			returnValues.put("message", "삭제에 실패했습니다.");
			e.printStackTrace();
		}
		return returnValues;
	}

	@Override
	public Map<String, Object> updateMember(Member member) {
		Map<String, Object> returnValues = new HashMap<String, Object>();
		try {
			memberDAO.updateMember(member);
			returnValues.put("result", "success");
		} catch (DataAccessException e) {
			returnValues.put("result", "fail");
			returnValues.put("message", "수정에 실패했습니다.");
			e.printStackTrace();
		}
		return returnValues;
	}

	@Override
	public Map<String, Object> registerMemberIdDuplicateCheck(String id) {
		Map<String, Object> returnValues = new HashMap<String, Object>();

		try {
			if (StringUtils.isBlank(id)) {
				throw new ValueBlankException("Id is blank!");
			}
			
			String chkPattern = "^[a-z0-9]{5,20}$";
			Boolean chkResult = Pattern.matches(chkPattern, id);
			if(!chkResult) {
				throw new InvalidValueException();
			}
			
			String checkinDbId = memberDAO.selectMemberId(id);
			if (id.equals(checkinDbId) == true) {
				throw new ValueDuplicateException("Id is duplicate!");
			}
   
			returnValues.put("result", "success");
			returnValues.put("message", id + "은(는) 사용 가능합니다.");

		} catch (ValueBlankException e) {
			returnValues.put("result", "blank");
			returnValues.put("message", "아이디가 입력되지 않았습니다.");
		} catch (InvalidValueException e) {
			returnValues.put("result", "invalid");
			returnValues.put("message", "사용자 ID는 5~20자 사이의 영문 소문자, 숫자만 사용가능합니다."); 
		} catch (ValueDuplicateException e) {
			returnValues.put("result", "duplicate");
			returnValues.put("message", id + "은(는) 이미 존재하는 아이디이므로 사용 불가능 합니다.");
		} catch (DataAccessException e) {
			returnValues.put("result", "fail");
			returnValues.put("message", "중복 검사에 실패했습니다.");
		}
		return returnValues;
	}

	@Override
	public Map<String, Object> registerMemberNicknameDuplicateCheck(String nickname) {
		Map<String, Object> returnValues = new HashMap<String, Object>();
 
		try {
			if (StringUtils.isBlank(nickname)) {
				throw new ValueBlankException("Nickname is blank!");
			}
			
			String chkPattern = "^[ㄱ-ㅎ가-힣a-zA-Z0-9]{2,10}$";
			int minNickname = 2;
			int maxNickname = 10;
			Boolean chkResult = Pattern.matches(chkPattern, nickname);
			if(!chkResult) {
				throw new InvalidValueException();
			}
			
			String checkinDbNickname = memberDAO.selectMemberNickname(nickname);
			if (nickname.equals(checkinDbNickname)== true) {
				throw new ValueDuplicateException("Nickname is duplicate!");
			}

			returnValues.put("result", "success");
			returnValues.put("message", nickname + "은(는) 사용 가능합니다.");

		} catch (ValueBlankException e) {
			returnValues.put("result", "blank");
			returnValues.put("message", "닉네임이 입력되지 않았습니다.");
		} catch (InvalidValueException e) {
			returnValues.put("result", "invalid");
			returnValues.put("message", "닉네임은 2~10자 사이의 한글, 숫자, 영문 대 소문자만 사용가능합니다. (공백,특수기호 사용불가)"); 
		} catch (ValueDuplicateException e) {
			returnValues.put("result", "duplicate");
			returnValues.put("message", nickname + "은(는) 이미 존재하는 닉네임이므로 사용 불가능 합니다..");
		} catch (DataAccessException e) {
			returnValues.put("result", "fail");
			returnValues.put("message", "중복 검사에 실패했습니다.");
		}
		return returnValues;
	}
    
	@Override
	public Map<String, Object> registerMemberPwCheck(String pw){
		Map<String, Object> returnValues = new HashMap<String, Object>();
		
		try {
			if (StringUtils.isBlank(pw)) {
				throw new ValueBlankException("Pw is blank!");
			}
			
			String chkPattern = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,16}$";
			int minPw = 8;
			int maxPw = 16;
			Boolean chkResult = Pattern.matches(chkPattern, pw);
			if(!chkResult  ) {
				throw new InvalidValueException();
			}
			
			returnValues.put("result", "success");
			returnValues.put("message", "사용 가능합니다.");

		} catch (ValueBlankException e) {
			returnValues.put("result", "blank");
			returnValues.put("message", "비밀번호가 입력되지 않았습니다.");
		} catch (InvalidValueException e) {
			returnValues.put("result", "invalid");
			returnValues.put("message", "8~16자 영문 대 소문자, 숫자, 특수문자만 사용가능합니다."); 
		} 
		
		return returnValues;
	}
	
}
