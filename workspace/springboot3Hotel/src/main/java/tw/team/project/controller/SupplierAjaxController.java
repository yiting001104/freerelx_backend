package tw.team.project.controller;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.team.project.model.Supplier;
import tw.team.project.service.SupplierService;

@RestController
@RequestMapping("hotel")
@CrossOrigin
public class SupplierAjaxController {
	@Autowired
	private SupplierService supplierService;
///////////////////////////////////////////////////////////////////////////////////////////////////////這些全部需要過濾
	@PostMapping("/suppliers")
	public String create(@RequestBody String json) throws JSONException {
		JSONObject responseJson = new JSONObject();

		Supplier supplier = supplierService.create(json);
		if (supplier == null) {
			responseJson.put("success", false);
			responseJson.put("message", "新增失敗");
		} else {
			responseJson.put("success", true);
			responseJson.put("message", "新增成功");
		}

		return responseJson.toString();
	}

	@DeleteMapping("/suppliers/{pk}")
	public String remove(@PathVariable(name = "pk") Integer id) throws JSONException {
		JSONObject responseJson = new JSONObject();
		if (id == null) {
			responseJson.put("success", false);
			responseJson.put("message", "id是必要欄位");
		} else if (!supplierService.existById(id)) {
			responseJson.put("success", false);
			responseJson.put("message", "id不存在");
		} else {
			if (supplierService.delete(id)) {
				responseJson.put("success", true);
				responseJson.put("message", "刪除成功");
			} else {
				responseJson.put("success", false);
				responseJson.put("message", "刪除失敗");
			}
		}
		return responseJson.toString();
	}

	@PutMapping("/suppliers/{pk}")
	public String modify(@PathVariable(name = "pk") Integer id, @RequestBody String json) throws JSONException {
		JSONObject responseJson = new JSONObject();
		if (id == null) {
			responseJson.put("success", false);
			responseJson.put("message", "id是必要欄位");
		} else if (!supplierService.existById(id)) {
			responseJson.put("success", false);
			responseJson.put("message", "id不存在");
		} else {
			Supplier supplier = supplierService.modify(json);
			if (supplier == null) {
				responseJson.put("success", false);
				responseJson.put("message", "修改失敗");
			} else {
				responseJson.put("success", true);
				responseJson.put("message", "修改成功");
			}
		}
		return responseJson.toString();
	}

	@GetMapping("/suppliers/{pk}")
	public String findById(@PathVariable(name = "pk") Integer id) throws JSONException {
		JSONObject responseJson = new JSONObject();
		JSONArray array = new JSONArray();
		Supplier supplier = supplierService.findById(id);
		if (supplier != null) {
			JSONObject item = new JSONObject().put("id", supplier.getId()).put("name", supplier.getName())
					.put("cname", supplier.getCname()).put("phone", supplier.getPhone())
					.put("email", supplier.getEmail()).put("address", supplier.getAddress());
			array.put(item);

		}
		responseJson.put("list", array);
		return responseJson.toString();
	}

	@PostMapping("/suppliers/findall")
	public String find() throws JSONException {
		JSONObject responseJson = new JSONObject();

		JSONArray array = new JSONArray();
		List<Supplier> suppliers = supplierService.findall();
		if (suppliers != null && !suppliers.isEmpty()) {
			for (Supplier supplier : suppliers) {
				JSONObject item = new JSONObject().put("id", supplier.getId()).put("name", supplier.getName())
						.put("cname", supplier.getCname()).put("phone", supplier.getPhone())
						.put("email", supplier.getEmail()).put("address", supplier.getAddress());
				array.put(item);
			}
		}
		responseJson.put("list", array);
		return responseJson.toString();
	}
}
