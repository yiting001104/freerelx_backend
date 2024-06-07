package tw.team.project.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.transaction.Transactional;
import tw.team.project.model.Member;
import tw.team.project.model.MemberRepository;
import tw.team.project.util.EmailValidator;

@Service
public class MemberService {
	
	@Autowired
	private MemberRepository memberRepository;
	
	public List<Member> findAll() {
			return memberRepository.findAll();
	}
	
	public Member findbyId(Integer id) {
		Optional<Member> option = memberRepository.findById(id);
		if (option.isPresent())
			return option.get();
		else
			return null;
	}
	@Transactional
	public Member checkLogin(String email, String password) {
		if (EmailValidator.isValidEmail(email)) {
			Optional<Member> option = memberRepository.findByEmail(email);
			if (option.isPresent()) {
				Member member = option.get();
				String origin = member.getPassword();
				if (origin.equals(password)) {
					Date date = new Date();
					member.setLoginTime(date);
					member.setLoginStatus("登入中");
					return member;
				}
			}
		}
		return null;
		
	}
	@Transactional
	public Member logout(Integer id) {
		if (id!=null) {
			Optional<Member> option = memberRepository.findById(id);
			if (option.isPresent()) {
				Member member = option.get();
				member.setLoginStatus("尚未登入");
				return member;	
			}
		}
		return null;
	}
	// 更新會員資料
	@Transactional
	public Member updateData(Integer id, String json) {
		try {
			JSONObject obj = new JSONObject(json);
			
			String name = obj.isNull("name") ? null : obj.getString("name");
			String gender = obj.isNull("gender") ? null : obj.getString("gender");
			String birth = obj.isNull("birth") ? null : obj.getString("birth");
			Date birth1;
			if (birth != null) {
				birth1=new SimpleDateFormat("yyyy-MM-dd").parse(birth);
			} else
				birth1 = null;
			
			
			String nationalId = obj.isNull("national_id") ? null : obj.getString("national_id");
			String email = obj.isNull("email") ? null : obj.getString("email");
			String phone = obj.isNull("phone_number") ? null : obj.getString("phone_number");
			String creditCard = obj.isNull("credit_card") ? null : obj.getString("credit_card");
			String contactAddress = obj.isNull("contact_address") ? null : obj.getString("contact_address");
			String password = obj.isNull("password") ? null : obj.getString("password");
			String nationality = obj.isNull("nationality") ? null : obj.getString("nationality");
//			String picture = obj.isNull("picture") ? null : obj.getString("picture");
			Date date = new Date();
			Optional<Member> op = memberRepository.findById(id);
			if (op.isPresent()) {
				Member originMember = op.get();				
				if (name != null && gender != null && birth1 !=null && nationalId != null && email != null && phone !=null && contactAddress!=null && password!=null && nationality!=null) {
					originMember.setMemberName(name);
					originMember.setGender(gender);
					originMember.setBirth(birth1);
					originMember.setNationId(nationalId);
					originMember.setEmail(email);
					originMember.setPhoneNumber(phone);
					originMember.setCreditCard(creditCard);
					originMember.setContactAddress(contactAddress);
					originMember.setNationality(nationality);
					originMember.setPassword(password);
					originMember.setRegistrationDate(date);
					return originMember;
				}
			}

		} catch (JSONException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	@Transactional
	public Member updateData2(Integer id, JSONObject json, MultipartFile multipartFile) {
		
		try {
//			JSONObject obj = new JSONObject(json);
			
			String name = json.isNull("name") ? null : json.getString("name");
			String gender = json.isNull("gender") ? null : json.getString("gender");
			String birth = json.isNull("birth") ? null : json.getString("birth");
			System.out.println(birth);
			Date birth1;
			if (birth != null) {
				birth1=new SimpleDateFormat("yyyy-MM-dd").parse(birth);
			} else
				birth1 = null;
			
			
			String nationalId =json.isNull("national_id") ? null : json.getString("national_id");
			String email = json.isNull("email") ? null : json.getString("email");
			String phone = json.isNull("phone_number") ? null : json.getString("phone_number");
			String creditCard = json.isNull("credit_card") ? null : json.getString("credit_card");
			String contactAddress = json.isNull("contact_address") ? null : json.getString("contact_address");
			String password = json.isNull("password") ? null : json.getString("password");
			String nationality = json.isNull("nationality") ? null : json.getString("nationality");
			byte[] picture;
			Date date = new Date();
			Optional<Member> op = memberRepository.findById(id);
			if (op.isPresent()) {
				Member originMember = op.get();				
				if (name != null && gender != null && birth1 !=null && nationalId != null && email != null && phone !=null && contactAddress!=null && password!=null && nationality!=null) {
					originMember.setMemberName(name);
					originMember.setGender(gender);
					originMember.setBirth(birth1);
					originMember.setNationId(nationalId);
					originMember.setEmail(email);
					originMember.setPhoneNumber(phone);
					originMember.setCreditCard(creditCard);
					originMember.setContactAddress(contactAddress);
					originMember.setNationality(nationality);
					originMember.setPassword(password);
					originMember.setRegistrationDate(date);
					if (multipartFile !=null) {
						picture = multipartFile.getBytes();
						originMember.setPicture(picture);
					}
					return originMember;
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Member newMember(Member person) {
		return memberRepository.save(person);
	}
	
	//  刪除會員
	public void deleteMember(Integer id) {
		memberRepository.deleteById(id);
	}
	
	@Transactional
	public Member updatePassword(String json, Integer inputId) throws JSONException {
		JSONObject obj = new JSONObject(json);
		Integer id = obj.isNull("memberId")? null : obj.getInt("memberId");
    	String originalPassword = obj.isNull("originPassword") ? null : obj.getString("originPassword");
    	String newPassword = obj.isNull("newPassword") ? null : obj.getString("newPassword");
    	if (id!=null && id==inputId && originalPassword!=null && newPassword!=null) {
    		Optional<Member> optional = memberRepository.findById(id);
    		if (optional.isPresent()) {
    			Member origin = optional.get();
    			if (originalPassword.equals(origin.getPassword())) {
    				origin.setPassword(newPassword);
    				return origin;
    			}
    		}
    	}
    	return null;
	}
	//後台更新狀態
	@Transactional
	public Member updateStatus(String json, Integer memberId) throws JSONException{
		JSONObject obj = new JSONObject(json);
		String status = obj.isNull("status")? null : obj.getString("status");
		Integer id = obj.isNull("memberId")? null : obj.getInt("memberId");
    	if (status!=null && status.length()!=0 && memberId!=null && id == memberId) {
    		Optional<Member> optional = memberRepository.findById(memberId);
    		if (optional.isPresent()) {
    			Member origin = optional.get();
    			origin.setMemberStatus(status);
    			return origin;
    		}
    	}
    	return null;
	}



}