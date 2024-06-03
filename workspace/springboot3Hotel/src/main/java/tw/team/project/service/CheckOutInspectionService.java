package tw.team.project.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

//	public List<CheckOutInspection> findAll() {
//		return CheckOutInspectionRepo.findAll();
//	}
	
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
	
    public Page<CheckOutInspection> findAll(Integer pageNumber){
        Pageable pgb = PageRequest.of(pageNumber-1, 10, Sort.Direction.ASC, "id");
        Page<CheckOutInspection> page = checkOutInspectionRepo.findAll(pgb);
        return page;

    }
	

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

//				if (bean.getFee() != null) {
//					Optional<HousingManagement> housingOptional = housingManagementRepo
//							.findById(bean.getHousingManagement().getId());
//					if (housingOptional.isPresent()) {
//						HousingManagement housing = housingOptional.get();
//						housing.setTotalCompensation(housing.getTotalCompensation().add(bean.getFee()));
//						housingManagementRepo.save(housing);
//					}
//				}

				return checkOutInspectionRepo.save(check);
			}
		}
		return null;
	}

//	public CheckOutInspection insert(CheckOutInspection bean) {
//		if (bean != null) {
//			Optional<CheckOutInspection> optional = checkOutInspectionRepo.findById(bean.getId());
//			if (optional.isPresent()) {
//				CheckOutInspection check = optional.get();
//
//				check.setCompensation(bean.getCompensation());
//				check.setFee(bean.getFee());
//				check.setPhoto(bean.getPhoto());
//			
//            if (bean.getFee() != null) {
//                Optional<HousingManagement> housingOptional = housingManagementRepo.findById(bean.getId());
//                if(housingOptional.isPresent()) {
//                	HousingManagement housing = housingOptional.get();
//                    housing.setTotalCompensation(housing.getTotalCompensation().add(bean.getFee()));
//                    housingManagementRepo.save(housing);
//                }
//            }
//			
//				return checkOutInspectionRepo.save(bean);
//			}
//		}
//		return null;
//	}
	
	public CheckOutInspection insert(CheckOutInspection bean) {
	    if (bean != null) {
//	        CheckOutInspection newCheckOutInspection = new CheckOutInspection();
//	        newCheckOutInspection.setCompensation(bean.getCompensation());
//	        newCheckOutInspection.setFee(bean.getFee());
//	        newCheckOutInspection.setPhoto(bean.getPhoto());
//	        newCheckOutInspection.setHousingManagement(bean.getHousingManagement());
//	        
//	        CheckOutInspection savedCheckOutInspection = checkOutInspectionRepo.save(newCheckOutInspection);
	        
	        //  HousingManagement 
	        if (bean.getFee() != null) {
	            Optional<HousingManagement> housingOptional = housingManagementRepo.findById(bean.getId());
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