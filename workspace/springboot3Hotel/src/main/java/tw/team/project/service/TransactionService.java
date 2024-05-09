package tw.team.project.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import tw.team.project.dto.TransactionDTO;
import tw.team.project.model.CreditCardDiscount;
import tw.team.project.model.CreditCardDiscountRepository;
import tw.team.project.model.RefundType;
import tw.team.project.model.RefundTypeRepository;
import tw.team.project.model.Transaction;
import tw.team.project.model.TransactionRepository;

@Service
public class TransactionService {
    
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private CreditCardDiscountRepository creditCardDiscountRepository;
    @Autowired
    private RefundTypeRepository refundTypeRepository;

    public Page<Transaction> findAll(Integer pageNumber){
        Pageable pgb = PageRequest.of(pageNumber-1, 2, Sort.Direction.DESC,"transactionId");
        Page<Transaction> page = transactionRepository.findAll(pgb);
        return page;
    }
    // 新增訂單成立時會建立
    public Transaction insert(Transaction tran){
        return transactionRepository.save(tran);
    }

    public Transaction findById(Integer id){
        if (id!=null && id >= 1){
            Optional<Transaction> optional = transactionRepository.findById(id);
            if (optional.isPresent()){
                return optional.get();
            }
        }
        return null;
    }

    @Transactional
    public TransactionDTO update(TransactionDTO transDTO){
        CreditCardDiscount bank;
        RefundType refundType;
        if (transDTO!=null && transDTO.getTransactionId()!=null){
            Optional<Transaction> optional = transactionRepository.findById(transDTO.getTransactionId());
            if (optional.isPresent()){
                Transaction trans = optional.get();

                trans.setAmount(transDTO.getAmount());
                trans.setLastFiveAccNum(transDTO.getLastFiveAccNum());
                trans.setTransferDate(transDTO.getTransferDate());
                trans.setTaxIDNumber(transDTO.getTaxIDNumber());
                trans.setUnsubscribeDate(transDTO.getUnsubscribeDate());
                trans.setRffundAmount(transDTO.getRffundAmount());
                trans.setRemark(transDTO.getRemark());

                if (transDTO.getDiscount_id()!=null){
                    Optional<CreditCardDiscount> optional2 = creditCardDiscountRepository.findById(transDTO.getDiscount_id());
                    if (optional2.isPresent()){
                        bank = optional2.get();
                        trans.setDiscounts(bank);
                    }
                }
                if (transDTO.getRefund_id()!=null){
                    Optional<RefundType> optional3 = refundTypeRepository.findById(transDTO.getRefund_id());
                    if (optional3.isPresent()){
                        refundType = optional3.get();
                        trans.setRefundType(refundType);
                    }
                }
                return transDTO;
            }
        }
        return null;
    }
    
    public boolean existById(Integer id) {
    	if (id!=null && id!=0) {
    		return transactionRepository.existsById(id);
    	} else {
    		return false;
    	}    	
    }
    
    public boolean deleteById(Integer id) {
    	if (id!=null) {
    		if (transactionRepository.existsById(id)) {
    			transactionRepository.deleteById(id);
    			return true;
    		}
    	}
    	return false;
    }

}
