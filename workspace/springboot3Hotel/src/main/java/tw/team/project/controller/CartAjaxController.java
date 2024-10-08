package tw.team.project.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.json.JSONException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.team.project.model.Cart;
import tw.team.project.model.Member;
import tw.team.project.model.MemberRepository;
import tw.team.project.model.Order;
import tw.team.project.model.OrderDeatilRepository;
import tw.team.project.model.OrderDetail;
import tw.team.project.model.OrderRepository;
import tw.team.project.service.CartService;
import tw.team.project.service.ProductService;

@RestController
@RequestMapping("hotel")
@CrossOrigin
public class CartAjaxController {

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private OrderDeatilRepository orderDeatilRepository;
	
	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CartService cartService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
    private JavaMailSender javaMailSender;


	@PostMapping("/carts/post") // 顧客新增購物車一筆資料的功能
	public String createcart(@RequestBody String json) throws JSONException {
		JSONObject responseJson = new JSONObject();
		Cart cart = cartService.create(json);
		if (cart == null) {
			responseJson.put("success", false);
			responseJson.put("message", "新增失敗");
		} else {
			responseJson.put("success", true);
			responseJson.put("message", "新增成功");
		}
		return responseJson.toString();
	}

	@PutMapping("/carts/delete") // 顧客刪除購物車一筆資料的功能
	public String deletecart(@RequestBody String json) throws JSONException {
		JSONObject responseJson = new JSONObject();

		boolean isdelete = cartService.delete(json);
		if (isdelete == false) {
			responseJson.put("success", false);
			responseJson.put("message", "刪除失敗");
		} else {
			responseJson.put("success", true);
			responseJson.put("message", "刪除成功");
		}

		return responseJson.toString();
	}

	@PutMapping("/carts/checkoutchange") // 顧客是否要結帳的功能
	public String checkoutchange(@RequestBody String json) throws JSONException {
		JSONObject responseJson = new JSONObject();

		boolean isdelete = cartService.checkoutchange(json);
		if (isdelete == false) {
			responseJson.put("success", false);
			responseJson.put("message", "訂單狀態更改失敗");
		} else {
			responseJson.put("success", true);
			responseJson.put("message", "訂單狀態更改成功");
		}
		return responseJson.toString();
	}

	@PostMapping("/carts/find") // 顧客查看自己的訂單
	public String find(@RequestBody String json) throws JSONException {
		JSONObject responseJson = new JSONObject();
		JSONArray array = new JSONArray();
		List<Cart> carts = cartService.find(json);
		if (carts != null && !carts.isEmpty()) {
			for (Cart membercart : carts) {
				JSONObject item = new JSONObject().put("memberid", membercart.getCartId().getMemberId())
						.put("productid", membercart.getCartId().getId())
						.put("quantity", membercart.getQuantity())
						.put("productname",productService.findById(membercart.getCartId().getId()).getProductName())
						.put("productprice",productService.findById(membercart.getCartId().getId()).getProductPrice())
						.put("productStock",productService.findById(membercart.getCartId().getId()).getProductStock())
						.put("check", membercart.isCheckout());
				array.put(item);
			}
		}
		responseJson.put("list", array);
		return responseJson.toString();
	}

