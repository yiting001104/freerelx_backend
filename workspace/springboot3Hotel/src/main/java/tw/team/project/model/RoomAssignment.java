package tw.team.project.model;


import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name="roomAssignment")
public class RoomAssignment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="assignment_id")
	private Integer id;
	
	@Column(name="rooms_left", nullable = false)
	private Integer left;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="assignment_date", nullable = false)
	private Date date;
	
//	@ManyToOne
//	@JoinColumn(name="order_room_detail_id", referencedColumnName = "order_id, room_Information_Id")
//	private OderRoomId orderRoomId;
	
	@ManyToOne
	@JoinColumn(name="room_information_id", referencedColumnName = "room_information_id")
	private RoomInformation roomInformation;
	
}
