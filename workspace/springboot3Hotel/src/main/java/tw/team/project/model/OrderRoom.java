package tw.team.project.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.DynamicInsert;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "orderRoom")
@DynamicInsert // 動態生成SQL語法
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "orderId")
public class OrderRoom {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private Integer orderId;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss EEEE") 
	@Temporal(TemporalType.TIMESTAMP)
	private Date orderDate;
	
	@Column(name = "order_person_name", nullable = false)
	private String customerName;
	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Column(name = "gender", nullable = false)
	private String gender;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date birth;
	
	@Column(name = "national_id", nullable = false)
	private String nationId;
	
	@Column(name = "email", nullable = false)
	private String email;
	
	@Column(name = "phone_number",nullable = false, unique = true)
	private String phone;
	
	@Column(name = "credit_card", nullable = false)
	private String creditCard;
	
	@Column(name = "adult_pax", nullable = false)
	private Integer adultPax;
	
	@Column(name = "child_pax")
	private Integer childPax;
	
	@Column(name = "room_type_amount", nullable = false)
	private Integer roomAmount;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss EEEE") 
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "arrival_date")
	private Date arrivateDate;
	
	@Column(name = "reservation_status", nullable = false)
	private String reservationStatus;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss EEEE") 
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "reservation_status_date", nullable = false)
	private Date reservationSaDate;
	
	@Column(name = "transaction_password", nullable = false)
	private String transactionPassword;
	
	@OneToMany(mappedBy = "orderId", cascade = CascadeType.ALL)
	private Set<OrderRoomDetail> roomInformations = new HashSet<>();
	
	@ManyToOne
	@JoinColumn(name = "member_id",referencedColumnName = "member_id")
	private Member member;
	
	@OneToOne(mappedBy = "orderRoom",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private Transaction transaction;
	
	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	@Column(name = "cancellation_reason")
	private String cancellReason;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss EEEE") 
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "checkout_date")
	private Date checkoutDate;
	
	@Column(name = "base_price")
	private BigDecimal basePrice;
	
	@Column(name = "stay_person_name")
	private String SPName;
	
	@Column(name = "stay_person_gender")
	private String SPGender;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name = "stay_person_birth")
	private Date SPBirth;
	
	@Column(name = "stay_person_national_id")
	private String SPNationId;
	
	@Column(name = "stay_person_phone")
	private String SPPhone;
	
	@Column(name = "stay_person_Email")
	private String SPEmail;
	
	private String remark;

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getNationId() {
		return nationId;
	}

	public void setNationId(String nationId) {
		this.nationId = nationId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}

	public Integer getAdultPax() {
		return adultPax;
	}

	public void setAdultPax(Integer adultPax) {
		this.adultPax = adultPax;
	}

	public Integer getChildPax() {
		return childPax;
	}

	public void setChildPax(Integer childPax) {
		this.childPax = childPax;
	}

	public Integer getRoomAmount() {
		return roomAmount;
	}

	public void setRoomAmount(Integer roomAmount) {
		this.roomAmount = roomAmount;
	}

	public Date getArrivateDate() {
		return arrivateDate;
	}

	public void setArrivateDate(Date arrivateDate) {
		this.arrivateDate = arrivateDate;
	}

	public String getReservationStatus() {
		return reservationStatus;
	}

	public void setReservationStatus(String reservationStatus) {
		this.reservationStatus = reservationStatus;
	}

	public Date getReservationSaDate() {
		return reservationSaDate;
	}

	public void setReservationSaDate(Date reservationSaDate) {
		this.reservationSaDate = reservationSaDate;
	}

	public String getTransactionPassword() {
		return transactionPassword;
	}

	public void setTransactionPassword(String transactionPassword) {
		this.transactionPassword = transactionPassword;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public String getCancellReason() {
		return cancellReason;
	}

	public void setCancellReason(String cancellReason) {
		this.cancellReason = cancellReason;
	}

	public Date getCheckoutDate() {
		return checkoutDate;
	}

	public void setCheckoutDate(Date checkoutDate) {
		this.checkoutDate = checkoutDate;
	}

	public BigDecimal getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(BigDecimal basePrice) {
		this.basePrice = basePrice;
	}

	public String getSPName() {
		return SPName;
	}

	public void setSPName(String sPName) {
		SPName = sPName;
	}

	public String getSPGender() {
		return SPGender;
	}

	public void setSPGender(String sPGender) {
		SPGender = sPGender;
	}

	public Date getSPBirth() {
		return SPBirth;
	}

	public void setSPBirth(Date sPBirth) {
		SPBirth = sPBirth;
	}

	public String getSPNationId() {
		return SPNationId;
	}

	public void setSPNationId(String sPNationId) {
		SPNationId = sPNationId;
	}

	public String getSPPhone() {
		return SPPhone;
	}

	public void setSPPhone(String sPPhone) {
		SPPhone = sPPhone;
	}

	public String getSPEmail() {
		return SPEmail;
	}

	public void setSPEmail(String sPEmail) {
		SPEmail = sPEmail;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
