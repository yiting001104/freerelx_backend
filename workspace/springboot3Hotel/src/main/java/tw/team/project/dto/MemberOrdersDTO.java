package tw.team.project.dto;

import java.math.BigDecimal;
import java.util.Date;


public class MemberOrdersDTO {

	private Date orderDate;
	
	private Integer adultPax;
	
	private Integer childPax;
	
	
	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
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

	public BigDecimal getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(BigDecimal basePrice) {
		this.basePrice = basePrice;
	}

	private Integer roomAmount;

	private Date arrivateDate;
	
	private String reservationStatus;
	
	private Date reservationSaDate;

	private BigDecimal basePrice;
}
