package tw.team.project.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="roomLevel")
public class RoomLevel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="room_level_id")
	private Integer id;
	
	@Column(name="chinese")	
	private String chinese;
	
	@Column(name="english")
	private String english;
	
	@Column(name="japanese")
	private String japanese;
	
	@OneToMany(mappedBy="roomLevel", cascade=CascadeType.ALL)
	private List<RoomInformation> roomInformation;

}
