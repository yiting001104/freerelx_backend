package tw.team.project.model;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "refundType")
public class RefundType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "refundType_id")
	private Integer refundTypeId;
	
	@Column(name = "type", nullable = false)
	private String type;
	
	@Column(name = "refund_ratio", nullable = false)
	private BigDecimal refundRatio;
	
	@OneToMany(mappedBy = "refundType",cascade = CascadeType.ALL)
	private List<Transaction> transcations;
	



	public List<Transaction> getTranscations() {
		return transcations;
	}

	public void setTranscations(List<Transaction> transcations) {
		this.transcations = transcations;
	}

	public Integer getRefundTypeId() {
		return refundTypeId;
	}

	public void setRefundTypeId(Integer refundTypeId) {
		this.refundTypeId = refundTypeId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getRefundRatio() {
		return refundRatio;
	}

	public void setRefundRatio(BigDecimal refundRatio) {
		this.refundRatio = refundRatio;
	}
}
