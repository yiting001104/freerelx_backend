package tw.team.project.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class OrderRoomDetailId implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Column(name = "order_id", nullable = false)
	private Integer orderId;
	
	@Column(name = "room_Information_Id", nullable = false)
	private Integer roomInformationId;
	
	public OrderRoomDetailId() {
		
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getRoomInformationId() {
		return roomInformationId;
	}

	public void setRoomInformationId(Integer roomInformationId) {
		this.roomInformationId = roomInformationId;
	}

	public OrderRoomDetailId(Integer orderId, Integer roomInformationId) {
		super();
		this.orderId = orderId;
		this.roomInformationId = roomInformationId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(orderId, roomInformationId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderRoomDetailId other = (OrderRoomDetailId) obj;
		return Objects.equals(orderId, other.orderId) && Objects.equals(roomInformationId, other.roomInformationId);
	}

	
	
}
