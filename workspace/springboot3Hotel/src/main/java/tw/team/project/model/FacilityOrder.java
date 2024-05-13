package tw.team.project.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "FacilityOrder")
public class FacilityOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FACILITY_ORDER_ID")
	private Integer facilityOrderId;

	@Column(name = "MEMBER_ID")
	private Integer memberId;

	@Column(name = "PHONE_NUMBER")
	private String phoneNumber;

	@Column(name = "FACILITY_ID")
	private String facilityId;

	@Column(name = "FACILITY_STATUS")
	private String facilityStatus;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "FACILITY_BOOKING_DATE")
	@Temporal(TemporalType.DATE)
	private Date facilityBookingDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "START_TIME")
	private Date startTime;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "END_TIME")
	private Date endTime;

	public Integer getFacilityOrderId() {
		return facilityOrderId;
	}

	public void setFacilityOrderId(Integer facilityOrderId) {
		this.facilityOrderId = facilityOrderId;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getFacilityId() {
		return facilityId;
	}

	public void setFacilityId(String facilityId) {
		this.facilityId = facilityId;
	}

	public String getFacilityStatus() {
		return facilityStatus;
	}

	public void setFacilityStatus(String facilityStatus) {
		this.facilityStatus = facilityStatus;
	}

	public Date getFacilityBookingDate() {
		return facilityBookingDate;
	}

	public void setFacilityBookingDate(Date facilityBookingDate) {
		this.facilityBookingDate = facilityBookingDate;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

}
