package tw.team.project.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.DynamicInsert;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="housingManagement")
@DynamicInsert
public class HousingManagement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="housing_management_id")
	private Integer id;
	
//	@JsonManagedReference
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="room_management_id", referencedColumnName = "room_management_id")
	private RoomManagement  roomManagement;
	
	@ManyToOne
	@JoinColumn(name="order_id", referencedColumnName = "order_id")
	private OrderRoom orderRoom;
	
	@Column(name="remarks")
	private String remarks;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss EEEE") 
	@Temporal(TemporalType.TIMESTAMP)
	private Date checkInTime;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss EEEE") 
	@Temporal(TemporalType.TIMESTAMP)
	private Date checkOutTime;
	
	@Column(name="total_compensation_fee")
	private BigDecimal totalCompensation;
	
	@Column(name="total_additional_fee")
	private BigDecimal totalAdditional;
	
//	@JsonBackReference
	@JsonIgnore
	@OneToMany(mappedBy="housingManagement", cascade=CascadeType.ALL)
	private List<CheckOutInspection> checkOutInspection;

//	@JsonIgnore
//	@OneToMany(mappedBy="housingManagement", cascade=CascadeType.ALL)
//	private List<AdditionalCharges> additionalCharges = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "housingManagementId", cascade=CascadeType.ALL)
	private Set<AdditionalCharges> additionalCharges = new HashSet<>();

}
