package tw.team.project.service;

import java.util.Optional;

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

@Service
public class RoomManagementService {

	@Autowired
	private RoomManagementRepository roomManagementRepo;

	public boolean existById(Integer id) {
		if (id != null) {
			return roomManagementRepo.existsById(id);
		}
		return false;
	}
	
    public Page<RoomManagement> findAll(Integer id){
        Pageable pgb = PageRequest.of(id-1, 2, Sort.Direction.DESC,"id");
        Page<RoomManagement> page = roomManagementRepo.findAll(pgb);
        return page;
    }

	public RoomManagement modify(String json) {
		try {
			JSONObject obj = new JSONObject(json);

			Integer id = obj.isNull("id") ? null : obj.getInt("id");
			String number = obj.isNull("number") ? null : obj.getString("number");
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
	
	public RoomManagementDTO findByNumber(String number) {
		if(number!=null) {
			Optional<RoomManagementDTO> optional = roomManagementRepo.findByNumber(number);
			if(optional.isPresent()) {
				return optional.get();
			}
		}return null;
	}
	
	

	
}
