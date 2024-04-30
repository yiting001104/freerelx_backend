package tw.team.project.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.team.project.model.Member;
import tw.team.project.service.MemberService;

@RestController
@RequestMapping("/hotel")
public class MemberController {

	@Autowired
	private MemberService memberservice;
	
	// 後台查看會員所有資料
	@PostMapping("/members/find")
	public String findAll() throws JSONException {
		List<Member> members = memberservice.findAll();
//		JSONObject responseObj = new JSONObject();
		JSONArray array = new JSONArray();
		
		if (members != null && !members.isEmpty())
		for (Member person : members) {
			JSONObject item = new JSONObject()
					.put("member_id", person.getMemberId())
					.put("name", person.getMemberName())
					.put("birth", person.getBirth())
					.put("gender", person.getGender())
					.put("nationId", person.getNationId())
					.put("email", person.getEmail())
					.put("phone_number", person.getPhoneNumber())
					.put("credit_card", person.getCreditCard())
					.put("contact_address", person.getContactAddress())
					.put("nationality", person.getNationality())
					.put("login_time", person.getLoginTime())
					.put("login_status", person.getLoginStatus())
					.put("picture", person.getPicture());
			array.put(item);
		}
//		responseObj.put("list", array);
		return array.toString();
	}
	
	// 會員登入
	@PostMapping("/member/login")
	public String login(@RequestBody String json) throws JSONException{
		JSONObject responseJson = new JSONObject();
		JSONObject obj = new JSONObject(json);
		
		String email = obj.isNull("email") ? null : obj.getString("email");
		String password = obj.isNull("password") ? null : obj.getString("password");
        if (email == null) {
        	responseJson.put("success", false);
            responseJson.put("acount", "email是必要欄位");
		}
        if (password == null) {
            responseJson.put("success", false);
            responseJson.put("password", "密碼是必要欄位");
		}
        if (email != null && password != null) {
        	Member member = memberservice.checkLogin(email, password);
        	if (member != null) {
                responseJson.put("success", true);
                responseJson.put("message", "登入成功");
        	} else {
                responseJson.put("success", false);
                responseJson.put("message", "帳號或密碼有錯");
        	}
        }
        
        return responseJson.toString();
	}
	// 會員申請
	@PostMapping("/member/apply")
	public String applyMember(@RequestBody String json) throws Exception{
		JSONObject responseJson = new JSONObject();
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
			if (memberservice.newMember(newMember) !=null) {
				responseJson.put("message", "加入會員成功");
				responseJson.put("success", true);
			} else {
				responseJson.put("message", "加入會員失敗");
				responseJson.put("success", false);
			}
		}else {
			responseJson.put("message", "請檢查是否有資料尚未填寫完整");
			responseJson.put("success", false);
		}
		
		return responseJson.toString();
	}
	// 會員資料更新
	
	@PutMapping("/member/{pk}")
	public String updateData(@PathVariable("pk") Integer id, String json) {
		JSONObject responseJson = new JSONObject();		
		try {
			if (id != null) {
				if (memberservice.updateData(json) != null) {
					responseJson.put("message", "更新成功");
					responseJson.put("success", true);
				} else {
					responseJson.put("message", "請查資料是否完整");
					responseJson.put("success", false);
				}
			}else {
				responseJson.put("message", "用戶不存在請重新登入");
				responseJson.put("success", false);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return responseJson.toString();
	}
	
}