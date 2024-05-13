package tw.team.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.team.project.model.Facility;
import tw.team.project.repository.FacilityRepository;

@Service
public class FacilityService {

	@Autowired
	private FacilityRepository facilityRepository;

	public List<Facility> getAllFacilities() {
		return facilityRepository.findAll();
	}

	public Facility findOneFacility(Integer facility) {

		Optional<Facility> facilityOpt = facilityRepository.findById(facility);
		if (facilityOpt.isPresent()) {
			return facilityOpt.get();
		} else {
			return null;
		}
	}

	public Facility addFacility(Facility facility) {
		return facilityRepository.save(facility);
	}

	public Facility updateFacility(Facility facility) {
		return facilityRepository.save(facility);
	}

}
