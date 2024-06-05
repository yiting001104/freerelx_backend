package tw.team.project.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import tw.team.project.dto.RoomManagementDTO;
import tw.team.project.model.RoomManagement;
import tw.team.project.repository.RoomManagementRepository;
import tw.team.project.util.JsonContainer2;

@Service
public class RoomManagementService {

	@Autowired
	private RoomManagementRepository roomManagementRepo;
	
    @Autowired
    private JsonContainer2 jsonContainer;

	public boolean existById(Integer id) {
		if (id != null) {
			return roomManagementRepo.existsById(id);
		}
		return false;
	}

    public List<RoomManagement> findAllList() {
        return roomManagementRepo.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }
    public List<RoomManagementDTO> findAllListDTO() {
        List<RoomManagement> rooms = findAllList();
        return rooms.stream().map(room -> jsonContainer.setRoomManagement(room)).collect(Collectors.toList());
    }
	
    public Page<RoomManagement> findAll(Integer id){
        Pageable pgb = PageRequest.of(id-1, 9, Sort.Direction.ASC,"id");
        Page<RoomManagement> page = roomManagementRepo.findAll(pgb);
        return page;
    }
    

	public RoomManagement modify(String json) {
		try {
			JSONObject obj = new JSONObject(json);

			Integer id = obj.isNull("id") ? null : obj.getInt("id");
			Integer number = obj.isNull("number") ? null : obj.getInt("number");
	        String repairStatus = obj.isNull("repairStatus") ? null : obj.getString("repairStatus");

			if (id != null) {
				Optional<RoomManagement> optional = roomManagementRepo.findById(id);
				if (optional.isPresent()) {
					RoomManagement update = optional.get();
					update.setId(id);
					update.setNumber(number);
					update.setRepairStatus(repairStatus);

					return roomManagementRepo.save(update);
				}
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public RoomManagement modify1(RoomManagement roomManagement) {
	    return roomManagementRepo.save(roomManagement);
	}

	public RoomManagement findById(Integer id) {
		if (id != null) {
			Optional<RoomManagement> optional = roomManagementRepo.findById(id);
			if (optional.isPresent()) {
				return optional.get();
			}
		}
		return null;
	}
//	
//	public RoomManagement findByNumber(Integer number) {
//		if(number!=null) {
//			Optional<RoomManagement> optional = roomManagementRepo.findByNumber(number);
//			if(optional.isPresent()) {
//				return optional.get();
//			}
//		}return null;
//	}
	
	public RoomManagement findByNumber(Integer number) {
		if(number!=null) {
			Optional<RoomManagement> optional = roomManagementRepo.findByNumber(number);
			if(optional.isPresent()) {
				return optional.get();
			}
		}return null;
	}
	
	

	
}