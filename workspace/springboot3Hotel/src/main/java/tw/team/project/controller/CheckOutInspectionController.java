package tw.team.project.controller;

import java.net.URI;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import org.springframework.web.bind.annotation.RestController;

import tw.team.project.dto.CheckOutInspectionDTO;
import tw.team.project.model.CheckOutInspection;
import tw.team.project.service.CheckOutInspectionService;
import tw.team.project.util.JsonContainer2;


@RestController
@RequestMapping("/hotel")
@CrossOrigin
public class CheckOutInspectionController {

	@Value("${local.serverPort}")
	private String serverUri;
	//更新OR新增 時更改 housingMangement totalfee
	
	@Autowired
	private CheckOutInspectionService checkOutInspectionService;
	
	// findAll~
	
    @GetMapping("/backend/checkOutInspection")
    public ResponseEntity<List<CheckOutInspection>> getAllCheckOutInspections() {
        List<CheckOutInspection> inspections = checkOutInspectionService.findAll();
        return ResponseEntity.ok(inspections);
    }
	
//    @GetMapping("/backend/checkOutInspection")
//    public ResponseEntity<?> LisgCheckOutInspection(@RequestParam(value = "p",defaultValue = "1") Integer pageNumber){
//        Page<CheckOutInspection> page = checkOutInspectionService.findAll(pageNumber);
//        List<CheckOutInspectionDTO> checkOutInspectionList = new ArrayList<>();
//        for (CheckOutInspection checkOutInspection : page.getContent()){
//        	checkOutInspectionList.add(new JsonContainer2().setCheckOutInspection(checkOutInspection));
//        }
//        return ResponseEntity.ok(checkOutInspectionList);
//        
//    }
	
	// findById~
    @GetMapping("/backend/checkOutInspection/{pk}")
    public ResponseEntity<?> findById(@PathVariable("pk") Integer id){
    	CheckOutInspection checks = checkOutInspectionService.findById(id);
        if (checks != null){
        	CheckOutInspectionDTO checkDTO = new JsonContainer2().setCheckOutInspection(checks);
        	ResponseEntity<CheckOutInspectionDTO> ok = ResponseEntity.ok(checkDTO);
            return ok;
        } else {
            ResponseEntity<Void> notFound = ResponseEntity.notFound().build();
            return notFound;
        }
    }
	
    
	// update~	
	@PutMapping("/backend/checkOutInspection/{pk}")
	public ResponseEntity<?> modify(@PathVariable("pk") Integer id, @RequestBody CheckOutInspection entity) {
	    if (entity != null && entity.getId() != null && entity.getId() != 0) {
	        boolean exists = checkOutInspectionService.existById(entity.getId());
	        if (exists) {
	            CheckOutInspection newCheck = checkOutInspectionService.update(entity);
	            if (newCheck != null) {
	                return ResponseEntity.ok(newCheck);
	            }
	        }
	    }
	    // 返回具體的錯誤訊息
	    return ResponseEntity.badRequest().body("更新失敗：無法找到相應的記錄或資料不完整");
	}

	// create~
	@PostMapping("/backend/checkOutInspection")
	public ResponseEntity<?> create(@RequestBody CheckOutInspection bean) {
	    if (bean != null) {
	        // Insert the entity
	        CheckOutInspection check = checkOutInspectionService.insert(bean);
	        if (check != null) {
	            // Build the URI for the created resource
	            String uri = serverUri + "/hotel/checkOutInspection/" + check.getId();
	            return ResponseEntity.created(URI.create(uri))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .body(check); // 回應新增的 CheckOutInspection 物件
	        }
	    }
	    // 返回具體的錯誤訊息
	    return ResponseEntity.badRequest().body("新增失敗：資料不完整或無效");
	}



    @DeleteMapping("/backend/checkOutInspection/{pk}")
	public String remove(@PathVariable(name = "pk") Integer id) throws JSONException {
		JSONObject responseJson = new JSONObject();
		if (id == null) {
			responseJson.put("success", false);
			responseJson.put("message", "id是必要欄位");
		} else if (!checkOutInspectionService.existById(id)) {
			responseJson.put("success", false);
			responseJson.put("message", "id不存在");
		} else {
			if (checkOutInspectionService.delete(id)) {
				responseJson.put("success", true);
				responseJson.put("message", "刪除成功");
			} else {
				responseJson.put("success", false);
				responseJson.put("message", "刪除失敗");
			}
		}
		return responseJson.toString();
	}
}