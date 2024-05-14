package tw.team.project.controller;

import java.net.URI;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
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

import tw.team.project.model.RoomAssignment;
import tw.team.project.service.RoomAssignmentService;


@RestController
@RequestMapping("/hotel")
@CrossOrigin
public class RoomAssignmentController {

	@Autowired
	private RoomAssignmentService roomAssignmentService;
	
	//下單時確認date的left不為0
	//oderdatil下單後date的left做更動
	//取消訂單後datedate的left做更動
	//透過roomInfo做分類select date top5 left>0的page分頁
	

	
	//查詢多筆資料
	@GetMapping("/roomAssignment")
	public ResponseEntity<?> find(@RequestParam Map<String, String> param) {
		JSONObject obj = new JSONObject(param);
		List<RoomAssignment> room = roomAssignmentService.find(obj.toString());
		return ResponseEntity.ok(room);
	}

	//delete
	@DeleteMapping("/roomAssignment/{pk}")
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
	
	//update
	@PutMapping("/roomAssignment/{pk}")
	public ResponseEntity<?> modify(@PathVariable (name = "pk") Integer id, @RequestBody RoomAssignment bean) {
		if(bean!=null && bean.getId()!=null && bean.getId()!=0) {
			boolean exists = roomAssignmentService.existById(bean.getId());
			if(exists) {
				RoomAssignment room = roomAssignmentService.update(bean);
				if(room!=null) {
					return ResponseEntity.ok(room);
				}
			}
		}return ResponseEntity.notFound().build();
	}
	
	//insert
	@PostMapping("/roomAssignment")
	public ResponseEntity<?> create(@RequestBody RoomAssignment bean) {
		if(bean!=null && bean.getId()!=null && bean.getId()!=0) {
			boolean exists = roomAssignmentService.existById(bean.getId());
			if(!exists) {
				RoomAssignment room = roomAssignmentService.insert(bean);
				if(room!=null) {
					String uri = "http://localhost:8080/pages/roomAssignment/"+room.getId();
					return ResponseEntity.created(URI.create(uri))
    						.contentType(MediaType.APPLICATION_JSON)
    						.body(room);
    			}
    		}
    	}return ResponseEntity.noContent().build();
    }
	
  @GetMapping("/roomAssignment/{pk}")
  public ResponseEntity<?> findById(@PathVariable(name = "pk") Integer id) {
  	RoomAssignment room = roomAssignmentService.findById(id);
  	if(room!=null) {
  		ResponseEntity<RoomAssignment> ok = ResponseEntity.ok(room);
  		return ok;
  	} else {
  		ResponseEntity<Void> notfound = ResponseEntity.notFound().build();
  		return notfound;
  	}
  }
	
	
//	//有問題
//    @GetMapping("/roomAssignment/{pk}")
//    public ResponseEntity<?> findByDate(@PathVariable(name = "pk") Date date) {
//    	RoomAssignment room = roomAssignmentService.findByDate(date);
//    	if(room!=null) {
//    		ResponseEntity<RoomAssignment> ok = ResponseEntity.ok(room);
//    		return ok;
//    	} else {
//    		ResponseEntity<Void> notfound = ResponseEntity.notFound().build();
//    		return notfound;
//    	}
//    }
	
}
