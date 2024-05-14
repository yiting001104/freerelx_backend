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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="additionalCharges")
public class AdditionalCharges {
	
	@EmbeddedId
	private AdditionalChargesId additionalChargesId;

	@Column(name="quantity")
	private Integer quantity;
	
	@Column(name="amount")
	private BigDecimal amount;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="fk_minibar_id")
	@MapsId("minibarId")
	private Minibar minibar;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="fk_housingmanagement_id")
	@MapsId("housingManagementId")
	private HousingManagement housingManagement;

	
}
