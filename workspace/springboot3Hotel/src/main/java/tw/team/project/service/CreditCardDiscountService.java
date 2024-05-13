package tw.team.project.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import tw.team.project.model.CreditCardDiscount;
import tw.team.project.model.CreditCardDiscountRepository;

@Service
public class CreditCardDiscountService {
    
    @Autowired
    private CreditCardDiscountRepository creditCardDiscountRepository;

    public Page<CreditCardDiscount> findAll(Integer pageNumber){
        Pageable pgb = PageRequest.of(pageNumber-1,5,Sort.Direction.DESC,"bankId");
        Page<CreditCardDiscount> page = creditCardDiscountRepository.findAll(pgb);
        return page;
    }

    public CreditCardDiscount findById(Integer id){
        Optional<CreditCardDiscount> optional = creditCardDiscountRepository.findById(id);
        if (optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    public boolean existById(Integer id){
        if (id!=null && id>=1){
            return creditCardDiscountRepository.existsById(id);
        }
        return false;
    }

    public boolean existByName(String bankName){
        Long count = creditCardDiscountRepository.existsByName(bankName);
        if (count != 0){
            return true;
        }
        return false;
    }
    public CreditCardDiscount insert(CreditCardDiscount bank){
        if (bank != null){
                return creditCardDiscountRepository.save(bank);
        }
        return null;
    }

    @Transactional
    public CreditCardDiscount update(CreditCardDiscount bank){
        if (bank!=null){
            Optional<CreditCardDiscount> optional = creditCardDiscountRepository.findById(bank.getBankId());
            if(optional.isPresent()){
                CreditCardDiscount origin = optional.get();
                origin.setBankName(bank.getBankName());
                origin.setDiscount(bank.getDiscount());
                origin.setTransactions(bank.getTransactions());
                return origin;
            }
        }
        return null;
    }

    public boolean deleteById(Integer id){
        if (id!=null){
            if (creditCardDiscountRepository.existsById(id)){
                creditCardDiscountRepository.deleteById(id);
                return true;
            }
        }
        return false;
        
    }
}
