package tw.team.project.dto;

import java.math.BigDecimal;

import tw.team.project.model.AdditionalChargesId;
import tw.team.project.model.HousingManagement;
import tw.team.project.model.Minibar;


public class AdditionalChargesDTO {

	private AdditionalChargesId additionalChargesId;
	
	private Integer quantity;
	
	private BigDecimal amount;
	
	private HousingManagement housingManagement;
	
	private Minibar minibar;
	
	

	public Minibar getMinibar() {
		return minibar;
	}

	public void setMinibar(Minibar minibar) {
		this.minibar = minibar;
	}

	public AdditionalChargesId getAdditionalChargesId() {
		return additionalChargesId;
	}

	public void setAdditionalChargesId(AdditionalChargesId additionalChargesId) {
		this.additionalChargesId = additionalChargesId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public HousingManagement getHousingManagement() {
		return housingManagement;
	}

	public void setHousingManagement(HousingManagement housingManagement) {
		this.housingManagement = housingManagement;
	}
	
	
}
