package tw.team.project.dto;

import tw.team.project.model.RoomInformation;
import tw.team.project.model.RoomState;

public class RoomManagementDTO {

	private Integer id;
	
	private Integer number;
	
	private String repairStatus;

	private RoomInformation roomInformation;
	
	private RoomState roomState;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getRepairStatus() {
		return repairStatus;
	}

	public void setRepairStatus(String repairStatus) {
		this.repairStatus = repairStatus;
	}


	public RoomInformation getRoomInformation() {
		return roomInformation;
	}

	public void setRoomInformation(RoomInformation roomInformation) {
		this.roomInformation = roomInformation;
	}

	public RoomState getRoomState() {
		return roomState;
	}

	public void setRoomState(RoomState roomstate) {
		this.roomState = roomstate;
	}
	
}
