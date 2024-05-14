package tw.team.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.team.project.model.AdditionalCharges;
import tw.team.project.model.AdditionalChargesId;
import tw.team.project.repository.AdditionalChargesRepository;

@Service
public class AdditionalChargesService {

	@Autowired
	private AdditionalChargesRepository additionalChargesRepo;
	
	public List<AdditionalCharges> findAll(){
		return additionalChargesRepo.findAll();
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
	    	if(bean != null && bean.getAdditionalChargesId()!=null) {
	    		Optional<AdditionalCharges> optional = additionalChargesRepo.findById(bean.getAdditionalChargesId());
	    		if(optional.isEmpty()) {
	    			return additionalChargesRepo.save(bean);
	    		}
	    	}
	    	return null;    	
	    }
	    
		public AdditionalCharges update(AdditionalCharges bean) {
			if(bean!=null && bean.getAdditionalChargesId()!=null) {
				Optional<AdditionalCharges> optional = additionalChargesRepo.findById(bean.getAdditionalChargesId());
				if(optional.isPresent()) {
					return additionalChargesRepo.save(bean);
				}
			}
			return null;
		}
	    

    


	}

