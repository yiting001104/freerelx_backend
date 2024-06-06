package tw.team.project.controller;

import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.domain.Page;
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
import org.springframework.web.bind.annotation.RestController;

import tw.team.project.dto.RoomAssignmentDTO;
import tw.team.project.model.RoomAssignment;
import tw.team.project.service.RoomAssignmentService;
import tw.team.project.util.JsonContainer2;


@RestController
@RequestMapping("/hotel")
@CrossOrigin
public class RoomAssignmentController {
	@Value("${local.serverPort}")
	private String serverUri;
	
	@Autowired
	private RoomAssignmentService roomAssignmentService;
	
	//下單時確認date的left不為0
	//oderdatil下單後date的left做更動
	//取消訂單後datedate的left做更動
	//透過roomInfo做分類select date top5 left>0的page分頁
	
	
	//查詢多筆資料~
	@GetMapping("/backend/roomAssignment")
	public ResponseEntity<?> listRoomAssignment(@RequestParam(value = "p", defaultValue = "1") Integer Number) {
		Page<RoomAssignment> page = roomAssignmentService.findAll(Number);
		List<RoomAssignmentDTO> roomList = new ArrayList<>();
		for (RoomAssignment rooms : page.getContent()) {
			roomList.add(new JsonContainer2().setRoomAssignment(rooms));
		}
		return ResponseEntity.ok(roomList);
	}
	
	//findOrderById
	
	
	//findById~
	  @GetMapping("/backend/roomAssignment/{pk}")
	  public ResponseEntity<?> findById(@PathVariable(name = "pk") Integer id) {
	  	RoomAssignment room = roomAssignmentService.findById(id);
	  	if(room!=null) {
	  		RoomAssignmentDTO roomDTO = new JsonContainer2().setRoomAssignment(room);
	  		ResponseEntity<RoomAssignmentDTO> ok = ResponseEntity.ok(roomDTO);
	  		return ok;
	  	} else {
	  		ResponseEntity<Void> notfound = ResponseEntity.notFound().build();
	  		return notfound;
	  	}
	  }

	//delete~
	@DeleteMapping("/backend/roomAssignment/{pk}")
	public ResponseEntity<Void> remove(@PathVariable(name = "pk") Integer id) {
		if (id != null && id != 0) {
			boolean exists = roomAssignmentService.existById(id);
			if (exists) {
				if (roomAssignmentService.delete(id)) {
					return ResponseEntity.noContent().build();
				}
			}
		}
		return ResponseEntity.notFound().build();
	}
	
//	//update
//	@PutMapping("/backend/roomAssignment/{pk}")
//	public ResponseEntity<?> modify(@PathVariable (name = "pk") Integer id, @RequestBody RoomAssignment bean) {
//		if(bean!=null && bean.getId()!=null && bean.getId()!=0) {
//			boolean exists = roomAssignmentService.existById(bean.getId());
//			if(exists) {
//				RoomAssignment room = roomAssignmentService.update(bean);
//				if(room!=null) {
//					return ResponseEntity.ok(room);
//				}
//			}
//		}return ResponseEntity.notFound().build();
//	}
	
	
	@PutMapping("/backend/roomAssignment/{pk}")
	public String modify(@PathVariable(name = "pk") Integer id, @RequestBody String json) throws JSONException {
	    JSONObject responseJson = new JSONObject();
	    if (id == null) {
	        responseJson.put("success", false);
	        responseJson.put("message", "id是必要欄位");
	    } else if (!roomAssignmentService.existById(id)) {
	        responseJson.put("success", false);
	        responseJson.put("message", "id不存在");
	    } else {
	        RoomAssignment rooms = roomAssignmentService.updateData(id, json);
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

	// 查詢日期範圍內 left 最小的記錄
    @GetMapping("/backend/roomAssignment/fullAvailability")
    public ResponseEntity<?> findFullyAvailableAssignments(
            @RequestParam(name = "startDate") String startDate,
            @RequestParam(name = "endDate") String endDate,
            @RequestParam(name = "roomId") Integer roomId) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date start = new Date(sdf.parse(startDate).getTime());
            Date end = new Date(sdf.parse(endDate).getTime());

            boolean isFullyAvailable = roomAssignmentService.isFullyAvailableInRange(start, end, roomId);
            if (isFullyAvailable) {
                List<RoomAssignment> assignments = roomAssignmentService.findAvailableAssignmentsInRange(start, end, roomId);
                Integer minLeft = roomAssignmentService.findMinLeftInRange(start, end, roomId);

                List<RoomAssignmentDTO> assignmentDTOs = new ArrayList<>();
                for (RoomAssignment assignment : assignments) {
                    assignmentDTOs.add(new JsonContainer2().setRoomAssignment(assignment));
                }

                JSONObject responseJson = new JSONObject();
                responseJson.put("assignments", assignmentDTOs);
                responseJson.put("minLeft", minLeft);

                return ResponseEntity.ok(responseJson.toString());
            } else {
                return ResponseEntity.ok("The room is not fully available in the specified date range.");
            }
        } catch (ParseException | JSONException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Invalid date format");
        }
    }
	
	//insert
	@PostMapping("/backend/roomAssignment")
	public ResponseEntity<?> create(@RequestBody RoomAssignment bean) {
		if(bean!=null && bean.getId()!=null && bean.getId()!=0) {
			boolean exists = roomAssignmentService.existById(bean.getId());
			if(!exists) {
				RoomAssignment room = roomAssignmentService.insert(bean);
				if(room!=null) {
					String uri = serverUri+"/pages/roomAssignment/"+room.getId();
					return ResponseEntity.created(URI.create(uri))
    						.contentType(MediaType.APPLICATION_JSON)
    						.body(room);
    			}
    		}
    	}return ResponseEntity.noContent().build();
    }
	
	
	
	
	@PostMapping("/backend/roomAssignment/findID/{date}/{id}")
	public ResponseEntity<?> findByDate(@PathVariable(name = "date") String date, @PathVariable(name = "id") Integer id, @RequestBody String json) {
		Date inputDate=null;
		try {
			if (date!=null && date.length()!=0) {
				inputDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  	RoomAssignment room = roomAssignmentService.findByDate(inputDate, id, json);//
	  	if(room!=null) {
	  		RoomAssignmentDTO roomDTO = new JsonContainer2().setRoomAssignment(room);
	  		ResponseEntity<RoomAssignmentDTO> ok = ResponseEntity.ok(roomDTO);
	  		return ok;
	  	} else {
	  		ResponseEntity<Void> notfound = ResponseEntity.notFound().build();
	  		return notfound;
	  	}
	  }

}