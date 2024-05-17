package tw.team.project.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="checkOutInspection")
public class CheckOutInspection {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="compensation", nullable = false)
	private String compensation;
	
	@Column(name="compensation_fee", nullable = false)
	private BigDecimal fee;
	
	@Column(name="compensation_photo", columnDefinition = "image")
	private byte[] photo;
	
//	@JsonManagedReference
//	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="housing_management_id", referencedColumnName = "housing_management_id")
	private HousingManagement housingManagement;

}
