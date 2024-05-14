package tw.team.project.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "orderRoomDetail")
public class OrderRoomDetail {

	
	@EmbeddedId
	private OrderRoomDetailId orderRoomDetailId;
	
	@Column(name = "room_amount")
	private Integer roomAmount;
	
	private BigDecimal price;
	
	public OrderRoomDetailId getOrderRoomDetailId() {
		return orderRoomDetailId;
	}

	public void setOrderRoomDetailId(OrderRoomDetailId orderRoomDetailId) {
		this.orderRoomDetailId = orderRoomDetailId;
	}

	public Integer getRoomAmount() {
		return roomAmount;
	}

	public void setRoomAmount(Integer roomAmount) {
		this.roomAmount = roomAmount;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public OrderRoom getOrderRoom() {
		return orderRoom;
	}

	public void setOrderRoom(OrderRoom orderRoom) {
		this.orderRoom = orderRoom;
	}

	public RoomInformation getRoInformation() {
		return roInformation;
	}

	public void setRoInformation(RoomInformation roInformation) {
		this.roInformation = roInformation;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="order_id", referencedColumnName = "order_id")
	@MapsId("orderId")
	private OrderRoom orderRoom;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="room_Information_Id",referencedColumnName = "room_information_id")
	@MapsId("roomInformationId")
	private RoomInformation roInformation;
}
