package tw.team.project.model;


import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@Entity
@Table(name="roomInformation")
public class RoomInformation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="room_information_id")
	private Integer id;
	
//	@JsonManagedReference
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="room_type_id", referencedColumnName = "room_type_id")
	private RoomType roomType;
	
//	@JsonManagedReference
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="room_level_id", referencedColumnName = "room_level_id")
	private RoomLevel roomLevel;
	
	@Column(name="bed_type", nullable = false)
	private String bedType;
	
	@Column(name="max_occupancy", nullable = false)
	private Integer  maxOccupancy;
	
	@Column(name="room_price", nullable = false)
	private BigDecimal price;
	
	@Column(name="room_photo", nullable = false)
	private String photo;
	
	@Column(name="room_depiction", nullable = false)
	private String depiction;
	
	@Column(name="room_total")
	private Integer total;

	@JsonBackReference
	@OneToMany(mappedBy="roomInformation", cascade=CascadeType.ALL)
	private List<RoomAssignment> roomAssignment;
	
	@JsonBackReference
	@OneToMany(mappedBy="roomInformation", cascade=CascadeType.ALL)
	private List<RoomManagement> roomManagement;


}
