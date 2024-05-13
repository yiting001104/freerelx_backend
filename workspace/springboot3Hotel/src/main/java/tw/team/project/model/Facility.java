package tw.team.project.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Facility")
public class Facility {

	@Id
	@Column(name = "FACILITY")
	private Integer facility;

	@Column(name = "FACILITY_NAME")
	private String facilityName;

	@Column(name = "FACILITY_TYPE")
	private String facilityType;

	@Column(name = "MAXIMUM_CAPACITY")
	private Integer maximumCapacity;

	public Integer getFacility() {
		return facility;
	}

	public void setFacility(Integer facility) {
		this.facility = facility;
	}

	public String getFacilityName() {
		return facilityName;
	}

	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}

	public String getFacilityType() {
		return facilityType;
	}

	public void setFacilityType(String facilityType) {
		this.facilityType = facilityType;
	}

	public Integer getMaximumCapacity() {
		return maximumCapacity;
	}

	public void setMaximumCapacity(Integer maximumCapacity) {
		this.maximumCapacity = maximumCapacity;
	}

}
