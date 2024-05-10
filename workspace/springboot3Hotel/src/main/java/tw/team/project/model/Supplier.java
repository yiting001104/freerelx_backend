package tw.team.project.model;

import java.util.ArrayList;
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
@Table(name="supplier")
public class Supplier {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="product_supplier_id")
	private Integer id;
	
	@Column(name = "product_manufacturer")
	private String name;

	@Column(name="product_manufacturer_contact_name")
	private String cname;
	
	@Column(name="product_manufacturer_contact_phone")
	private String phone;
	
	@Column(name="product_manufacturer_contact_email")
	private String email;
	
	@Column(name="product_manufacturer_address")
	private String address;
	
	
	@OneToMany(mappedBy = "productSupplierId" ,cascade = CascadeType.ALL)
	private List<Product> products=new ArrayList<>();
	
	public Supplier() {
		
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getCname() {
		return cname;
	}


	public void setCname(String cname) {
		this.cname = cname;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}
	
}