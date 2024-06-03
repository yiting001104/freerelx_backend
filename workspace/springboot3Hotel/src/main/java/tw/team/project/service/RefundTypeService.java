package tw.team.project.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import tw.team.project.model.RefundType;
import tw.team.project.model.RefundTypeRepository;

@Service
public class RefundTypeService {

    @Autowired
    private RefundTypeRepository refundTypeRepository;

    public Page<RefundType> findAll(Integer pageNumber){
        Pageable pgb = PageRequest.of(pageNumber-1, 10, Sort.Direction.ASC, "refundTypeId");
        Page<RefundType> page = refundTypeRepository.findAll(pgb);
        return page;

    }
    public RefundType findById(Integer id) {
    	Optional<RefundType> optional = refundTypeRepository.findById(id);
    	if (optional.isPresent()) {
    		return optional.get();
    	}
    	return null;
    }
    
    public boolean existByName(String refundType){
        if (refundType!=null && refundType.length()!=0){
            Long count = refundTypeRepository.exitsName(refundType);
            if (count!=0){
                return true;
            }
        }
        return false;
    }

    public RefundType insert(RefundType refundType){
        if (refundType!=null){
            return refundTypeRepository.save(refundType);
        }
        return null;
    }

    @Transactional
    public RefundType update(RefundType refundtype){
        if (refundtype!=null){
            Optional<RefundType> optional = refundTypeRepository.findById(refundtype.getRefundTypeId());
            if (optional.isPresent()){
                RefundType origin = optional.get();
                origin.setType(refundtype.getType());
                origin.setRefundRatio(refundtype.getRefundRatio());
                origin.setTranscations(refundtype.getTranscations());
                return origin;
            }
        }
        return null;
    }
    
    public boolean deleteById(Integer id){
        if (id!=null){
            refundTypeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
