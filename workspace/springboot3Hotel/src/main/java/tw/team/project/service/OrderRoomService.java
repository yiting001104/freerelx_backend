package tw.team.project.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import tw.team.project.model.Member;
import tw.team.project.model.OrderRoom;
import tw.team.project.model.OrderRoomRepository;
import tw.team.project.model.Transaction;
import tw.team.project.util.EmailValidator;

@Service
public class OrderRoomService {

	@Autowired
	private OrderRoomRepository orderRoomRepo;
	@Autowired
	private MemberService memberservice;
	@Autowired
	private TransactionService transactionService;
	
	@Autowired
    private JavaMailSender javaMailSender;
	
	public Page<OrderRoom> findOrderByPage(Integer pageNumber, Integer dataNmuber){
		Pageable pgb = PageRequest.of(pageNumber-1, dataNmuber, Sort.Direction.DESC, "orderDate");
		Page<OrderRoom> page = orderRoomRepo.findAll(pgb);
		return page;
	}
	
	public Page<OrderRoom> findOrderCusByPage(Integer pageNumber){
		Pageable pgb = PageRequest.of(pageNumber-1, 4, Sort.Direction.DESC, "orderDate");
		Page<OrderRoom> page = orderRoomRepo.findCustomer(pgb);
		return page;
	}
	
