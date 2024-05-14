package tw.team.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

import tw.team.project.model.RoomType;
import tw.team.project.repository.RoomTypeRepository;


@Service
public class RoomTypeService {
	
	@Autowired
	private RoomTypeRepository roomTypeRepo;
	
	public RoomType saveType(RoomType json) {
		return roomTypeRepo.save(json);
	}
	
	public RoomType create(String json) {
	    try {
	        JSONObject obj = new JSONObject(json);
	        Integer id = obj.isNull("id") ? null : obj.getInt("id");
	        String chinese = obj.isNull("chinese") ? null : obj.getString("chinese");
	        String english = obj.isNull("english") ? null : obj.getString("english");
	        String japanese = obj.isNull("japanese") ? null : obj.getString("japanese");

	        if (id != null) {
	            Optional<RoomType> optional = roomTypeRepo.findById(id);
	            if (optional.isEmpty()) {
	                RoomType insert = new RoomType();
	                insert.setId(id);
	                insert.setChinese(chinese);
	                insert.setEnglish(english);
	                insert.setJapanese(japanese);

	                return roomTypeRepo.save(insert);
	            }
	        }
	    } catch (JSONException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	

	public RoomType findById(Integer id) {
		Optional<RoomType> optional = roomTypeRepo.findById(id);

		if (optional.isPresent()) {
			return optional.get();
		}return null;
	}
	
	public boolean existById(Integer id) {
		if(id!=null) {
			return roomTypeRepo.existsById(id);
		}
		return false;
	}

	public void deleteById(Integer id) {
		roomTypeRepo.deleteById(id);
	}
	
	public RoomType updateType(RoomType type) {
		return roomTypeRepo.save(type);
		
	}
	
	public List<RoomType> findAllType(){
		return roomTypeRepo.findAll();
	}
	
	

}