	@PostMapping("/carts/order") // 顧客要結帳購物車裡的東西
	public String cartsentorder(@RequestBody String json) throws JSONException {
		Integer total=0;
		JSONObject responseJson = new JSONObject();
		JSONArray array = new JSONArray();
		List<Cart> carts = cartService.findByMemberIdAndcheckout(json);
		if (carts != null && !carts.isEmpty()) {
			JSONObject jsonobject = new JSONObject(json);
			Integer memberId = jsonobject.isNull("memberId") ? null : jsonobject.getInt("memberId");
			Integer usebonus = jsonobject.isNull("usebonus") ? null : jsonobject.getInt("usebonus");
			String name = jsonobject.isNull("name") ? null : jsonobject.getString("name");
			String phone = jsonobject.isNull("phone") ? null : jsonobject.getString("phone");
			String address = jsonobject.isNull("address") ? null : jsonobject.getString("address");
			String payerName = jsonobject.isNull("payername") ? null : jsonobject.getString("payername");
			String payerPhoneNumber = jsonobject.isNull("payerphone") ? null : jsonobject.getString("payerphone");
			String payerContactAddress = jsonobject.isNull("payeradress") ? null : jsonobject.getString("payeradress");
				Order order=new Order();
				order.setOrderstatus("訂單成立");
				order.setMemberName(name);
				order.setPhoneNumber(phone);
				order.setContactAddress(address);
				order.setPayerName(payerName);
				order.setPayerPhoneNumber(payerPhoneNumber);
				order.setPayerContactAddress(payerContactAddress);
				order.setUsebonus(usebonus);
			for (Cart membercart : carts) {
				JSONObject item = new JSONObject()
						.put("memberid", membercart.getCartId().getMemberId())
						.put("productid", membercart.getCartId().getId())
						.put("Quantity",membercart.getQuantity());
				order.setMember(membercart.getMember());
				orderRepository.save(order);
				cartService.deletecart(membercart.getCartId().getMemberId(), membercart.getCartId().getId());
				OrderDetail orderDetail=new OrderDetail();
				orderDetail.setOrder(order);
				orderDetail.setQuantity(membercart.getQuantity());
				orderDetail.setProduct(membercart.getId());
				orderDetail.setProductmultiplequantity(membercart.getId().getProductPrice()*membercart.getQuantity());
				total=total+membercart.getId().getProductPrice()*membercart.getQuantity();
				orderDeatilRepository.save(orderDetail);
				array.put(item);
			}
			order.setTotal(total);
			order.setAddbonus((int) (total*0.1));
			order.setTotalminususebonus(total-usebonus);
			orderRepository.save(order);
			Member member=memberRepository.findById(memberId).get();
			member.setBonusPoint(member.getBonusPoint().add(new BigDecimal(total*0.1)).add(new BigDecimal(usebonus*-1)));
			memberRepository.save(member);
		}
		responseJson.put("list", array);
		SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("kawhi060941@gmail.com"); //設置收件人信箱
        message.setSubject("已收到您的訂單"); //設置信箱主題
        message.setText("謝謝您"); //設置信箱內容
        message.setFrom("Relax Hotel<kawhi060941@gmail.com>");// 寄信人
        System.out.println(message);
        javaMailSender.send(message); //發送郵件
		return responseJson.toString();
	}

	@PutMapping("/carts/modify")
	public String modify(@RequestBody String json) throws JSONException {
		JSONObject responseJson = new JSONObject();
			Cart cart = cartService.modify(json);
			if (cart == null) {
				responseJson.put("success", false);
				responseJson.put("message", "修改失敗");
			} else {
				responseJson.put("success", true);
				responseJson.put("message", "修改成功");
			}
		return responseJson.toString();
	}

	@PostMapping("/carts/selectall") 
	public String selectall(@RequestBody String json) throws JSONException {
		JSONObject responseJson = new JSONObject();
		boolean select = cartService.selectall(json);
		if (select == false) {
			responseJson.put("success", false);
			responseJson.put("message", "訂單狀態更改失敗");
		} else {
			responseJson.put("success", true);
			responseJson.put("message", "訂單狀態更改成功");
		}
	return responseJson.toString();
	}
	//結帳時需要個資
	@GetMapping("/carts/mes/{pk}")
	public String findById(@PathVariable(name = "pk") Integer id) throws JSONException {
		JSONObject responseJson = new JSONObject();
		JSONArray array = new JSONArray();
		Optional<Member> member= memberRepository.findById(id);
		if (member.get() != null) {
			JSONObject item = new JSONObject()
					.put("memberid", member.get().getMemberId())
					.put("MemberName",member.get().getMemberName())
					.put("birth", member.get().getBirth())
					.put("gender", member.get().getGender())
					.put("nationId", member.get().getNationId())
					.put("email", member.get().getEmail())
					.put("contactAddress", member.get().getContactAddress())
					.put("phoneNumber", member.get().getPhoneNumber())
					.put("creditcard", member.get().getCreditCard())
					.put("nationality", member.get().getNationality())
					.put("logintime", member.get().getLoginTime())
					.put("loginstatus", member.get().getLoginStatus())
					.put("picture",member.get().getPicture())
					.put("bonus", member.get().getBonusPoint());
			array.put(item);
		}
		responseJson.put("listt", array);
		System.out.print(responseJson.toString());
		return responseJson.toString();
	}

	@PostMapping("/carts/check") // 顧客真的要結帳了
	public String check(@RequestBody String json) throws JSONException {
		JSONObject responseJson = new JSONObject();
		JSONArray array = new JSONArray();
		List<Cart> carts = cartService.findByMemberIdAndcheckout(json);
		if (carts != null && !carts.isEmpty()) {
			for (Cart membercart : carts) {
				JSONObject item = new JSONObject().put("memberid", membercart.getCartId().getMemberId())
						.put("productid", membercart.getCartId().getId())
						.put("quantity", membercart.getQuantity())
						.put("productname",productService.findById(membercart.getCartId().getId()).getProductName())
						.put("productprice",productService.findById(membercart.getCartId().getId()).getProductPrice())
						.put("productStock",productService.findById(membercart.getCartId().getId()).getProductStock())
						.put("check", membercart.isCheckout());
				array.put(item);
			}
		}
		responseJson.put("list", array);
		return responseJson.toString();
	}
}