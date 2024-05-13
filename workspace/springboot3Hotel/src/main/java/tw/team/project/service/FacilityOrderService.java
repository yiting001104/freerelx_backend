package tw.team.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.team.project.model.FacilityOrder;
import tw.team.project.repository.FacilityOrderRepository;

@Service
public class FacilityOrderService {

	@Autowired
	private FacilityOrderRepository facilityOrderRepository;

	public List<FacilityOrder> getAllFacilities() {
		return facilityOrderRepository.findAll();
	}

	public FacilityOrder findOneFacility(Integer facilityOrder) {

		Optional<FacilityOrder> facilityOpt = facilityOrderRepository.findById(facilityOrder);
		if (facilityOpt.isPresent()) {
			return facilityOpt.get();
		} else {
			return null;
		}
	}

	public FacilityOrder addFacility(FacilityOrder facilityOrder) {
		return facilityOrderRepository.save(facilityOrder);
	}

	public FacilityOrder updateFacility(FacilityOrder facilityOrder) {
		return facilityOrderRepository.save(facilityOrder);
	}
}
