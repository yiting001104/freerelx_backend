package tw.team.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.team.project.model.RoomType;
import tw.team.project.service.RoomTypeService;


@RestController
@RequestMapping("/hotel")
public class RoomTypeController {

	@Autowired
	private RoomTypeService roomTypeService;
	
	
	//管理員新增
	@PostMapping("/type")
    public String create(@RequestBody String json) {
        JSONObject responseJson = new JSONObject();
        try {
			JSONObject obj = new JSONObject(json);
			Integer id = obj.isNull("id") ? null : obj.getInt("id");
			if(id==null) {
			    responseJson.put("success", false);
			    responseJson.put("message", "id是必要欄位");
			} else if(roomTypeService.existById(id) ) {
			    responseJson.put("success", false);
			    responseJson.put("message", "id已存在");
			} else {
				RoomType type = roomTypeService.create(json);
			    if(type==null) {
			        responseJson.put("success", false);
			        responseJson.put("message", "新增失敗");
			    } else {
			        responseJson.put("success", true);
			        responseJson.put("message", "新增成功");
			    }
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return responseJson.toString();
    }
	
	
//	@PostMapping("/member/apply")
//	public String applyMember(@RequestBody String json) throws Exception{
//		JSONObject responseJson = new JSONObject();
//		JSONObject obj = new JSONObject(json);
//		
//		String name = obj.isNull("name") ? null : obj.getString("name");
//		String gender = obj.isNull("gender") ? null : obj.getString("gender");
//		String birth = obj.isNull("birth") ? null : obj.getString("birth");
//		Date birth1;
//		if (birth != null) {
//			birth1=new SimpleDateFormat("yyyy-MM-dd").parse(birth);
//		} else
//			birth1 = null;
//		
//		
//		String nationalId = obj.isNull("national_id") ? null : obj.getString("national_id");
//		String email = obj.isNull("email") ? null : obj.getString("email");
//		String phone = obj.isNull("phone_number") ? null : obj.getString("phone_number");
//		String creditCard = obj.isNull("credit_card") ? null : obj.getString("credit_card");
//		String contactAddress = obj.isNull("contact_address") ? null : obj.getString("contact_address");
//		String password = obj.isNull("password") ? null : obj.getString("password");
//		String nationality = obj.isNull("nationality") ? null : obj.getString("nationality");
//		Date date = new Date();
//		System.out.println(date);
//		if (name != null && gender != null && birth1 !=null && nationalId != null && email != null && phone !=null && contactAddress!=null && password!=null && nationality!=null) {
//			Member newMember = new Member();
//			newMember.setMemberName(name);
//			newMember.setGender(gender);
//			newMember.setBirth(birth1);
//			newMember.setNationId(nationalId);
//			newMember.setEmail(email);
//			newMember.setPhoneNumber(phone);
//			newMember.setCreditCard(creditCard);
//			newMember.setContactAddress(contactAddress);
//			newMember.setNationality(nationality);
//			newMember.setPassword(password);
//			newMember.setRegistrationDate(date);
//			if (memberservice.newMember(newMember) !=null) {
//				responseJson.put("message", "加入會員成功");
//				responseJson.put("success", true);
//			} else {
//				responseJson.put("message", "加入會員失敗");
//				responseJson.put("success", false);
//			}
//		}else {
//			responseJson.put("message", "請檢查是否有資料尚未填寫完整");
//			responseJson.put("success", false);
//		}
//		
//		return responseJson.toString();
//	}
	
	
}
