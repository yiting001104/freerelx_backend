package tw.team.project.service;

import java.util.Optional;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import tw.team.project.dto.TransactionDTO;
import tw.team.project.linepay.ConsumerCheck;
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
                if (transDTO.getAmount()!=null) {
                	trans.setAmount(transDTO.getAmount());
                	System.out.println("transDTO.getAmount() " + transDTO.getAmount());
                }else {
                	transDTO.setAmount(trans.getAmount());
                }
                
                if (transDTO.getLastFiveAccNum()!=null) {
                	trans.setLastFiveAccNum(transDTO.getLastFiveAccNum());
                }else {
                	transDTO.setLastFiveAccNum(trans.getLastFiveAccNum());
                }
                
                if (transDTO.getTransferDate()!=null) {
                	trans.setTransferDate(transDTO.getTransferDate());
                }else {
                	transDTO.setTransferDate(trans.getTransferDate());
                }
                
                if (transDTO.getTaxIDNumber()!=null) {
                	trans.setTaxIDNumber(transDTO.getTaxIDNumber());
                }else {
                	transDTO.setTaxIDNumber(trans.getTaxIDNumber());
                }
                
                if (transDTO.getUnsubscribeDate()!=null) {
                	trans.setUnsubscribeDate(transDTO.getUnsubscribeDate());
                }else {
                	transDTO.setUnsubscribeDate(trans.getUnsubscribeDate());
                }
                
                if (transDTO.getRffundAmount()!=null) {
                	trans.setRffundAmount(transDTO.getRffundAmount());
                }else {
                	transDTO.setRffundAmount(trans.getRffundAmount());
                }
                
                if (transDTO.getRemark()!=null) {
                	trans.setRemark(transDTO.getRemark());
                }else {
                	transDTO.setRemark(trans.getRemark());
                }
                
                if (transDTO.getPaymentMethod()!=null) {
                	trans.setPaymentMethod(transDTO.getPaymentMethod());
                }else {
                	transDTO.setPaymentMethod(trans.getPaymentMethod());
                }
                
                if (transDTO.getTransactionStatus()!=null) {
                	trans.setTransactionStatus(transDTO.getTransactionStatus());
                }else {
                	transDTO.setTransactionStatus(trans.getTransactionStatus());
                }

                if (transDTO.getDiscount_id()!=null){
                    Optional<CreditCardDiscount> optional2 = creditCardDiscountRepository.findById(transDTO.getDiscount_id());
                    if (optional2.isPresent()){
                        bank = optional2.get();
                        trans.setDiscounts(bank);
                    }
//                    return null;
                }
                if (transDTO.getRefund_id()!=null){
                    Optional<RefundType> optional3 = refundTypeRepository.findById(transDTO.getRefund_id());
                    if (optional3.isPresent()){
                        refundType = optional3.get();
                        trans.setRefundType(refundType);
                    }
//                    return null;
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
    
    public Transaction finByOrderId(Integer orderId) {
    	Transaction trans = transactionRepository.findByOrderId(orderId);
    	if (trans!=null) {
    		return trans;
    	}
    	return null;
    }
    
     // 送請求給line
    public String callLinePay(String json) throws org.json.JSONException{
    	JSONObject obj = new JSONObject(json);
    	String encry = new ConsumerCheck().linePay(obj);
    	System.out.println(encry);
    	return encry;
    	
    }
    
    // 回應line付款完成
    public String confirmLinePay(String json) throws org.json.JSONException{
    	JSONObject obj = new JSONObject(json);
    	String encry = new ConsumerCheck().confirmLine(obj);
    	System.out.println(encry);
    	return encry;
    	
    }
    
    // 送請求給line
   public String callLinePayV2(String json) throws org.json.JSONException{
   	JSONObject inputJson = new JSONObject(json);
   	JSONObject responseJson = new JSONObject();
	Integer orderId = inputJson.isNull("orderId") ? null : inputJson.getInt("orderId");                            // v2 orderId
	Integer totalPrice = inputJson.isNull("totalPrice") ? null : inputJson.getInt("totalPrice");				   // v2 amount
	String productName = inputJson.isNull("productName") ? null : inputJson.getString("productName");			   // v2 productName
	String productPicture = inputJson.isNull("productPicture") ? null : inputJson.getString("productPicture");	   // v2 productPicture
	
	String successUri = inputJson.isNull("successUri") ? null : inputJson.getString("successUri");  

	if (orderId!=null && totalPrice!=null && productName!=null && successUri!=null) {
		responseJson.put("productName", productName);
		responseJson.put("amount", totalPrice);
		responseJson.put("currency", "TWD");
		responseJson.put("confirmUrl", successUri);
		responseJson.put("orderId", orderId);
		responseJson.put("payType", "NORMAL");
		return responseJson.toString();
		
	}else {
		return null;
	}
   	
   	
   }
   
   // 回應line付款完成
   public String confirmLinePayV2(String json) throws org.json.JSONException{
   	JSONObject obj = new JSONObject(json);
   	String encry = new ConsumerCheck().confirmLine(obj);
   	System.out.println(encry);
   	return encry;
   	
   }

}