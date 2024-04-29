package tw.team.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	public Member newMember(Member person) {
		return memberRepository.save(person);
	}
	


}
