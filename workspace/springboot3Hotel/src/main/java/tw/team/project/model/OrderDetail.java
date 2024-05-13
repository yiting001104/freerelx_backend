package tw.team.project.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "order_details")
public class OrderDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "detail_id")
	private Integer id;
//0000外來鍵
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id")
	private Order order;
//0001外來鍵
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	private Product product;

	private Integer quantity;
	
	private Integer productmultiplequantity;
	
	private String orderstatus;
	

	public Integer getProductmultiplequantity() {
		return productmultiplequantity;
	}

	public void setProductmultiplequantity(Integer productmultiplequantity) {
		this.productmultiplequantity = productmultiplequantity;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss EEEE") // ${{latestMsg.added}}讀取左邊設定
	@Temporal(TemporalType.TIMESTAMP)
	private Date addedTime;

	@PrePersist // 物件狀態轉到PERSISTENT 以前先執行這個方法
	public void onCreate() {
		if (addedTime == null) {
			addedTime = new Date();
		}
	}

	// 其他訂單詳細資料相關屬性

	public OrderDetail() {
	}

	// Getter 和 Setter 方法

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getOrderstatus() {
		return orderstatus;
	}

	public void setOrderstatus(String orderstatus) {
		this.orderstatus = orderstatus;
	}

	public Date getAddedTime() {
		return addedTime;
	}

	public void setAddedTime(Date addedTime) {
		this.addedTime = addedTime;
	}
	
	
}