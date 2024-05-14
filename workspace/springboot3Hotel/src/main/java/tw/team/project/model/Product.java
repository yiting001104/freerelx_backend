package tw.team.project.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "product")
public class Product {
	 //0001
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private Integer id;

	@Column(name = "product_name")
	private String productName;

	@Column(name = "product_price")
	private Integer productPrice;

	@Column(name = "product_stock")
	private Integer productStock;

	@Column(name = "product_description")
	private String productDescription;

	@ManyToOne
	@JoinColumn(name = "product_supplier_id")
	private Supplier productSupplierId;

//	@ManyToOne
//	@JoinColumn(name = "product_category_id")
//	private Category productCategoryId;

	@OneToMany(mappedBy = "productid", cascade = CascadeType.ALL)
	private List<Productphoto> productphoto = new ArrayList<>();

	public Supplier getProductSupplierId() {
		return productSupplierId;
	}

	public void setProductSupplierId(Supplier productSupplierId) {
		this.productSupplierId = productSupplierId;
	}

	@Column(name = "product_expected_arrival_day")
	private Integer productArrivalDay;

	public Product() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Integer productPrice) {
		this.productPrice = productPrice;
	}

	public Integer getProductStock() {
		return productStock;
	}

	public void setProductStock(Integer productStock) {
		this.productStock = productStock;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

//	public Category getProductCategoryId() {
//		return productCategoryId;
//	}
//
//	public void setProductCategoryId(Category productCategoryId) {
//		this.productCategoryId = productCategoryId;
//	}

	public Integer getProductArrivalDay() {
		return productArrivalDay;
	}

	public void setProductArrivalDay(Integer productArrivalDay) {
		this.productArrivalDay = productArrivalDay;
	}

}
