package tw.team.project.dto;

import java.util.Date;

import tw.team.project.model.RoomInformation;

public class RoomAssignmentDTO {
	
	private Integer id;
	
	private Integer left;
	
	private Date date;
	
//	private OderRoomId orderRoomId;
	
	private RoomInformation roomInformation;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLeft() {
		return left;
	}

	public void setLeft(Integer left) {
		this.left = left;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public RoomInformation getRoomInformation() {
		return roomInformation;
	}

	public void setRoomInformation(RoomInformation roomInformation) {
		this.roomInformation = roomInformation;
	}
	
	

}
