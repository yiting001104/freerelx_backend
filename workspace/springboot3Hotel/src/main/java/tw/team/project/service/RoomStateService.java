package tw.team.project.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.team.project.model.RoomState;
import tw.team.project.repository.RoomStateRepository;

@Service
public class RoomStateService {
	
	@Autowired
	private RoomStateRepository roomStateRepo;
	
	public RoomState findById(Integer id) {
		if (id != null) {
			Optional<RoomState> optional = roomStateRepo.findById(id);
			if (optional.isPresent()) {
				return optional.get();
			}
		}
		return null;
	}
	

}
