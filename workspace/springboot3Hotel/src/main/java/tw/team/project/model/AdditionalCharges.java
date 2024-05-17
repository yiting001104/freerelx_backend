package tw.team.project.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@IdClass(AdditionalChargesId.class)
@Table(name="additionalCharges")
public class AdditionalCharges {
	
//	@EmbeddedId
//	private AdditionalChargesId additionalChargesId;

	@Id
	@Column(name="housing_management_id")
	private Integer housingManagementId;
	
	@Id
	@Column(name="minibar_id")
	private Integer minibarId;
	
	@Column(name="quantity")
	private Integer quantity;
	
	@Column(name="amount")
	private BigDecimal amount;
	
	
	public AdditionalChargesId getId() {
		return new AdditionalChargesId(minibarId, housingManagementId);
	}

	public void setId(AdditionalChargesId id) {
		this.minibarId = id.getMinibarId();
		this.housingManagementId = id.getHousingManagementId();
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
	
	
//	@Id
//	@Column(name="housingManagement_id")
//	public Integer gethousingManagementId() {
//		return housingManagementId;
//	}
//	
//	public void sethousingManagementId(Integer housingManagementId) {
//		this.housingManagementId = housingManagementId;
//	}
//	
//	@Id
//	@Column(name="minibar_id")
//	public Integer getMinibarId() {
//		return minibarId;
//	}
//	
//	public void setMinibarId(Integer minibarId) {
//		this.minibarId = minibarId;
//	}
	
//	@JsonIgnore
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name="fk_minibar_id")
//	@MapsId("minibarId")
//	private Minibar minibar;
//	
//	@JsonIgnore
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name="fk_housingmanagement_id")
//	@MapsId("housingManagementId")
//	private HousingManagement housingManagement;

	
	
	
}
