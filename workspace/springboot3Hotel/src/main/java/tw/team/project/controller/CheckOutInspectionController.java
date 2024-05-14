package tw.team.project.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.team.project.model.CheckOutInspection;
import tw.team.project.service.CheckOutInspectionService;


@RestController
@RequestMapping("/hotel")
@CrossOrigin
public class CheckOutInspectionController {

	
	@Autowired
	private CheckOutInspectionService checkOutInspectionService;
	
	// findAll~
	@PostMapping("/checkOutInspection/findall")
	public String findAll() throws JSONException {
		List<CheckOutInspection> checks = checkOutInspectionService.findAll();
		JSONArray array = new JSONArray();
		
		if (checks != null && !checks.isEmpty())
		for (CheckOutInspection check : checks) {
			JSONObject item = new JSONObject()
					.put("id", check.getId())
					.put("compensation", check.getCompensation())
					.put("fee", check.getFee())
					.put("photo", check.getPhoto())
					.put("housing_management_id", check.getHousingManagement());
					
			array.put(item);
		}

		return array.toString();
	}
	
	// findById
    @GetMapping("/checkOutInspection/{pk}")
    public ResponseEntity<?> findById(@PathVariable("pk") Integer id){
    	CheckOutInspection checks = checkOutInspectionService.findById(id);
        if (checks != null){
        	ResponseEntity<CheckOutInspection> ok = ResponseEntity.ok(checks);
            return ok;
        } else {
            ResponseEntity<Void> notFound = ResponseEntity.notFound().build();
            return notFound;
        }
    }
	
	// update~
	@PutMapping("/checkOutInspection/{pk}")
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
    
 // create
    @PostMapping("/checkOutInspection")
    public ResponseEntity<?> create(@RequestBody CheckOutInspection bean) {
        if (bean != null && bean.getId() != null && bean.getId() != 0) {
            // Check if the entity already exists
            boolean exists = checkOutInspectionService.existById(bean.getId());
            if (!exists) {
                // Insert the entity
                CheckOutInspection check = checkOutInspectionService.insert(bean);
                if (check != null) {
                    // Build the URI for the created resource
                	String uri = "http://localhost:8080/hotel/checkOutInspection/"+check.getId();
    				return ResponseEntity.created(URI.create(uri))
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(check);
                }
            }
        }
        return ResponseEntity.noContent().build();
    }

}
