package tw.team.project.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tw.team.project.dto.RoomManagementDTO;
import tw.team.project.model.RoomManagement;
import tw.team.project.service.RoomManagementService;
import tw.team.project.util.JsonContainer2;

@RestController
@RequestMapping("/hotel")
@CrossOrigin
public class RoomManagementController {
	
	@Autowired
	private RoomManagementService roomManagementService;
	
	//新增HousingManagemant時改變state
	
    @GetMapping("/backend/list/roomManagement")
    public ResponseEntity<List<RoomManagementDTO>> getAllCheckOutInspections() {
        List<RoomManagementDTO> inspections = roomManagementService.findAllListDTO();
        return ResponseEntity.ok(inspections);
    }
	
	@GetMapping("/backend/roomManagement")
	public ResponseEntity<?> listRoomPage(@RequestParam(value = "p", defaultValue = "1") Integer Number) {
	    Page<RoomManagement> page = roomManagementService.findAll(Number);
	    List<RoomManagementDTO> roomList = new ArrayList<>();
	    for (RoomManagement rooms : page.getContent()) {
	        roomList.add(new JsonContainer2().setRoomManagement(rooms));
	    }
	    return ResponseEntity.ok(roomList);
	}
	
	
	@PutMapping("/backend/roomManagement/{pk}")
	public String modify(@PathVariable(name = "pk") Integer id, @RequestBody String json) throws JSONException {
	    JSONObject responseJson = new JSONObject();
	    if (id == null) {
	        responseJson.put("success", false);
	        responseJson.put("message", "id是必要欄位");
	    } else if (!roomManagementService.existById(id)) {
	        responseJson.put("success", false);
	        responseJson.put("message", "id不存在");
	    } else {
	        RoomManagement rooms = roomManagementService.modify(json);
	        if (rooms == null) {
	            responseJson.put("success", false);
	            responseJson.put("message", "修改失敗");
	        } else {
	            responseJson.put("success", true);
	            responseJson.put("message", "修改成功");
	        }
	    }
	    return responseJson.toString();
	}
    
    
    @GetMapping("/backend/roomManagement/{pk}")
    public ResponseEntity<?> findById(@PathVariable(name = "pk") Integer id) {
        RoomManagement room = roomManagementService.findById(id);
        if (room != null) {
            return ResponseEntity.ok(room);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

	 
    @GetMapping("/backend/roomManagement/number/{number}")
    public ResponseEntity<?> findByNumber(@PathVariable(name = "number") Integer number) {
        RoomManagement room = roomManagementService.findByNumber(number);
        if (room != null) {
            return ResponseEntity.ok(room);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}