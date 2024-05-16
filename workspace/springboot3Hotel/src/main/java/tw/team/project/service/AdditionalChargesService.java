package tw.team.project.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import tw.team.project.model.AdditionalCharges;
import tw.team.project.model.AdditionalChargesId;
import tw.team.project.model.HousingManagement;
import tw.team.project.repository.AdditionalChargesRepository;
import tw.team.project.repository.HousingManagementRepository;

@Service
public class AdditionalChargesService {

	@Autowired
	private AdditionalChargesRepository additionalChargesRepo;
	
	@Autowired
	private HousingManagementRepository housingManagementRepo;

	
//	public List<AdditionalCharges> findAll(){
//		return additionalChargesRepo.findAll();
//	}
    public Page<AdditionalCharges> findAll(Integer pageNumber){
        Pageable pgb = PageRequest.of(pageNumber-1, 10, Sort.Direction.ASC, "id");
        Page<AdditionalCharges> page = additionalChargesRepo.findAll(pgb);
        return page;

    }
	

	    public boolean existById(AdditionalChargesId additionalChargesId) {
	        if (additionalChargesId != null) {
	            return additionalChargesRepo.existsById(additionalChargesId);
	        }
	        return false;
	    }
	    
//	    public AdditionalCharges findById(AdditionalChargesId additionalChargesId) {
//	        return additionalChargesRepo.findById(additionalChargesId);
//	    }
	    
//	    public Optional<AdditionalCharges> findById(Integer minibarId, Integer housingManagementId) {
//	        return additionalChargesRepo.findById(new AdditionalChargesId(minibarId, housingManagementId));
//	    }
	    
	    
	    public AdditionalCharges findById(Integer minibarId, Integer housingManagementId) {
	        return additionalChargesRepo.findById(new AdditionalChargesId(minibarId, housingManagementId)).orElse(null);
	    }
	    
	    
	    public AdditionalCharges create(AdditionalCharges bean) {
	    	if(bean != null) {
	    		Optional<AdditionalCharges> optional = additionalChargesRepo.findById(bean.getId());
				if(optional.isEmpty()) {
	            if (bean.getAmount() != null) {
	                Optional<HousingManagement> housingOptional = housingManagementRepo.findById(bean.getId().getHousingManagementId());
	                if(housingOptional.isPresent()) {
	                	HousingManagement housing = housingOptional.get();
	                    housing.setTotalAdditional(housing.getTotalAdditional().add(bean.getAmount()));
	                    housingManagementRepo.save(housing);
	                }
	            }
	    			return additionalChargesRepo.save(bean);
	    		}	
	    	}
	    	return null;    	
	    }
	    
	    //不做
		public AdditionalCharges update(AdditionalCharges bean) {
			if(bean!=null) {
				Optional<AdditionalCharges> optional = additionalChargesRepo.findById(bean.getId());
				if(optional.isPresent()) {
					
//					AdditionalCharges add =  optional.get();
//					add.setAmount(add.getAmount().add(bean.getAmount()));
//					add.setQuantity(add.getQuantity()+(bean.getQuantity()));
//					
		            if (bean.getAmount() != null) {
		                Optional<HousingManagement> housingOptional = housingManagementRepo.findById(bean.getId().getHousingManagementId());
		                if(housingOptional.isPresent()) {
		                	HousingManagement housing = housingOptional.get();
		                	housing.setTotalAdditional(housing.getTotalAdditional().add(bean.getAmount()));
		                	housingManagementRepo.save(housing);
		                }
		            }
					return additionalChargesRepo.save(bean);
				}
			}
			return null;
		}
	    

    


	}

