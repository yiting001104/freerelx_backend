package tw.team.project.dto;

import java.math.BigDecimal;
import java.util.Date;

import tw.team.project.model.OrderRoom;
import tw.team.project.model.RoomManagement;

public class HousingManagementDTO {

	private Integer id; 
	
	private String remarks;
	
	private Date checkInTime;
	
	private Date checkOutTime;
	
	private BigDecimal totalAdditional;
	
	private BigDecimal totalCompensation;
	
	private OrderRoom orderRoom;
	
	private RoomManagement  roomManagement;
	
	private Integer number;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getCheckInTime() {
		return checkInTime;
	}

	public void setCheckInTime(Date checkInTime) {
		this.checkInTime = checkInTime;
	}

	public Date getCheckOutTime() {
		return checkOutTime;
	}

	public void setCheckOutTime(Date checkOutTime) {
		this.checkOutTime = checkOutTime;
	}

	public BigDecimal getTotalAdditional() {
		return totalAdditional;
	}

	public void setTotalAdditional(BigDecimal totalAdditional) {
		this.totalAdditional = totalAdditional;
	}

	public BigDecimal getTotalCompensation() {
		return totalCompensation;
	}

	public void setTotalCompensation(BigDecimal totalCompensation) {
		this.totalCompensation = totalCompensation;
	}

	public OrderRoom getOrderRoom() {
		return orderRoom;
	}

	public void setOrderRoom(OrderRoom orderRoom) {
		this.orderRoom = orderRoom;
	}

	public RoomManagement getRoomManagement() {
		return roomManagement;
	}

	public void setRoomManagement(RoomManagement roomManagement) {
		this.roomManagement = roomManagement;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}
	
	
}
