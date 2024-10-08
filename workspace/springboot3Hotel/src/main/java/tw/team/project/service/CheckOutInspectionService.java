package tw.team.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import tw.team.project.model.CheckOutInspection;
import tw.team.project.model.HousingManagement;
import tw.team.project.repository.CheckOutInspectionRepository;
import tw.team.project.repository.HousingManagementRepository;

@Service
public class CheckOutInspectionService {

	@Autowired
	private CheckOutInspectionRepository checkOutInspectionRepo;

	@Autowired
	private HousingManagementRepository housingManagementRepo;

	public boolean delete(Integer id) {
		if (id != null) {
			Optional<CheckOutInspection> optional = checkOutInspectionRepo.findById(id);
			if (optional.isPresent()) {
				checkOutInspectionRepo.deleteById(id);
				return true;
			}
		}
		return false;
	}
	
	 public List<CheckOutInspection> findAll() {
	        return checkOutInspectionRepo.findAll(Sort.by(Sort.Direction.DESC, "id"));
	    }
	
//    public Page<CheckOutInspection> findAll(Integer pageNumber){
//        Pageable pgb = PageRequest.of(pageNumber-1, 10, Sort.Direction.DESC, "id");
//        Page<CheckOutInspection> page = checkOutInspectionRepo.findAll(pgb);
//        return page;
//
//    }
	

	public CheckOutInspection findById(Integer id) {
		Optional<CheckOutInspection> option = checkOutInspectionRepo.findById(id);
		if (option.isPresent())
			return option.get();
		else
			return null;
	}


	public boolean existById(Integer id) {
		if (id != null) {
			return checkOutInspectionRepo.existsById(id);
		}
		return false;
	}

	public CheckOutInspection update(CheckOutInspection bean) {
		if (bean != null && bean.getId() != null) {
			Optional<CheckOutInspection> optional = checkOutInspectionRepo.findById(bean.getId());
			if (optional.isPresent()) {

				CheckOutInspection check = optional.get();

				check.setCompensation(bean.getCompensation());
				check.setFee(bean.getFee());
				check.setPhoto(bean.getPhoto());

				return checkOutInspectionRepo.save(check);
			}
		}
		return null;
	}
	
	public CheckOutInspection insert(CheckOutInspection bean) {
	    if (bean != null) {
	        if (bean.getFee() != null && bean.getHousingManagement() != null && bean.getHousingManagement().getId() != null) {
	            Optional<HousingManagement> housingOptional = housingManagementRepo.findById(bean.getHousingManagement().getId());
	            if(housingOptional.isPresent()) {
	                HousingManagement housing = housingOptional.get();
	                housing.setTotalCompensation(housing.getTotalCompensation().add(bean.getFee()));
	                housingManagementRepo.save(housing);
	            }
	        }	        
	        return checkOutInspectionRepo.save(bean);
	    }
	    return null;
	}

}