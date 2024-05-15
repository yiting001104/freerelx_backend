package tw.team.project.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
//0002外來鍵
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
 //0000
    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails=new ArrayList<>();;
    //
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss EEEE") // ${{latestMsg.added}}讀取左邊設定
	@Temporal(TemporalType.TIMESTAMP)
	private Date addedTime;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss EEEE") // ${{latestMsg.added}}讀取左邊設定
	@Temporal(TemporalType.TIMESTAMP)
	private Date arriveddTime;
    
    private Integer total;

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	@PrePersist // 物件狀態轉到PERSISTENT 以前先執行這個方法
	public void onCreate() {
		if (addedTime == null) {
			addedTime = new Date();
		}
	}
	private String memberName;
	
	private String payment;
	

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}
	private String contactAddress;
	
	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getContactAddress() {
		return contactAddress;
	}

	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	private String phoneNumber;
	
	private String orderstatus;
	
	public String getOrderstatus() {
		return orderstatus;
	}

	public void setOrderstatus(String orderstatus) {
		this.orderstatus = orderstatus;
	}
    //
    public Order() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
	public Date getAddedTime() {
		return addedTime;
	}
	public void setAddedTime(Date addedTime) {
		this.addedTime = addedTime;
	}

	public Date getArriveddTime() {
		return arriveddTime;
	}

	public void setArriveddTime(Date arriveddTime) {
		this.arriveddTime = arriveddTime;
	}
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
