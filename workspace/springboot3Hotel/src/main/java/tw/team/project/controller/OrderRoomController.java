package tw.team.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tw.team.project.model.OrderRoom;
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
								.put("reservation_status", order.getReservationStatus())
								.put("reservation_status_date", order.getReservationSaDate())
								.put("transaction_password", order.getTransactionPassword())
//								.put("member_id", order.getMember().getMemberId())
								.put("cancellation_reason", order.getCancellReason())
								.put("order_status", order.getOrderStatus())
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
								.put("reservation_status", order.getReservationStatus())
								.put("reservation_status_date", order.getReservationSaDate())
								.put("transaction_password", order.getTransactionPassword())
//								.put("member_id", order.getMember().getMemberId())
								.put("cancellation_reason", order.getCancellReason())
								.put("order_status", order.getOrderStatus())
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
}
