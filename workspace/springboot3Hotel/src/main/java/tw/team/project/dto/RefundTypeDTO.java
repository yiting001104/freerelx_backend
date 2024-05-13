package tw.team.project.dto;

import java.math.BigDecimal;



public class RefundTypeDTO {

	private Integer refundTypeId;

	private String type;

	private BigDecimal refundRatio;

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
