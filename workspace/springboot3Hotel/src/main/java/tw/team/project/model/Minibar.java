package tw.team.project.model;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@Table(name="minibar")
public class Minibar {
	
	@Id
	@Column(name="minibar_id")
	private Integer id;
	
	@Column(name="minibar_price")
	private BigDecimal price;
	
	@Column(name="minibar_item")
	private String item;
	
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss EEEE") // 若 thymeleaf 要讀取到這個設定，EL 要用雙層大括號
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "minibar_make")
	private Date make;
	
	@Column(name = "minibar_expire")
	private Integer expire;
	
	@Column(name = "minibar_photo", columnDefinition = "image")
	private byte[] photo;

	@Override
	public String toString() {
		return "Minibar [id=" + id + ", price=" + price + ", item=" + item + ", make=" + make + ", photo="
				+ Arrays.toString(photo) + "]";
	}
	
	

//	
//	@OneToMany(mappedBy = "AdditionalCharges", cascade=CascadeType.ALL)
//	private List<AdditionalCharges> additionlCharges = new ArrayList<>();

}
