package com.sparkcw.goodteam.serviceimpl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.sparkcw.goodteam.dao.MemberDAO;
import com.sparkcw.goodteam.dto.Member;
import com.sparkcw.goodteam.exception.InvalidValueException;
import com.sparkcw.goodteam.exception.ValueBlankException;
import com.sparkcw.goodteam.exception.ValueDuplicateException;
import com.sparkcw.goodteam.service.MemberAuthService;
import com.sparkcw.goodteam.service.MemberService;
import com.sparkcw.goodteam.userdetails.CustomUserDetails;

@Service("memberService")
public class MemberServiceImpl implements MemberService {

	private static final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);
	
	@Autowired
	MemberDAO memberDAO;
	@Autowired
	MemberAuthService memberAuthService;
	
	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		Member member = memberDAO.selectMember(id);
		
		if(ObjectUtils.isEmpty(member)) {
			throw new UsernameNotFoundException("User Not Found by ID: "+ id);
		}
		
		Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
		
		if(authSet.size() == 0) {
			throw new UsernameNotFoundException("No Authority");
		}
		
		authSet.addAll(memberAuthService.getUserAuthorities(member.getCode()));
		
		CustomUserDetails result = new CustomUserDetails(
							member.getCode(), member.getName(), member.getId(), member.getPw(), member.getNickname(), authSet) ;
	    return result;
	}
	
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
	public Map<String, Object> getMember(String id) {
		Map<String, Object> returnValues = new HashMap<String, Object>();
		try {
			Member member = memberDAO.selectMember(id);
			if(ObjectUtils.isEmpty(member)) {
				throw new NullPointerException();
			}
			returnValues.put("data", member);
			returnValues.put("result", "success");
		} catch (DataAccessException e) {
			returnValues.put("result", "fail");
			returnValues.put("message", "조회에 실패했습니다.");
			e.printStackTrace();
		} catch (NullPointerException e) {
			returnValues.put("result", "fail");
			returnValues.put("message", "존재하지않는 사용자입니다.");
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
			if (!chkResult) {
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

			Boolean chkResult = Pattern.matches(chkPattern, nickname);
			if (!chkResult) {
				throw new InvalidValueException();
			}

			String checkinDbNickname = memberDAO.selectMemberNickname(nickname);
			if (nickname.equals(checkinDbNickname) == true) {
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
	public Map<String, Object> registerMemberPwCheck(String pw) {
		Map<String, Object> returnValues = new HashMap<String, Object>();

		try {
			if (StringUtils.isBlank(pw)) {
				throw new ValueBlankException("Pw is blank!");
			}

			String chkPattern = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,16}$";
			Boolean chkResult = Pattern.matches(chkPattern, pw);
			if (!chkResult) {
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

	public Map<String, Object> registerMemberNameCheck(String name) {
		Map<String, Object> returnValues = new HashMap<String, Object>();

		try {
			if (StringUtils.isBlank(name)) {
				throw new ValueBlankException("name is blank!");
			}

			String chkPattern = "^[가-힣a-zA-Z]{0,40}$";
			Boolean chkResult = Pattern.matches(chkPattern, name);
			if (!chkResult) {
				throw new InvalidValueException();
			}

			returnValues.put("result", "success");
			returnValues.put("message", "사용 가능합니다.");

		} catch (ValueBlankException e) {
			returnValues.put("result", "blank");
			returnValues.put("message", "이름이 입력되지 않았습니다.");
		} catch (InvalidValueException e) {
			returnValues.put("result", "invalid");
			returnValues.put("message", "한글, 영문 대 소문자만 사용가능합니다. (공백,특수기호 사용불가)");
		}

		return returnValues;
	}

	public Map<String, Object> registerMemberBirthdayCheck(LocalDate birthday) {
		Map<String, Object> returnValues = new HashMap<String, Object>();
		
		int[] month_day = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

		try {
			if (birthday == null) {
				throw new ValueBlankException("birthday is blank!");
			}
			
			LocalDate today = LocalDate.now();
			DateTimeFormatter fomatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String str_birthday = birthday.format(fomatter);
			
			String chkPattern = "^(1|2)[0-9]{3}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[0-1])$";
			Boolean chkResult = Pattern.matches(chkPattern, str_birthday);
			if (!chkResult) {
				throw new InvalidValueException("생년월일을 다시 확인해주세요.");
			}
			
			if(birthday.isAfter(today)) {
				throw new InvalidValueException("미래에서 오셨습니까?");
			}
				
		    LocalDate minBirthday = today.minusYears(100);
		    
			if((birthday.isBefore(minBirthday))) {
				throw new InvalidValueException("정말입니까?");
			}
			
			int bir_mm = birthday.getMonthValue();
			int bir_dd = birthday.getDayOfMonth();
		
			if (!birthday.isLeapYear()) {
				if (bir_dd > month_day[bir_mm]) {
					throw new InvalidValueException("생년월일을 확인해주세요.");
				}
			} else {
				if(bir_mm == 2) {
					if (bir_dd > (month_day[bir_mm] + 1)) {
						throw new InvalidValueException("생년월일을 확인해주세요.");
					}
				} else {
					if (bir_dd > month_day[bir_mm]) {
						throw new InvalidValueException("생년월일을 확인해주세요.");
					}
				}
			}

			returnValues.put("result", "success");
			returnValues.put("message", "사용 가능합니다.");

		} catch (ValueBlankException e) {
			returnValues.put("result", "blank");
			returnValues.put("message", "생년월일이 입력되지 않았습니다.");
		} catch (InvalidValueException e) {
			returnValues.put("result", "invalid");
			returnValues.put("message", e.getMessage());
		}

		return returnValues;
	}

	@Override
	public Map<String, Object> registerMemberEmailCheck(String email) {
		Map<String, Object> returnValues = new HashMap<String, Object>();
		try {
			if (StringUtils.isBlank(email)) {
				throw new ValueBlankException("email is blank!");
			}
			String chkPattern = "(^[\\w-]+)@(([\\w-]+\\.)+)([a-zA-Z]{2,}$)";
			Boolean chkResult = Pattern.matches(chkPattern, email);
			if (!chkResult) {
				throw new InvalidValueException();
			}
			returnValues.put("result", "success");
			returnValues.put("message", "사용 가능합니다.");
		} catch (ValueBlankException e) {
			returnValues.put("result", "blank");
			returnValues.put("message", "이메일 주소가 입력되지 않았습니다.");
		}catch (InvalidValueException e) {
			returnValues.put("result", "invalid");
			returnValues.put("message", "이메일 주소를 다시 확인해주세요.");
		}
		return returnValues;
	}

	@Override
	public Map<String, Object> registerMemberPhoneCheck(String phone) {
		Map<String, Object> returnValues = new HashMap<String, Object>();
		try {
			if (StringUtils.isBlank(phone)) {
				throw new ValueBlankException("phone is blank!");
			}
			
			String chkPattern = "^01([0|1|6|7|8|9]?)([0-9]{3,4})([0-9]{4})$";
			Boolean chkResult = Pattern.matches(chkPattern, phone);
			if (!chkResult) {
				throw new InvalidValueException("휴대전화번호를 다시 확인해주세요.");
			}
			
			returnValues.put("result", "success");
			returnValues.put("message", "사용 가능합니다.");
		} catch (ValueBlankException e) {
			returnValues.put("result", "blank");
			returnValues.put("message", "휴대전화번호가 입력되지 않았습니다.");
		} catch (InvalidValueException e) {
			returnValues.put("result", "invalid");
			returnValues.put("message", "휴대전화번호를 다시 확인해주세요.");
		}
		return returnValues;
	}

	@Override
	public Map<String, Object> registerMemberSexCheck(String sex) {
		Map<String, Object> returnValues = new HashMap<String, Object>();
		try {
			if (StringUtils.isBlank(sex)) {
				throw new ValueBlankException("sex is blank!");
			}
			
			String chkPattern = "^[M|F]$";
			Boolean chkResult = Pattern.matches(chkPattern, sex);
			if (!chkResult) {
				throw new InvalidValueException("성별을 다시 확인해주세요.");
			}
			
			returnValues.put("result", "success");
			returnValues.put("message", "사용 가능합니다.");
		} catch (ValueBlankException e) {
			returnValues.put("result", "blank");
			returnValues.put("message", "성별이 입력되지 않았습니다.");
		} catch (InvalidValueException e) {
			returnValues.put("result", "invalid");
			returnValues.put("message", "성별을 다시 확인해주세요.");
		}
		return returnValues;
	}

}
