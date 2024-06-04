package tw.team.project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "alert")
public class Alert {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "member_id", referencedColumnName = "member_id")
	private Member memberid;

	public String getAlertmessage() {
		return alertmessage;
	}
	public void setAlertmessage(String alertmessage) {
		this.alertmessage = alertmessage;
	}
	private String alertmessage;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Member getMemberid() {
		return memberid;
	}
	public void setMemberid(Member memberid) {
		this.memberid = memberid;
	}
	public Alert() {
	}

}
