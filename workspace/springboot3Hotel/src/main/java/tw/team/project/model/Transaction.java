package tw.team.project.model;

import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.annotations.DynamicInsert;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = " transactionTable")
@DynamicInsert // 動態生成SQL語法
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "transactionId")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "transaction_Id")
	private Integer transactionId;
	
	@Column(name = "amount",nullable = false)
	private BigDecimal amount; //實收金額
	
	@OneToOne
	@JoinColumn(name = "order_id",referencedColumnName = "order_id")
	private OrderRoom orderRoom;
	
	@Column(name = "last_five_account_number", nullable = true)
	private String lastFiveAccNum;
	
	@DateTimeFormat(pattern ="yyyy-MM-dd HH:mm:ss EEEE")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column(name = "transfer_date")
	private Date transferDate;
	
	@ManyToOne
	@JoinColumn(name = "discount_id", referencedColumnName = "bank_id")
	private CreditCardDiscount discounts;
	
	private String taxIDNumber;
	
	@Column(name = "transaction_status")
	private String transactionStatus;
	
	@Column(name = "payment_method")
	private String paymentMethod;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss EEEE")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column(name = "unsubscribe_date")
	private Date unsubscribeDate;
	
	@Column(name = "refund_amount")
	private BigDecimal rffundAmount;
	
	@ManyToOne
	@JoinColumn(name = "refund_id", referencedColumnName = "refundType_id")
	private RefundType refundType;
	
	@Column(name = "remark")
	private String remark;

	public Integer getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public OrderRoom getOrderRoom() {
		return orderRoom;
	}

	public void setOrderRoom(OrderRoom orderRoom) {
		this.orderRoom = orderRoom;
	}

	public String getLastFiveAccNum() {
		return lastFiveAccNum;
	}

	public void setLastFiveAccNum(String lastFiveAccNum) {
		this.lastFiveAccNum = lastFiveAccNum;
	}

	public Date getTransferDate() {
		return transferDate;
	}

	public void setTransferDate(Date transferDate) {
		this.transferDate = transferDate;
	}

	public CreditCardDiscount getDiscounts() {
		return discounts;
	}

	public void setDiscounts(CreditCardDiscount discounts) {
		this.discounts = discounts;
	}

	public String getTaxIDNumber() {
		return taxIDNumber;
	}

	public void setTaxIDNumber(String taxIDNumber) {
		this.taxIDNumber = taxIDNumber;
	}

	public String getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public Date getUnsubscribeDate() {
		return unsubscribeDate;
	}

	public void setUnsubscribeDate(Date unsubscribeDate) {
		this.unsubscribeDate = unsubscribeDate;
	}

	public BigDecimal getRffundAmount() {
		return rffundAmount;
	}

	public void setRffundAmount(BigDecimal rffundAmount) {
		this.rffundAmount = rffundAmount;
	}

	public RefundType getRefundType() {
		return refundType;
	}

	public void setRefundType(RefundType refundType) {
		this.refundType = refundType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
