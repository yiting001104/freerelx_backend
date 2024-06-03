package tw.team.project.controller;

import java.net.URI;
import java.util.ArrayList;
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
    public ResponseEntity<?> LisgCheckOutInspection(@RequestParam(value = "p",defaultValue = "1") Integer pageNumber){
        Page<CheckOutInspection> page = checkOutInspectionService.findAll(pageNumber);
        List<CheckOutInspectionDTO> checkOutInspectionList = new ArrayList<>();
        for (CheckOutInspection checkOutInspection : page.getContent()){
        	checkOutInspectionList.add(new JsonContainer2().setCheckOutInspection(checkOutInspection));
        }
        return ResponseEntity.ok(checkOutInspectionList);
        
    }
	
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
		if(entity != null && entity.getId() != null && entity.getId() != 0) {
			boolean exists = checkOutInspectionService.existById(entity.getId());
			if(exists) {
				CheckOutInspection newCheck = checkOutInspectionService.update(entity);
				if(newCheck != null) {
					return ResponseEntity.ok(newCheck);
				}
			}
		}return ResponseEntity.notFound().build();
	}
	
	// create
//    @PostMapping("/checkOutInspection")
//    public ResponseEntity<?> create(@RequestBody CheckOutInspection bean) {
//    	if(bean!=null && bean.getId()!=null && bean.getId()!=0) {
//    		boolean exists = checkOutInspectionService.existById(bean.getId());
//    		if(!exists) {
//    			CheckOutInspection check = checkOutInspectionService.insert(bean);
//    			if(check!=null) {
//    				String uri = "http://localhost:8080/hotel/checkOutInspection/"+check.getId();
//    				return ResponseEntity.created(URI.create(uri))
//    						.contentType(MediaType.APPLICATION_JSON)
//    						.body(check);
//    			}
//    		}
//    	}return ResponseEntity.noContent().build();
//    }
    
 // create~
    @PostMapping("/backend/checkOutInspection")
    public ResponseEntity<?> create(@RequestBody CheckOutInspection bean) {
        if (bean != null) {
       
                // Insert the entity
                CheckOutInspection check = checkOutInspectionService.insert(bean);
                if (check != null) {
                    // Build the URI for the created resource
                	String uri = serverUri+"/hotel/checkOutInspection"+check.getId();
    				return ResponseEntity.created(URI.create(uri))
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(check);
            }
        }
        return ResponseEntity.notFound().build();
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
