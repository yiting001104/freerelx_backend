package tw.team.project.model;

import java.io.Serializable;
import java.util.Objects;

//@Getter
//@Setter
//@Embeddable
public class AdditionalChargesId implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer minibarId;
	
	private Integer housingManagementId;

	public AdditionalChargesId() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AdditionalChargesId(Integer minibarId, Integer housingManagementId) {
		super();
		this.minibarId = minibarId;
		this.housingManagementId = housingManagementId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(housingManagementId, minibarId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AdditionalChargesId other = (AdditionalChargesId) obj;
		return Objects.equals(housingManagementId, other.housingManagementId)
				&& Objects.equals(minibarId, other.minibarId);
	}

	/**
	 * @return the minibarId
	 */
	public Integer getMinibarId() {
		return minibarId;
	}

	/**
	 * @param minibarId the minibarId to set
	 */
	public void setMinibarId(Integer minibarId) {
		this.minibarId = minibarId;
	}

	/**
	 * @return the housingManagementId
	 */
	public Integer getHousingManagementId() {
		return housingManagementId;
	}

	/**
	 * @param housingManagementId the housingManagementId to set
	 */
	public void setHousingManagementId(Integer housingManagementId) {
		this.housingManagementId = housingManagementId;
	}
	
	
	
}
