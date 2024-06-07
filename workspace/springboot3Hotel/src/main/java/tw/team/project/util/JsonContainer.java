package tw.team.project.util;

import org.json.JSONException;
import org.json.JSONObject;

import tw.team.project.dto.CreditCardDiscountDTO;
import tw.team.project.dto.MemberOrdersDTO;
import tw.team.project.dto.RefundTypeDTO;
import tw.team.project.dto.TransactionDTO;
import tw.team.project.model.CreditCardDiscount;
import tw.team.project.model.OrderRoom;
import tw.team.project.model.RefundType;
import tw.team.project.model.Transaction;

public class JsonContainer {

	public JSONObject getOrderRoom(OrderRoom order) {
		try {
			if (order != null) {
				System.out.print(order.getMember()==null);
				String date = order.getCheckoutDate()+"";
				String CheckoutDate = date.split(" ")[0];
				System.out.println(CheckoutDate);
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
						.put("checkout_date", CheckoutDate)
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

	public JSONObject getTransaction(Transaction trans){
		try {
			if (trans != null){
				JSONObject obj = new JSONObject()
			                .put("transaction_Id", trans.getTransactionId())
			                .put("amount", trans.getAmount())
			                .put("order_id", trans.getOrderRoom().getOrderId())
			                .put("last_five_account_number", trans.getLastFiveAccNum())
			                .put("transfer_date", trans.getTransferDate())
			                .put("taxIDNumber", trans.getTaxIDNumber())
			                .put("unsubscribe_date", trans.getUnsubscribeDate())
			                .put("refund_amount", trans.getRffundAmount())
			                .put("remark", trans.getRemark());
				if (trans.getDiscounts() !=null){
					obj.put("discount_id", trans.getDiscounts().getBankId());
				} else {
					obj.put("discount_id", "null");
				}
				if (trans.getRefundType() !=null){
					obj.put("refund_id", trans.getRefundType().getRefundTypeId());
				} else {
					obj.put("refund_id", "null");
				}
				return obj;
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public TransactionDTO setTransaction(Transaction trans){
			TransactionDTO transDTO = new TransactionDTO();
			if (trans != null){
				transDTO.setTransactionId(trans.getTransactionId());
				transDTO.setAmount(trans.getAmount());
				transDTO.setOrder_id(trans.getOrderRoom().getOrderId());
				transDTO.setLastFiveAccNum(trans.getLastFiveAccNum());
				transDTO.setTransferDate(trans.getTransferDate());
				transDTO.setTaxIDNumber(trans.getTaxIDNumber());
				transDTO.setUnsubscribeDate(trans.getUnsubscribeDate());
				transDTO.setRffundAmount(trans.getRffundAmount());
				transDTO.setRemark(trans.getRemark());
				if (trans.getDiscounts() !=null){
					transDTO.setDiscount_id(trans.getDiscounts().getBankId());
				} else {
					transDTO.setDiscount_id(null);
				}
				if (trans.getRefundType() !=null){
					transDTO.setRefund_id(trans.getRefundType().getRefundTypeId());
					
				} else {
					transDTO.setRefund_id(null);
				}
				return transDTO;
			}
	
		return null;

	}

	public CreditCardDiscountDTO setCrediteCard(CreditCardDiscount banks){
		CreditCardDiscountDTO bankDTO = new CreditCardDiscountDTO();
		if (banks != null){
			bankDTO.setBankId(banks.getBankId());
			bankDTO.setBankName(banks.getBankName());
			bankDTO.setDiscount(banks.getDiscount());
			return bankDTO;
		}
		return null;
	}

	public RefundTypeDTO setRefundType(RefundType refundTypes){
		RefundTypeDTO refundTypeDTO = new RefundTypeDTO();
		if (refundTypes != null){
			refundTypeDTO.setRefundTypeId(refundTypes.getRefundTypeId());
			refundTypeDTO.setRefundRatio(refundTypes.getRefundRatio());
			refundTypeDTO.setType(refundTypes.getType());
			return refundTypeDTO;
		}
		return null;
	}
	
	public MemberOrdersDTO setMemberOrders(OrderRoom order) {
		MemberOrdersDTO memberOrd = new MemberOrdersDTO();
		if (order!=null) {
			memberOrd.setOrderDate(order.getOrderDate());
			memberOrd.setAdultPax(order.getAdultPax());
			memberOrd.setChildPax(order.getChildPax());
			memberOrd.setArrivateDate(order.getArrivateDate());
			memberOrd.setRoomAmount(order.getRoomAmount());
			memberOrd.setReservationStatus(order.getReservationStatus());
			memberOrd.setReservationSaDate(order.getReservationSaDate());
			memberOrd.setBasePrice(order.getBasePrice());
			return memberOrd;
		}
		return null;
	}
}

