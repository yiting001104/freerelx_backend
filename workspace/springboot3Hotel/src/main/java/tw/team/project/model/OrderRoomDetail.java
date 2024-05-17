package tw.team.project.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@IdClass(OrderRoomDetailId.class)
@Entity
@Table(name = "orderRoomDetail")
public class OrderRoomDetail {

	
//	@EmbeddedId
//	private OrderRoomDetailId orderRoomDetailId;

	@Id
	@Column(name = "order_id")
	private Integer orderId;
	
	@Id
	@Column(name = "room_Information_Id")
	private Integer roomInformationId;
	
	
	@Column(name = "room_amount")
	private Integer roomAmount;
	
	private BigDecimal price;
	
	public OrderRoomDetailId getId() {
		return new OrderRoomDetailId(orderId, roomInformationId);
	}
	
	public void setId(OrderRoomDetailId id) {
		this.orderId = id.getOrderId();
		this.roomInformationId = id.getRoomInformationId();
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
	
	


}
