package tw.team.project.service;

import java.util.List;
import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.team.project.model.Cart;
import tw.team.project.model.CartId;
import tw.team.project.model.CartRepository;
import tw.team.project.model.Member;
import tw.team.project.model.MemberRepository;
import tw.team.project.model.Product;
import tw.team.project.model.ProductRepository;

@Service
public class CartService {

	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CartRepository cartRepository;

//1號成員，加入一個"數量是1的一號產品到購物車"
	public Cart create(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			Integer memberId = obj.isNull("memberId") ? null : obj.getInt("memberId");
			Integer productId = obj.isNull("productId") ? null : obj.getInt("productId");
			Integer quality = obj.isNull("quality") ? null : obj.getInt("quality");

			Optional<Member> op = memberRepository.findById(memberId);
			Member user = op.get();
			Optional<Product> op1 = productRepository.findById(productId);
			Product item = op1.get();

			CartId cartId = new CartId();
			cartId.setId(productId);
			cartId.setMemberId(memberId);

			Cart cart = new Cart();
			cart.setCartId(cartId);
			cart.setId(item);
			cart.setMember(user);
			cart.setQuantity(quality);
			return cartRepository.save(cart);

		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

/////////////////////////////////////////////////////////////////////
	// 1號成員，刪除一筆購物車的內容"找到再刪除
	public boolean delete(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			Integer memberId = obj.isNull("memberId") ? null : obj.getInt("memberId");
			Integer productId = obj.isNull("productId") ? null : obj.getInt("productId");
			CartId cartId = new CartId();
			cartId.setId(productId);
			cartId.setMemberId(memberId);
			Optional<Cart> cartexist = cartRepository.findById(cartId);
			if (cartexist != null) {
				cartexist.get();
				cartRepository.deleteById(cartId);
				return true;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return false;
	}

//修改購物車勾選狀態
	public boolean checkoutchange(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			Integer memberId = obj.isNull("memberId") ? null : obj.getInt("memberId");
			Integer productId = obj.isNull("productId") ? null : obj.getInt("productId");

			CartId cartId = new CartId();
			cartId.setId(productId);
			cartId.setMemberId(memberId);
			Optional<Cart> cartexist = cartRepository.findById(cartId);
			if (cartexist.get() != null) {
				if (cartexist.get().isCheckout() == false) {
					cartexist.get().setCheckout(true);
					cartRepository.save(cartexist.get());
				} else {
					cartexist.get().setCheckout(false);
					cartRepository.save(cartexist.get());
				}
				return true;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return false;
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public List<Cart> find(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			Integer memberId = obj.isNull("memberId") ? null : obj.getInt("memberId");
			return cartRepository.findByMemberIdCart(memberId);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////
	public List<Cart> findByMemberIdAndcheckout(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			Integer memberId = obj.isNull("memberId") ? null : obj.getInt("memberId");
			return cartRepository.findByMemberIdAndcheckout(memberId);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void deletecart(Integer memberId, Integer productId) {
		CartId cartId = new CartId();
		cartId.setId(productId);
		cartId.setMemberId(memberId);
		Optional<Cart> cartexist = cartRepository.findById(cartId);
		if (cartexist != null) {
			cartexist.get();
			cartRepository.deleteById(cartId);
		}

	}

//修改購物車的數量
	public Cart modify(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			System.out.println(json);
			Integer memberId = obj.isNull("memberId") ? null : obj.getInt("memberId");
			Integer productId = obj.isNull("productId") ? null : obj.getInt("productId");
			Integer quality = obj.isNull("quality") ? null : obj.getInt("quality");
			CartId cartId = new CartId();
			cartId.setId(productId);
			cartId.setMemberId(memberId);
			Cart cart = new Cart();
			cart.setCartId(cartId);
			Optional<Cart> cartexist = cartRepository.findById(cartId);
			if (cartexist != null) {
				cartexist.get().setQuantity(quality);
				return cartRepository.save(cartexist.get());
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean selectall(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			Integer memberId = obj.isNull("memberId") ? null : obj.getInt("memberId");
			List<Cart> all = cartRepository.findByMemberIdCart(memberId);
			for (Cart one : all) {
				one.setCheckout(true);
				cartRepository.save(one);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return true;
	}
}
