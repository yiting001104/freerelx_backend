package tw.team.project.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;
import tw.team.project.dto.MemberDTO;
import tw.team.project.model.Member;
import tw.team.project.model.OrderRoom;
import tw.team.project.service.MemberService;
import tw.team.project.service.OrderRoomService;
import tw.team.project.util.JsonWebTokenUtility;

@RestController
@RequestMapping("/hotel")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MemberController {

	@Autowired
	private MemberService memberservice;
	@Autowired
	private JsonWebTokenUtility jsonWebTokenUtility;
	// 後台查看會員所有資料
	@PostMapping(value = "/members/find")
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
					.put("picture", person.getPicture())
					.put("registration_date", person.getRegistrationDate())
					.put("member_status", person.getMemberStatus());
			array.put(item);
		}
//		responseObj.put("list", array);
		return array.toString();
	}
	
	// 會員登入
	@PostMapping("/member/login")
	public String login(@RequestBody String json, HttpSession httpSession) throws JSONException{
		JSONObject responseJson = new JSONObject();
		JSONObject obj = new JSONObject(json);
		
		String email = obj.isNull("email") ? null : obj.getString("email");
		String password = obj.isNull("password") ? null : obj.getString("password");
        if (email == null || email.length()==0) {
        	responseJson.put("success", false);
            responseJson.put("acount", "email是必要欄位");
		}
        if (password == null || password.length()==0) {
            responseJson.put("success", false);
            responseJson.put("password", "密碼是必要欄位");
		}
        if (email != null && password != null && email.length()!=0 && password.length()!=0) {
        	Member member = memberservice.checkLogin(email, password);
        	if (member != null) {
        		
        		if (!member.getMemberStatus().equals("banned")) {
        			
    		      responseJson.put("success", true);
                  responseJson.put("message", "登入成功");
                  httpSession.setAttribute("loginUserId", member.getMemberId());
                  httpSession.setAttribute("loginUserName", member.getMemberName());
                      
          			JSONObject user = new JSONObject()
          					.put("custid", member.getMemberId())
          					.put("email", member.getEmail());
          			String token = jsonWebTokenUtility.createEncryptedToken(user.toString(), null);
          			responseJson.put("token", token);
          			responseJson.put("user", member.getMemberName());
          			responseJson.put("userId", member.getMemberId());
        		}else {
        			responseJson.put("success", false);
        			responseJson.put("message", "你的帳號被管理禁用，請聯繫管理員");
        		}
                
        	} else {
                responseJson.put("success", false);
                responseJson.put("message", "帳號或密碼有錯");
        	}
        }
        
        return responseJson.toString();
	}
	
	// 會員登出
	@PutMapping("/member/logout/{pk}")
	public String logoutAction(@PathVariable("pk") Integer id,HttpSession httpSession) throws JSONException{
		Member member = memberservice.logout(id);
		JSONObject responseJson = new JSONObject();
		if (member!=null) {
            responseJson.put("success", true);
            responseJson.put("message", "登出成功");
		}else {
            responseJson.put("success", false);
            responseJson.put("message", "登出失敗");
		}
		// 方法一 這可以做到第三方廣告的使用
//		httpSession.removeAttribute("loginUser");
//		httpSession.removeAttribute("loginUserId");
		
		// 方法二
		httpSession.invalidate();
		
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
		if (birth != null && birth.length()!=0) {
			birth1 = new SimpleDateFormat("yyyy-MM-dd").parse(birth);
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
	@PutMapping("/member/alert/{pk}")
	public String updateData(@PathVariable("pk") Integer id, @RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		System.out.println(json);
		try {
			if (id != null) {
				if (memberservice.updateData(id, json) != null) {
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
	
	// 測試更新資料含有圖片
	@PutMapping(value = "/member/alert2/{pk}", consumes = "multipart/form-data")//, 
	public String updateData2(@PathVariable("pk") Integer id, @RequestParam String json, @RequestParam(required = false) MultipartFile multipartFile) throws JSONException { //@ModelAttribute NoteDTO notedto 
		JSONObject responseJson = new JSONObject();
//		MultipartFile  multipartFile = notedto.getMultipartFile();
		JSONObject jsonObj = new JSONObject(json);
		try {
			if (id != null) {
				if (memberservice.updateData2(id, jsonObj, multipartFile) != null) {
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
	
	@DeleteMapping("/backend/delete/{id}")
	public String deleteMem(@PathVariable("id") Integer id) {
		memberservice.deleteMember(id);
		JSONObject responseJson = new JSONObject();
		try {
			responseJson.put("message", "刪除成功");
			responseJson.put("success", true);
			return responseJson.toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	//顯示照片
	@GetMapping("/photos/download")
	public ResponseEntity<byte[]> downloadImage(@RequestParam Integer id){
		Member member = memberservice.findbyId(id);
		byte[] photoFile = member.getPicture();
		
		HttpHeaders headers = new HttpHeaders();//用來記錄content type，servlet是用response 的setContent 可以看來一個大括號-spring作業參考(老師的channel)
		headers.setContentType(MediaType.IMAGE_JPEG);
		
		//                                  資料     Header   HttpStatusCode
		return new ResponseEntity<byte[]>(photoFile,headers,HttpStatusCode.valueOf(200));
		// 另一種寫法，他已經幫妳寫好了
		//return new ResponseEntity<byte[]>(photoFile,headers,HttpStatus.OK);
	}
	@GetMapping("/member/{pk}")
	public ResponseEntity<?> findById(@PathVariable("pk") Integer id){
		Member member = memberservice.findbyId(id);
		if (member!=null) {
			return ResponseEntity.ok(member);
		}
		return ResponseEntity.notFound().build();
		
	}
	@PutMapping("/members/password/{pk}")
	public ResponseEntity<?> modifyPassword(@PathVariable("pk") Integer id, @RequestBody String json){
		try {
			Member member = memberservice.updatePassword(json, id);
			if (member!=null) {
				return ResponseEntity.ok(member);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.notFound().build();
	}
	@PutMapping("/backend/members/status/{pk}")
	public ResponseEntity<?> modifyStatus(@PathVariable("pk") Integer id, @RequestBody String json){
		try {
			Member member = memberservice.updateStatus(json, id);
			if (member!=null) {
				return ResponseEntity.ok(member);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.notFound().build();
	}

	
//    @GetMapping("/orderRoom/transactions/{pk}")
//    public ResponseEntity<?> findById(@PathVariable("pk") Integer id){
//        Transaction trans = transactionService.findById(id);
//        if (trans != null){
//            TransactionDTO transDTO = new JsonContainer().setTransaction(trans);
//            ResponseEntity<TransactionDTO> ok = ResponseEntity.ok(transDTO);
//            return ok;
//        } else {
//            ResponseEntity<Void> notFound = ResponseEntity.notFound().build();
//            return notFound;
//        }
//    }
}