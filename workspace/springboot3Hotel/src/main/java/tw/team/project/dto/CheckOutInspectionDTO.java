package tw.team.project.dto;

import java.math.BigDecimal;

import tw.team.project.model.HousingManagement;

public class CheckOutInspectionDTO {
	
	private Integer id;
	
	private String compensation;
	
	private BigDecimal fee;
	
	private HousingManagement housingManagement;
	


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCompensation() {
		return compensation;
	}

	public void setCompensation(String compensation) {
		this.compensation = compensation;
	}

	public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}


	public HousingManagement getHousingManagement() {
		return housingManagement;
	}

	public void setHousingManagement(HousingManagement housingManagement) {
		this.housingManagement = housingManagement;
	}
	
	

}
