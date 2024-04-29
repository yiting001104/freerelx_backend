package tw.team.project.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name="cart")
public class Cart {
	
	@EmbeddedId
	private CartId cartId;
	
	private Integer quantity;
	
	@MapsId("memberId")
	@ManyToOne(fetch = FetchType.LAZY)
	private Member member;
	
	@MapsId("id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Product id;

	public Cart() {
		
	}

	public CartId getCartId() {
		return cartId;
	}

	public void setCartId(CartId cartId) {
		this.cartId = cartId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Product getId() {
		return id;
	}

	public void setId(Product id) {
		this.id = id;
	}
	

}
