package tw.team.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.team.project.model.RoomInformation;
import tw.team.project.repository.RoomInformationRepository;

@Service
public class RoomInformationService {

	@Autowired
	private RoomInformationRepository roomInfoRepo;

	public RoomInformation saveRoom(RoomInformation room) {
		return roomInfoRepo.save(room);
	}

	public RoomInformation findRoomById(Integer id) {
		Optional<RoomInformation> optional = roomInfoRepo.findById(id);

		if (optional.isPresent()) {
			return optional.get();
		}return null;
	}

	public void deleteRoomById(Integer id) {
		roomInfoRepo.deleteById(id);
	}
	
	public RoomInformation updateRoom(RoomInformation room) {
		return roomInfoRepo.save(room);
		
	}
	
	public List<RoomInformation> findAllRoomInformation(){
		return roomInfoRepo.findAll();
	}
	
}
