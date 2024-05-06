package tw.team.project.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import tw.team.project.model.Member;
import tw.team.project.model.OrderRoom;
import tw.team.project.service.MemberService;
import tw.team.project.service.OrderRoomService;

@RestController
@RequestMapping("/hotel")
public class OrderRoomController {

	@Autowired
	private OrderRoomService ordRoomService;

	
	@GetMapping("/orderRoom/page")
	public String listOrderPage(@RequestParam(value = "p",defaultValue = "1") Integer pageNumber) {
		Page<OrderRoom> page = ordRoomService.findOrderByPage(pageNumber);
		JSONObject responseJson = new JSONObject();
		JSONArray array = new JSONArray();
		try {
			for (OrderRoom order:page.getContent()) {
				JSONObject obj = new JSONObject()
								.put("order_id", order.getOrderId())
								.put("orderdate", order.getOrderDate())
								.put("order_person_name", order.getCustomerName())
								.put("gender", order.getGender())
								.put("birth", order.getBirth())
								.put("national_id", order.getNationId())
								.put("email", order.getEmail())
								.put("phone_number", order.getPhone())
								.put("credit_card", order.getCreditCard())
								.put("adult_pax", order.getAdultPax())
								.put("child_pax", order.getChildPax())
								.put("room_type_amount", order.getRoomAmount())
								.put("arrival_date", order.getArrivateDate())
								.put("checkout_date", order.getCheckoutDate())
								.put("reservation_status", order.getReservationStatus())
								.put("reservation_status_date", order.getReservationSaDate())
								.put("transaction_password", order.getTransactionPassword())
//								.put("member_id", order.getMember().getMemberId())
								.put("cancellation_reason", order.getCancellReason())
								.put("base_price", order.getBasePrice())
								.put("stay_person_name", order.getSPName())
								.put("stay_person_gender", order.getSPGender())
								.put("stay_person_birth", order.getSPBirth())
								.put("stay_person_national_id",  order.getSPNationId())
								.put("stay_person_phone", order.getSPPhone())
								.put("stay_person_Email", order.getSPEmail())
								.put("remark", order.getRemark());
				array.put(obj);
			}
			responseJson.put("data", array);
			return responseJson.toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@GetMapping("/orderRoom/page2")
	public String listOrderCusPage(@RequestParam(value = "p",defaultValue = "1") Integer pageNumber) {
		Page<OrderRoom> page = ordRoomService.findOrderCusByPage(pageNumber);
		JSONObject responseJson = new JSONObject();
		JSONArray array = new JSONArray();
		try {
			for (OrderRoom order:page.getContent()) {
				JSONObject obj = new JSONObject()
								.put("order_id", order.getOrderId())
								.put("orderdate", order.getOrderDate())
								.put("order_person_name", order.getCustomerName())
								.put("gender", order.getGender())
								.put("birth", order.getBirth())
								.put("national_id", order.getNationId())
								.put("email", order.getEmail())
								.put("phone_number", order.getPhone())
								.put("credit_card", order.getCreditCard())
								.put("adult_pax", order.getAdultPax())
								.put("child_pax", order.getChildPax())
								.put("room_type_amount", order.getRoomAmount())
								.put("arrival_date", order.getArrivateDate())
								.put("checkout_date", order.getCheckoutDate())
								.put("reservation_status", order.getReservationStatus())
								.put("reservation_status_date", order.getReservationSaDate())
								.put("transaction_password", order.getTransactionPassword())
//								.put("member_id", order.getMember().getMemberId())
								.put("cancellation_reason", order.getCancellReason())
								.put("base_price", order.getBasePrice())
								.put("stay_person_name", order.getSPName())
								.put("stay_person_gender", order.getSPGender())
								.put("stay_person_birth", order.getSPBirth())
								.put("stay_person_national_id",  order.getSPNationId())
								.put("stay_person_phone", order.getSPPhone())
								.put("stay_person_Email", order.getSPEmail())
								.put("remark", order.getRemark());
				array.put(obj);
			}
			responseJson.put("data", array);
			return responseJson.toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@PostMapping("/orderRoom/add")
	public String orderCreate(@RequestBody String json, HttpSession httpSession) {
//		Integer id = (Integer)httpSession.getAttribute("loginUserId");
		Integer id = null;
		JSONObject responseJson = new JSONObject();
		OrderRoom orderRoom= ordRoomService.create(json, id);
        try {
			if(orderRoom==null) {
			    responseJson.put("success", false);
			    responseJson.put("message", "新增失敗");
			} else {
			    responseJson.put("success", true);
			    responseJson.put("message", "新增成功");
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return responseJson.toString();
	}
	
	@GetMapping("/orderRoom/{pk}")
	public String findById(@PathVariable(name = "pk") Integer id) {
		JSONObject responseJson = new JSONObject();
		JSONArray array = new JSONArray();
		OrderRoom order = ordRoomService.findById(id);
		try {
			if (order != null) {
				System.out.print(order.getMember()==null);
				JSONObject obj  = new JSONObject()
						.put("order_id", order.getOrderId())
						.put("orderdate", order.getOrderDate())
						.put("order_person_name", order.getCustomerName())
						.put("gender", order.getGender())
						.put("birth", order.getBirth())
						.put("national_id", order.getNationId())
						.put("email", order.getEmail())
						.put("phone_number", order.getPhone())
						.put("credit_card", order.getCreditCard())
						.put("adult_pax", order.getAdultPax())
						.put("child_pax", order.getChildPax())
						.put("room_type_amount", order.getRoomAmount())
						.put("arrival_date", order.getArrivateDate())
						.put("checkout_date", order.getCheckoutDate())
						.put("reservation_status", order.getReservationStatus())
						.put("reservation_status_date", order.getReservationSaDate())
						.put("transaction_password", order.getTransactionPassword())
						.put("cancellation_reason", order.getCancellReason())
						.put("base_price", order.getBasePrice())
						.put("stay_person_name", order.getSPName())
						.put("stay_person_gender", order.getSPGender())
						.put("stay_person_birth", order.getSPBirth())
						.put("stay_person_national_id",  order.getSPNationId())
						.put("stay_person_phone", order.getSPPhone())
						.put("stay_person_Email", order.getSPEmail())
						.put("remark", order.getRemark());
					
				
				if (order.getMember()!=null) {
					obj.put("member_id", order.getMember().getMemberId());
				}else
					obj.put("member_id", "null");
				
				array.put(obj);
			}
			responseJson.put("data", array);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return responseJson.toString();
	}
}
