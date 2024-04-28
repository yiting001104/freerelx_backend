package tw.team.project.model;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "comment")
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "comment_id")
	private Integer commentId;
	
	@Column(name = "comment_text", nullable = false)
	private String commentText;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss EEEE") 
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date", nullable = false)
	private Date createDate;
	
	@JsonIgnore // 當回傳形式是JSON形式時就會忽略
	@Lob
	private byte[] picture;
	
	
	@Column(name = "situation_type", nullable = false)
	private String situationType;
	
	@ManyToOne
	@JoinColumn(name = "member_id",referencedColumnName = "member_id")
	private Member member;
	
	@Column(name = "type_instance", nullable = false)
	private String typeInstance;
	
	private BigDecimal score;


	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	public String getSituationType() {
		return situationType;
	}

	public void setSituationType(String situationType) {
		this.situationType = situationType;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public String getTypeInstance() {
		return typeInstance;
	}

	public void setTypeInstance(String typeInstance) {
		this.typeInstance = typeInstance;
	}

	public BigDecimal getScore() {
		return score;
	}

	public void setScore(BigDecimal score) {
		this.score = score;
	}
	
	
}
