package tw.team.project.util;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import tw.team.project.model.OrderRoom;

public class JsonContainer {

	public JSONObject getOrderRoom(OrderRoom order) {
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
				
				return obj;
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}

