package tw.team.project.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="roomManagement")
public class RoomManagement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="room_management_id")
	private Integer id;
	
	@Column(name="room_number")
	private Integer number;
	
	@JsonBackReference
	@OneToMany(mappedBy="roomManagement", cascade=CascadeType.ALL)
	private List<HousingManagement> housingManagement;
	
	@Column(name="repair_status")
	private String repairStatus;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="room_information_id", referencedColumnName = "room_information_id")
	private RoomInformation roomInformation;
	
	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name="room_state_id", referencedColumnName = "room_state_id")
	private RoomState roomState;
	
}
