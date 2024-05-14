package tw.team.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.team.project.model.RoomLevel;
import tw.team.project.service.RoomLevelService;


@RestController
@RequestMapping("/hotel")
public class RoomLevelController {
	
	@Autowired
	private RoomLevelService roomLevelService;
	
	@PostMapping("/level")
	public String create(@RequestBody String json) {
		JSONObject responseJson = new JSONObject();
        try {
			JSONObject obj = new JSONObject(json);
			Integer id = obj.isNull("id") ? null : obj.getInt("id");
			if(id==null) {
			    responseJson.put("success", false);
			    responseJson.put("message", "id是必要欄位");
			} else if(roomLevelService.existById(id) ) {
			    responseJson.put("success", false);
			    responseJson.put("message", "id已存在");
			} else {
				RoomLevel level = roomLevelService.create(json);
			    if(level==null) {
			        responseJson.put("success", false);
			        responseJson.put("message", "新增失敗");
			    } else {
			        responseJson.put("success", true);
			        responseJson.put("message", "新增成功");
			    }
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return responseJson.toString();
    }
	
}
