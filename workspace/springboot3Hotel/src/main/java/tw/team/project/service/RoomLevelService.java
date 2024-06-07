package tw.team.project.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import tw.team.project.model.RoomLevel;
import tw.team.project.repository.RoomLevelRepository;

@Service
public class RoomLevelService {
	
	@Autowired
	private RoomLevelRepository roomLevelRepo;
	
	public RoomLevel create(String json) {
	    try {
	        JSONObject obj = new JSONObject(json);
	        Integer id = obj.isNull("id") ? null : obj.getInt("id");
	        String chinese = obj.isNull("chinese") ? null : obj.getString("chinese");
	        String english = obj.isNull("english") ? null : obj.getString("english");
	        String japanese = obj.isNull("japanese") ? null : obj.getString("japanese");

	        if (id != null) {
	            Optional<RoomLevel> optional = roomLevelRepo.findById(id);
	            if (optional.isEmpty()) {
	            	RoomLevel insert = new RoomLevel();
	                insert.setId(id);
	                insert.setChinese(chinese);
	                insert.setEnglish(english);
	                insert.setJapanese(japanese);

	                return roomLevelRepo.save(insert);
	            }
	        }
	    } catch (JSONException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
	public boolean existById(Integer id) {
		if(id!=null) {
			return roomLevelRepo.existsById(id);
		}
		return false;
	}
	

}
