package tw.team.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import tw.team.project.model.HousingManagement;
import tw.team.project.service.HousingManagementService;
import tw.team.project.util.DatetimeConverter;

@RestController
@RequestMapping("/hotel")
@CrossOrigin
public class HousingManagementController {

	@Autowired
	private HousingManagementService housingManagementService;
	
	
	//新增時更動 roomAssignment 該天的left - roomManagement 的房間state
	//checkOI 及 addition 新繩增加 totalfee
	
	@PostMapping("/housingManagement")
	public String create(@RequestBody String json, HttpSession httpSession) throws JSONException {
		Integer id = null;
		JSONObject responseJson = new JSONObject();
		HousingManagement housingManagement = housingManagementService.create(json, id);
		try {
			if (housingManagement == null) {
				responseJson.put("success", false);
				responseJson.put("message", "新增失敗");
			} else {
				responseJson.put("success", true);
				responseJson.put("message", "新增成功");
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return responseJson.toString();
	}
	

	@GetMapping("/housingManagement/number/{number}")
	public String existsByItem(@PathVariable("number") Integer number) {
		JSONObject responseJson = new JSONObject();
		boolean exist = housingManagementService.existsById(number);
		try {
			if (exist) {
				responseJson.put("success", false);
				responseJson.put("message", "房號已存在");
			} else {
				responseJson.put("success", true);
				responseJson.put("message", "房號不存在");
			}
			return responseJson.toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@PostMapping("/housingManagement/find")
	public String find(@RequestBody String json) throws JSONException {
		JSONObject responseJson = new JSONObject();

		JSONArray array = new JSONArray();
		List<HousingManagement> rooms = housingManagementService.find(json);
	    if (rooms != null && !rooms.isEmpty()) {
	        for (HousingManagement room : rooms) {
	            String checkInTime = DatetimeConverter.toString(room.getCheckInTime(), "yyyy-MM-dd HH:mm:ss");
	            String checkOutTime = DatetimeConverter.toString(room.getCheckOutTime(), "yyyy-MM-dd HH:mm:ss");
	            
	            JSONObject housing = new JSONObject()
	                .put("remarks", room.getRemarks())
	                .put("checkInTime", checkInTime)
	                .put("checkOutTime", checkOutTime)
	                .put("totalAdditional", room.getTotalAdditional())
	                .put("totalCompensation", room.getTotalCompensation());
	            
	            array.put(housing);
	        }
		}
		responseJson.put("list", array);

		long count = housingManagementService.count(json);
		responseJson.put("count", count);

		return responseJson.toString();
	}
	

	@PutMapping("/housingManagement/{pk}")
	public String updateData(@PathVariable("pk") Integer id, @RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		try {
			if (id != null) {
				if (housingManagementService.modify(id, json) != null) {
					responseJson.put("message", "更新成功");
					responseJson.put("success", true);
				} else {
					responseJson.put("message", "請查資料是否完整");
					responseJson.put("success", false);
				}
			} else {
				responseJson.put("message", "資料已過期請重新登入");
				responseJson.put("success", false);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return responseJson.toString();
	}


	@GetMapping("/housingManagement/{pk}")
	public String findById(@PathVariable(name = "pk") Integer id) throws JSONException {
		JSONObject responseJson = new JSONObject();
		JSONArray array = new JSONArray();
		HousingManagement room = housingManagementService.findById(id);
		if (room != null) {
			JSONObject housing = new JSONObject().put("remarks", room.getRemarks())
					.put("checkInTime", room.getCheckInTime()).put("checkOutTime", room.getCheckOutTime())
					.put("totalAdditional", room.getTotalAdditional())
					.put("totalCompensation", room.getTotalCompensation());
			array.put(housing);
		}
		responseJson.put("list", array);
		return responseJson.toString();
	}
}
