package tw.team.project.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
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

import tw.team.project.dto.AdditionalChargesDTO;
import tw.team.project.model.AdditionalCharges;
import tw.team.project.service.AdditionalChargesService;
import tw.team.project.util.JsonContainer2;

@RestController
@RequestMapping("/hotel")
@CrossOrigin
public class AdditionalChargesController {

	@Value("${local.serverPort}")
	private String serverUri;
	@Autowired
	private AdditionalChargesService additionalChargesService;

	//更新OR新增 時更改 housingMangement totalfee
	//minibar 處確認下單後座 新增
	
	
	// findAll
    @GetMapping("/additionalCharges")
    public ResponseEntity<?> LisgAdditionalCharges(@RequestParam(value = "p",defaultValue = "1") Integer pageNumber){
        Page<AdditionalCharges> page = additionalChargesService.findAll(pageNumber);
        List<AdditionalChargesDTO> additionalChargesList = new ArrayList<>();
        for (AdditionalCharges additionalCharges : page.getContent()){
        	additionalChargesList.add(new JsonContainer2().setAdditionalCharges(additionalCharges));
        }
        return ResponseEntity.ok(additionalChargesList);
        
    }
//	@PostMapping("/additionalCharges/findall")
//	public String findAll() throws JSONException {
//		List<AdditionalCharges> checks = additionalChargesService.findAll();
//		JSONArray array = new JSONArray();
//
//		if (checks != null && !checks.isEmpty())
//			for (AdditionalCharges add : checks) {
//				JSONObject item = new JSONObject().put("additionalChargesId", add.getAdditionalChargesId())
//						.put("quantity", add.getQuantity()).put("amout", add.getAmount())
//						.put("minibar", add.getMinibar()).put("housingManagement", add.getHousingManagement());
//
//				array.put(item);
//			}
//
//		return array.toString();
//	}

	// findById 修改JSP

	@GetMapping("/additionalCharges/{mid}/{hid}")
	public ResponseEntity<?> findById(@PathVariable(name = "mid") Integer minibarId,
			@PathVariable(name = "hid") Integer housingManagementId) {
		AdditionalCharges addition = additionalChargesService.findById(minibarId, housingManagementId);
		if (addition != null) {
			ResponseEntity<AdditionalCharges> ok = ResponseEntity.ok(addition);
			return ok;
		} else {
			ResponseEntity<Void> notFound = ResponseEntity.notFound().build();
			return notFound;
		}
	}

	//create
	@PostMapping("/additionalCharges")
	public ResponseEntity<?> create(@RequestBody AdditionalCharges bean) {
		if (bean != null) {
				AdditionalCharges product = additionalChargesService.create(bean);
				if (product != null) {
					String uri = serverUri+"/hotel/additionalCharges" + product.getId();
    				return ResponseEntity.created(URI.create(uri))
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(product);
				}
			}
		
		return ResponseEntity.noContent().build();
	}

	// update
	@PutMapping("/additionalCharges/{mid}/{hid}")
	public ResponseEntity<?> modify(
			@PathVariable(name = "mid") Integer minibarId,
			@PathVariable(name = "hid") Integer housingManagementId, 
			@RequestBody AdditionalCharges entity) {
		if (entity != null && entity.getId() != null) {
			boolean exists = additionalChargesService.existById(entity.getId());
			if (exists) {
				AdditionalCharges addition = additionalChargesService.update(entity);
				if (addition != null) {
					return ResponseEntity.ok(addition);
				}
			}
		}
		return ResponseEntity.notFound().build();
	}

}
