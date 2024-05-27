package tw.team.project.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.DynamicInsert;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


@Entity
@Table(name="member")
@DynamicInsert // 動態生成SQL語法
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id")
	private Integer memberId;
	
	@Column(name="name", nullable = false)
	private String memberName;
	
	@Column(name = "gender", nullable = false)
	private String gender;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date birth;
	
	@Column(name = "national_id", nullable = false)
	private String nationId;
	
	
	@Column(name = "email", nullable = false)
	private String email;
	
	@Column(name = "phone_number",nullable = false, unique = true)
	private String phoneNumber;
	
	@Column(name = "credit_card",nullable=true)
	private String creditCard;
	
	@Column(name = "contact_address", nullable=false)
	private String contactAddress;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss EEEE")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "registration_date", nullable = false)
	private Date registrationDate;
	
	@Column(name = "member_status")
	private String memberStatus;
	
	@Column(name = "nationality", nullable = false)
	private String nationality;
	
	@Lob
	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	@Column(name = "total_bonus_points")
	private BigDecimal bonusPoint;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss EEEE")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "login_time")
	private Date loginTime;
	
	@Column(name = "login_status")
	private String loginStatus;
	
	@Column(name = "picture")
	private byte[] picture;
	public List<OrderRoom> getOrdersRoom() {
		return ordersRoom;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public void setOrdersRoom(List<OrderRoom> ordersRoom) {
		this.ordersRoom = ordersRoom;
	}
	@JsonIgnore
	@OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<OrderRoom> ordersRoom;
	
	@JsonIgnore
	@OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
	private List<Comment> comments;
	
	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}

	public String getContactAddress() {
		return contactAddress;
	}

	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getMemberStatus() {
		return memberStatus;
	}

	public void setMemberStatus(String memberStatus) {
		this.memberStatus = memberStatus;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public BigDecimal getBonusPoint() {
		return bonusPoint;
	}

	public void setBonusPoint(BigDecimal bonusPoint) {
		this.bonusPoint = bonusPoint;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public String getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}
	
	@JsonIgnore
	@OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
	private List<Order> order = new ArrayList<>();

	public List<Order> getOrder() {
		return order;
	}

	public List<Alert> getAlert() {
		return alert;
	}

	public void setAlert(List<Alert> alert) {
		this.alert = alert;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "memberid", cascade = CascadeType.ALL)
	private List<Alert> alert = new ArrayList<>();
	
	public void setOrder(List<Order> order) {
		this.order = order;
	}
}