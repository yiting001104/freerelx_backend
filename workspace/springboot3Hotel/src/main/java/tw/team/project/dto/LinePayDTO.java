package tw.team.project.dto;

import java.math.BigDecimal;

public class LinePayDTO extends Object{

	private BigDecimal base_price;
	
	private Integer room_amount;
	
	private Integer order_id;
	
	private Integer room_type_id;
	
	private BigDecimal room_price;
	
	private String bed_type;

	public BigDecimal getBase_price() {
		return base_price;
	}

	public void setBase_price(BigDecimal base_price) {
		this.base_price = base_price;
	}

	public Integer getRoom_amount() {
		return room_amount;
	}

	public void setRoom_amount(Integer room_amount) {
		this.room_amount = room_amount;
	}

	public Integer getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}

	public Integer getRoom_type_id() {
		return room_type_id;
	}

	public void setRoom_type_id(Integer room_type_id) {
		this.room_type_id = room_type_id;
	}

	public BigDecimal getRoom_price() {
		return room_price;
	}

	public void setRoom_price(BigDecimal room_price) {
		this.room_price = room_price;
	}

	public String getBed_type() {
		return bed_type;
	}

	public void setBed_type(String bed_type) {
		this.bed_type = bed_type;
	}
}
