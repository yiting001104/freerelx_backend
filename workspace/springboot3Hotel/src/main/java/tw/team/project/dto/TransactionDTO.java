package tw.team.project.dto;

import java.math.BigDecimal;
import java.util.Date;

public class TransactionDTO {
    

	private Integer transactionId;
	

	private BigDecimal amount; //實收金額
	

	private Integer order_id;
	

	private String lastFiveAccNum;
	

	private Date transferDate;
	

	private Integer discount_id;
	
	private String taxIDNumber;
	
	
	private String transactionStatus;
	

	private String paymentMethod;
	

	private Date unsubscribeDate;
	

	private BigDecimal rffundAmount;
    private Integer refund_id;
  
	private String remark;
	
    public Integer getRefund_id() {
        return refund_id;
    }


    public void setRefund_id(Integer refund_id) {
        this.refund_id = refund_id;
    }




	public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }


    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }


    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }


    public void setLastFiveAccNum(String lastFiveAccNum) {
        this.lastFiveAccNum = lastFiveAccNum;
    }


    public void setTransferDate(Date transferDate) {
        this.transferDate = transferDate;
    }


    public void setDiscount_id(Integer discount_id) {
        this.discount_id = discount_id;
    }


    public void setTaxIDNumber(String taxIDNumber) {
        this.taxIDNumber = taxIDNumber;
    }


    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }


    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }


    public void setUnsubscribeDate(Date unsubscribeDate) {
        this.unsubscribeDate = unsubscribeDate;
    }


    public void setRffundAmount(BigDecimal rffundAmount) {
        this.rffundAmount = rffundAmount;
    }




    public void setRemark(String remark) {
        this.remark = remark;
    }


    public Integer getTransactionId() {
        return transactionId;
    }


    public BigDecimal getAmount() {
        return amount;
    }


    public Integer getOrder_id() {
        return order_id;
    }


    public String getLastFiveAccNum() {
        return lastFiveAccNum;
    }


    public Date getTransferDate() {
        return transferDate;
    }


    public Integer getDiscount_id() {
        return discount_id;
    }


    public String getTaxIDNumber() {
        return taxIDNumber;
    }


    public String getTransactionStatus() {
        return transactionStatus;
    }


    public String getPaymentMethod() {
        return paymentMethod;
    }


    public Date getUnsubscribeDate() {
        return unsubscribeDate;
    }


    public BigDecimal getRffundAmount() {
        return rffundAmount;
    }





    public String getRemark() {
        return remark;
    }



}
