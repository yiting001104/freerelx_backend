package tw.team.project.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import tw.team.project.dto.HousingManagementDTO;
import tw.team.project.model.HousingManagement;
import tw.team.project.service.HousingManagementService;
import tw.team.project.util.DatetimeConverter;
import tw.team.project.util.JsonContainer2;

@RestController
@RequestMapping("/hotel")
@CrossOrigin
public class HousingManagementController {

	@Autowired
	private HousingManagementService housingManagementService;

	// 新增時更動 roomAssignment 該天的left - roomManagement 的房間state
	// checkOI 及 addition 新繩增加 totalfee

	//findAll
	@GetMapping("/backend/housingManagement")
	public ResponseEntity<?> listHoustingRoomPage(@RequestParam(value = "p", defaultValue = "1") Integer Number) {
		Page<HousingManagement> page = housingManagementService.findAll(Number);
		List<HousingManagementDTO> roomList = new ArrayList<>();
		for (HousingManagement rooms : page.getContent()) {
			roomList.add(new JsonContainer2().setHousingManagement(rooms));
		}
		return ResponseEntity.ok(roomList);
	}
	
	//findById
    @GetMapping("/backend/housingManagement/{pk}")
    public ResponseEntity<?> findById(@PathVariable("pk") Integer id){
    	HousingManagement room = housingManagementService.findById(id);
        if (room != null){
        	HousingManagementDTO roomDTO = new JsonContainer2().setHousingManagement(room);
        	ResponseEntity<HousingManagementDTO> ok = ResponseEntity.ok(roomDTO);
            return ok;
        } else {
            ResponseEntity<Void> notFound = ResponseEntity.notFound().build();
            return notFound;
        }
    }
    

    @PostMapping("/backend/housingManagement")
    public ResponseEntity<String> create(@RequestBody String json, HttpSession httpSession) {
        try {
            JSONObject obj = new JSONObject(json);
            Integer orderid = obj.isNull("orderid") ? null : obj.getInt("orderid");
            Integer roomid = obj.isNull("roomid") ? null : obj.getInt("roomid");
            if (orderid == null && roomid == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID cannot be null");
            }else {

            JSONObject responseJson = new JSONObject();
            HousingManagement housingManagement = housingManagementService.create(json, orderid, roomid);
            if (housingManagement == null) {
                responseJson.put("success", false);
                responseJson.put("message", "新增失敗");
            } else {
                responseJson.put("success", true);
                responseJson.put("message", "新增成功");
            }
            return ResponseEntity.ok(responseJson.toString());}
        } catch (JSONException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    
    
	@PutMapping("/backend/housingManagement/{pk}/{roomid}")
	public String updateData(@PathVariable("pk") Integer id, @PathVariable("roomid") Integer roomid, @RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		try {
			if (id != null) {
				if (housingManagementService.modify(id, roomid, json) != null) {
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
     

	@GetMapping("/backend/housingManagement/number/{number}")
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

	@PostMapping("/backend/housingManagement/find")
	public String find(@RequestBody String json) throws JSONException {
		JSONObject responseJson = new JSONObject();

		JSONArray array = new JSONArray();
		List<HousingManagement> rooms = housingManagementService.find(json);
		if (rooms != null && !rooms.isEmpty()) {
			for (HousingManagement room : rooms) {
				String checkInTime = DatetimeConverter.toString(room.getCheckInTime(), "yyyy-MM-dd HH:mm:ss");
				String checkOutTime = DatetimeConverter.toString(room.getCheckOutTime(), "yyyy-MM-dd HH:mm:ss");

				JSONObject housing = new JSONObject().put("remarks", room.getRemarks()).put("checkInTime", checkInTime)
						.put("checkOutTime", checkOutTime).put("totalAdditional", room.getTotalAdditional())
						.put("totalCompensation", room.getTotalCompensation());

				array.put(housing);
			}
		}
		responseJson.put("list", array);

		long count = housingManagementService.count(json);
		responseJson.put("count", count);

		return responseJson.toString();
	}
	
	@PutMapping("/backend/housingManagement/{pk}")
	public String updateCheckOutTime(@PathVariable("pk") Integer id, @RequestBody String json) {
	    JSONObject responseJson = new JSONObject();
	    try {
	        if (id != null) {
	            if (housingManagementService.updateCheckOutTime(id, json) != null) {
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
	        e.printStackTrace();
	    }
	    return responseJson.toString();
	}

	
	@PutMapping("/backend/housingManagement/changeRoom/{pk}")
	public String changeRoom(@PathVariable("pk") Integer id, @RequestBody String json) {
	    JSONObject responseJson = new JSONObject();
	    try {
	        JSONObject obj = new JSONObject(json);
	        Integer newRoomId = obj.getInt("newRoomId");
	        
	        if (id != null && newRoomId != null) {
	            if (housingManagementService.changeRoom(id, newRoomId) != null) {
	                responseJson.put("message", "換房成功");
	                responseJson.put("success", true);
	            } else {
	                responseJson.put("message", "換房失敗，請檢查資料");
	                responseJson.put("success", false);
	            }
	        } else {
	            responseJson.put("message", "資料不完整或已過期，請重試");
	            responseJson.put("success", false);
	        }
	    } catch (JSONException e) {
	        e.printStackTrace();
	    }
	    return responseJson.toString();
	}



}