package tw.team.project.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import tw.team.project.model.Income;
import tw.team.project.model.IncomeRepository;

@Service
public class IncomeService {

	@Autowired
	private IncomeRepository incomeRepository;
	
	 public Page<Income> findAll(Integer pageNumber){
	        Pageable pgb = PageRequest.of(pageNumber-1,10,Sort.Direction.ASC,"incomeId");
	        Page<Income> page = incomeRepository.findAll(pgb);
	        return page;
	    }

	    public Income findById(Integer id){
	        Optional<Income> optional = incomeRepository.findById(id);
	        if (optional.isPresent()){
	            return optional.get();
	        }
	        return null;
	    }

	    public boolean existById(Integer id){
	        if (id!=null && id>=1){
	            return incomeRepository.existsById(id);
	        }
	        return false;
	    }
	    
	    public Income insert(Income income){
	        if (income != null){
	                return incomeRepository.save(income);
	        }
	        return null;
	    }
	    
	    public boolean deleteById(Integer id){
	        if (id!=null){
	            if (incomeRepository.existsById(id)){
	                incomeRepository.deleteById(id);
	                return true;
	            }
	        }
	        return false;
	        
	    }
	    
	    public List<Income> findByCreateDate(Date begainDate, Date endDate){
	    	return incomeRepository.findByCreateDateBetween(begainDate, endDate);
	    }
	
}
