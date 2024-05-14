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

import tw.team.project.model.AdditionalCharges;
import tw.team.project.service.AdditionalChargesService;

@RestController
@RequestMapping("/hotel")
@CrossOrigin
public class AdditionalChargesController {

	@Autowired
	private AdditionalChargesService additionalChargesService;

	//更新OR新增 時更改 housingMangement totalfee
	//minibar 處確認下單後座 新增
	
	
	// findAll
	@PostMapping("/additionalCharges/findall")
	public String findAll() throws JSONException {
		List<AdditionalCharges> checks = additionalChargesService.findAll();
		JSONArray array = new JSONArray();

		if (checks != null && !checks.isEmpty())
			for (AdditionalCharges add : checks) {
				JSONObject item = new JSONObject().put("additionalChargesId", add.getAdditionalChargesId())
						.put("quantity", add.getQuantity()).put("amout", add.getAmount())
						.put("minibar", add.getMinibar()).put("housingManagement", add.getHousingManagement());

				array.put(item);
			}

		return array.toString();
	}

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
		if (bean != null && bean.getAdditionalChargesId() != null) {
			boolean exists = additionalChargesService.existById(bean.getAdditionalChargesId());
			if (!exists) {
				AdditionalCharges product = additionalChargesService.create(bean);
				if (product != null) {
					String uri = "http://localhost:8080/hotel/additionalCharges/" + product.getAdditionalChargesId();
					return ResponseEntity.created(URI.create(uri)).contentType(MediaType.APPLICATION_JSON)
							.body(product);
				}
			}
		}
		return ResponseEntity.noContent().build();
	}

	// update
	@PutMapping("/additionalCharges/{id}")
	public ResponseEntity<?> modify(@PathVariable Integer AdditionalChargesId, @RequestBody AdditionalCharges entity) {
		if (entity != null && entity.getAdditionalChargesId() != null) {
			boolean exists = additionalChargesService.existById(entity.getAdditionalChargesId());
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