	// 查詢單筆
	public OrderRoom findById(Integer id) {
		Optional<OrderRoom> optional = orderRoomRepo.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}else {
			return null;
		}
	}
	
	// 刪除資料
	public boolean deleteOrder(Integer id) {
		Optional<OrderRoom> optional = orderRoomRepo.findById(id);
		if (optional.isPresent()) {
			orderRoomRepo.deleteById(id);
			return true;
		}
		return false;
	}
	// 更新部分資料：基本資料不能更改
	@Transactional
	public OrderRoom update(Integer id, String json) {
		try {
			JSONObject obj = new JSONObject(json);
			String transaction_password = obj.isNull("transaction_password") ? null : obj.getString("transaction_password");
			String credit_card = obj.isNull("credit_card") ? null : obj.getString("credit_card");
			String stay_person_name = obj.isNull("stay_person_name") ? null : obj.getString("stay_person_name");
			String stay_person_gender = obj.isNull("stay_person_gender") ? null : obj.getString("stay_person_gender");
			
			String birth2 = obj.isNull("stay_person_birth") ? null : obj.getString("stay_person_birth");
			Date stay_person_birth;
			if(birth2!=null && birth2.length()!=0) {
				stay_person_birth = new SimpleDateFormat("yyyy-MM-dd").parse(birth2);
			} else 
				stay_person_birth = null;
			
			String stay_person_national_id = obj.isNull("stay_person_national_id") ? null : obj.getString("stay_person_national_id");
			String stay_person_phone = obj.isNull("stay_person_phone") ? null : obj.getString("stay_person_phone");
			String stay_person_Email = obj.isNull("stay_person_Email") ? null : obj.getString("stay_person_Email");

			Date reservation_status_date;
			String status_date = obj.isNull("reservation_status_date") ? null : obj.getString("reservation_status_date");
			if(status_date!=null && status_date.length()!=0) {
				reservation_status_date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(status_date);
			} else 
				reservation_status_date = null;
			
			String reservation_status = obj.isNull("reservation_status") ? null : obj.getString("reservation_status");
			String remark = obj.isNull("remark") ? null : obj.getString("remark");
			String cancellation_reason = obj.isNull("cancellation_reason") ? null : obj.getString("cancellation_reason");
			Optional<OrderRoom> op = orderRoomRepo.findById(id);
			if (op.isPresent()) {
				OrderRoom order = op.get();
				if (transaction_password!=null) {
					order.setTransactionPassword(transaction_password);
				}
				if (credit_card!=null) {
					order.setCreditCard(credit_card);
				}
				if (stay_person_name!=null) {
					order.setSPName(stay_person_name);
				}
				if (stay_person_birth!=null) {
					order.setSPBirth(stay_person_birth);
				}
				if (stay_person_gender!=null) order.setSPGender(stay_person_gender);
				if (stay_person_national_id!=null) order.setSPNationId(stay_person_national_id);
				
				if (stay_person_phone!=null) order.setSPPhone(stay_person_phone);
				if (stay_person_Email!=null) order.setSPEmail(stay_person_Email);
				
				if (remark!=null) order.setRemark(remark);
				if (reservation_status!=null) order.setReservationStatus(reservation_status);
				if (reservation_status_date!=null) order.setReservationSaDate(reservation_status_date);
				
				if (cancellation_reason!=null) order.setCancellReason(cancellation_reason);
				return order;
			}
		} catch (JSONException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	// 新增訂單
	public OrderRoom create(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			Date birth1;
			String order_person_name, gender, national_id, email, phone_number, credit_card, transaction_password;
			Member member=null;
			Integer id = obj.isNull("member_id") ? null : obj.getInt("member_id");
			// 非會員
			if (id == null) {			
				order_person_name = obj.isNull("order_person_name") ? null : obj.getString("order_person_name");
				gender = obj.isNull("gender") ? null : obj.getString("gender");
				
				String birth = obj.isNull("birth") ? null : obj.getString("birth");
				
				if(birth!=null && birth.length()!=0) {
					birth1 = new SimpleDateFormat("yyyy-MM-dd").parse(birth);
				} else 
					birth1 = null;
				
				national_id = obj.isNull("national_id") ? null : obj.getString("national_id");
				email = obj.isNull("email") ? null : obj.getString("email");
				phone_number = obj.isNull("phone_number") ? null : obj.getString("phone_number");
				credit_card = obj.isNull("credit_card") ? null : obj.getString("credit_card");
				transaction_password = obj.isNull("transaction_password") ? null : obj.getString("transaction_password");

//			.put("member_id", order.getMember().getMemberId())

				
			} else {
				member = memberservice.findbyId(id);
				national_id = member.getNationId();
				order_person_name = member.getMemberName();
				gender = member.getGender();
				birth1 = member.getBirth();
				email = member.getEmail();
				phone_number = member.getPhoneNumber();
				credit_card = member.getCreditCard();
				transaction_password = member.getPassword();
			}
			Integer adult_pax = obj.isNull("adult_pax") ? null : obj.getInt("adult_pax");
			Integer child_pax = obj.isNull("child_pax") ? null : obj.getInt("child_pax");
			Integer room_type_amount = obj.isNull("room_type_amount") ? null : obj.getInt("room_type_amount");
			
			System.out.println("room_type_amount"+ room_type_amount);
			String arrival_date = obj.isNull("arrival_date") ? null : obj.getString("arrival_date");
			Date arrivalDate;
			if (arrival_date != null && arrival_date.length()!=0) {
				arrivalDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(arrival_date);
			}else {
				arrivalDate = null;
			}
			
			
			String checkout_date = obj.isNull("checkout_date") ? null : obj.getString("checkout_date");
			Date checkoutDate;
			if (checkout_date != null && checkout_date.length()!=0) {
				checkoutDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(checkout_date);
			}else {
				checkoutDate = null;
			}
			// 用預設
//			String reservation_status = obj.isNull("reservation_status") ? null : obj.getString("reservation_status");

			Integer base_price = obj.isNull("base_price") ? null : obj.getInt("base_price");
			BigDecimal basePrice = new BigDecimal(base_price) ;
			
			// 判斷是訂房和住房是否為同一人
//			Boolean isSamePerson = obj.isNull("isSamePerson") ? false : obj.getBoolean("isSamePerson");
			Date stay_person_birth;
			String stay_person_name, stay_person_gender, stay_person_national_id,stay_person_phone, stay_person_Email;
//		if (!isSamePerson) {
			stay_person_name = obj.isNull("stay_person_name") ? null : obj.getString("stay_person_name");
			stay_person_gender = obj.isNull("stay_person_gender") ? null : obj.getString("stay_person_gender");
			
			String birth2 = obj.isNull("stay_person_birth") ? null : obj.getString("stay_person_birth");
			
			if(birth2!=null && birth2.length()!=0) {
				stay_person_birth = new SimpleDateFormat("yyyy-MM-dd").parse(birth2);
			} else 
				stay_person_birth = null;
			
			stay_person_national_id = obj.isNull("stay_person_national_id") ? null : obj.getString("stay_person_national_id");
			stay_person_phone = obj.isNull("stay_person_phone") ? null : obj.getString("stay_person_phone");
			stay_person_Email = obj.isNull("stay_person_Email") ? null : obj.getString("stay_person_Email");
//		}
			String remark = obj.isNull("remark") ? null : obj.getString("remark");
			Date orderdate = new Date();
			Date reservation_status_date = new Date();
			if (national_id !=null && gender!=null && order_person_name!=null && birth1!=null && email!=null && phone_number!=null && adult_pax!=null && room_type_amount!=null && transaction_password!=null) {
				OrderRoom order = new OrderRoom();
				order.setCustomerName(order_person_name);
				order.setNationId(national_id);
				order.setGender(gender);
				order.setBirth(birth1);
				order.setEmail(email);
				order.setPhone(phone_number);
				order.setCreditCard(credit_card);
				order.setTransactionPassword(transaction_password);
				order.setAdultPax(adult_pax);
				order.setChildPax(child_pax);
				order.setRoomAmount(room_type_amount);
				order.setArrivateDate(arrivalDate);
				order.setCheckoutDate(checkoutDate);
				order.setReservationStatus("Awaiting payment");
				order.setBasePrice(basePrice);
				order.setSPName(stay_person_name);
				order.setSPBirth(stay_person_birth);
				order.setSPEmail(stay_person_Email);
				order.setSPGender(stay_person_gender);
				order.setSPNationId(stay_person_national_id);
				order.setSPPhone(stay_person_phone);
				order.setOrderDate(orderdate);
				order.setReservationSaDate(reservation_status_date);
				order.setRemark(remark);
				if (id!=null) {
					order.setMember(member);
				}
				// 新增訂單
				Transaction trans = new Transaction();
				
				OrderRoom newOrder = orderRoomRepo.save(order);
				trans.setAmount(basePrice);
				trans.setOrderRoom(newOrder);
				trans.setTransactionStatus("尚未完成付款");
				transactionService.insert(trans);
				
				
				return newOrder;

				// 新增 orderdetail
				// 調整roomAssint

			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public OrderRoom inquireLogin(String email, String password) {
		if (EmailValidator.isValidEmail(email)) {
			Optional<OrderRoom> option = orderRoomRepo.findByEmail(email);
			if (option.isPresent()) {
				OrderRoom order = option.get();
				String origin = order.getTransactionPassword();
				if (origin.equals(password)) {
					return order;
				}
			}
		}
		return null;
		
	}
	
	public boolean existById(Integer id) {
		if (id!=null) {
			return orderRoomRepo.existsById(id);
		}
		return false;
	}
	// 找出會員的訂單
	public Page<OrderRoom> findMemberOrderByPage(Integer pageNumber, Integer memberId, Integer dataNumber){
		Pageable pgb = PageRequest.of(pageNumber-1, dataNumber, Sort.Direction.DESC, "orderDate");
		Page<OrderRoom> page = orderRoomRepo.findMemberOrder(pgb, memberId);
		
		return page;
	}
	public Long memberFindTotal(Integer memberId) {
		return orderRoomRepo.findMemberOrderTotal(memberId);
	}
	
	public Integer findLatestOrderByName(String name) {
		Integer id = orderRoomRepo.findLatestByOrderName(name);
		if (id!=null) {
			return id;
		}
		return null;
	}
	
	public Integer findLatestOrderByEmail(String email) {
		Integer id = orderRoomRepo.findLatestByOrderEmail(email);
		if (id!=null) {
			return id;
		}
		return null;
	}
	
	public String findOrderInformation(Integer orderId) {
		String line = orderRoomRepo.findorderInformation(orderId);
		if (line!=null) {
//			Map entity = (Map)line;
//			System.out.println(entity);
			return line;
		}
		return null;
	}
	
	public void sendConfirmMail(String recrvier, String subject, String context) throws Exception {
	        SimpleMailMessage message = new SimpleMailMessage();
	        message.setFrom("Relax Hotel<kawhi060941@gmail.com>");
	        message.setTo("r10522805@g.ntu.edu.tw");//recrvier
	        message.setSubject(subject);
	        message.setText(context);

	        javaMailSender.send(message);
	    }
	
}
