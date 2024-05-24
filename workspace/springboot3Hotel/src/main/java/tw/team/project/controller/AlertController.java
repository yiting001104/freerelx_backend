package tw.team.project.controller;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.team.project.model.Alert;
import tw.team.project.service.AlertService;

@RestController
@RequestMapping("hotel")
@CrossOrigin
public class AlertController {

	@Autowired
	private AlertService alertService;
	@PostMapping("/alerts/post") // 顧客新增購物車一筆資料的功能
	public String createalert(@RequestBody String json) throws JSONException {
		JSONObject responseJson = new JSONObject();
		boolean cart = alertService.createalert(json);
		if (cart == false) {
			responseJson.put("success", false);
			responseJson.put("message", "新增失敗");
		} else {
			responseJson.put("success", true);
			responseJson.put("message", "新增成功");
		}
		return responseJson.toString();
	}
	@PostMapping("/alerts/find") // 顧客查看自己的警告訊息
	public String find(@RequestBody String json) throws JSONException {
		JSONObject responseJson = new JSONObject();
		JSONArray array = new JSONArray();
		List<Alert> alerts = alertService.getalert(json);
		if (alerts != null && !alerts.isEmpty()) {
			for (Alert alert : alerts) {
				JSONObject item = new JSONObject()
						.put("alertmessage", alert.getAlertmessage())
						.put("memberid", alert.getMemberid().getMemberId());
				array.put(item);
				alertService.deletealert(alert.getId());
			}
		}
		responseJson.put("list", array);
		return responseJson.toString();
	}
	
}
