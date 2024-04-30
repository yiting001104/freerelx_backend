package tw.team.project.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

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
	
	public Member checkLogin(String email, String password) {
		if (EmailValidator.isValidEmail(email)) {
			Optional<Member> option = memberRepository.findByEmail(email);
			if (option.isPresent()) {
				Member member = option.get();
				String origin = member.getPassword();
				if (origin.equals(password)) {
					return member;
				}
			}
		}
		return null;
		
	}
	// 更新會員資料或申請會員
	public Member updateData(String json) {
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
			Date date = new Date();
			System.out.println(date);
			if (name != null && gender != null && birth1 !=null && nationalId != null && email != null && phone !=null && contactAddress!=null && password!=null && nationality!=null) {
				Member newMember = new Member();
				newMember.setMemberName(name);
				newMember.setGender(gender);
				newMember.setBirth(birth1);
				newMember.setNationId(nationalId);
				newMember.setEmail(email);
				newMember.setPhoneNumber(phone);
				newMember.setCreditCard(creditCard);
				newMember.setContactAddress(contactAddress);
				newMember.setNationality(nationality);
				newMember.setPassword(password);
				newMember.setRegistrationDate(date);
				return memberRepository.save(newMember);
			}
		} catch (JSONException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	public Member newMember(Member person) {
		return memberRepository.save(person);
	}
	
	public Member updatePhoto(Member member, byte[] photo) {
		member.setPicture(photo);
		return memberRepository.save(member);
		
	}



}