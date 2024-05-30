package tw.team.project.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import tw.team.project.dto.MemberOrdersDTO;
import tw.team.project.model.OrderRoom;
import tw.team.project.service.OrderRoomService;
import tw.team.project.util.JsonContainer;

@RestController
@RequestMapping("/hotel")
@CrossOrigin
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
				array.put(new JsonContainer().getOrderRoom(order));
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
				array.put(new JsonContainer().getOrderRoom(order));
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
//		Integer id = null;
		JSONObject responseJson = new JSONObject();
		OrderRoom orderRoom= ordRoomService.create(json);
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
				array.put(new JsonContainer().getOrderRoom(order));
			}
			responseJson.put("data", array);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return responseJson.toString();
	}
	@DeleteMapping("/orderRoom/{pk}")
	public String deleteById(@PathVariable("pk") Integer id) {
		JSONObject responseJson = new JSONObject();
		boolean result = ordRoomService.deleteOrder(id);
		try {
			if (result) {
			    responseJson.put("success", true);
			    responseJson.put("message", "刪除成功");
			}else {
			    responseJson.put("success", false);
			    responseJson.put("message", "刪除失敗");
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return responseJson.toString();
	}
	
	@PutMapping("/orderRoom/alert/{pk}")
	public String updateData(@PathVariable("pk") Integer id, @RequestBody String json) {
		JSONObject responseJson = new JSONObject();		
		try {
			if (id != null) {
				if (ordRoomService.update(id, json) != null) {
					responseJson.put("message", "更新成功");
					responseJson.put("success", true);
				} else {
					responseJson.put("message", "請查資料是否完整");
					responseJson.put("success", false);
				}
			}else {
				responseJson.put("message", "資料已過期請重新登入");
				responseJson.put("success", false);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return responseJson.toString();
	}
	
	@PostMapping("/orderRoom/InquireOrder")
	public String login(@RequestBody String json, HttpSession httpSession) throws JSONException{
		JSONObject responseJson = new JSONObject();
		JSONObject obj = new JSONObject(json);
		
		String email = obj.isNull("email") ? null : obj.getString("email");
		String password = obj.isNull("password") ? null : obj.getString("password");
        if (email == null || email.length()==0) {
        	responseJson.put("success", false);
            responseJson.put("acount", "email是必要欄位");
		}
        if (password == null || password.length()==0) {
            responseJson.put("success", false);
            responseJson.put("password", "交易密碼是必要欄位");
		}
        if (email != null && password != null && email.length()!=0 && password.length()!=0) {
        	OrderRoom order = ordRoomService.inquireLogin(email, password);
        	if (order != null) {
                responseJson.put("success", true);
                responseJson.put("message", "登入成功");
                httpSession.setAttribute("customer", order.getEmail());
                httpSession.setAttribute("orderId", order.getOrderId());
        	} else {
                responseJson.put("success", false);
                responseJson.put("message", "信箱或交易密碼有錯");
        	}
        }
        
        return responseJson.toString();
	}
	
	// 新增交易記錄
	
	// 會員查詢紀錄
	@GetMapping("/members/OrderRooms/{pk}")
	public ResponseEntity<?> findOrderRoom(@RequestParam(value = "p",defaultValue = "1") Integer pageNumber, @RequestParam(value = "num",defaultValue = "4") Integer dataNmuber, @PathVariable("pk") Integer id){
		Page<OrderRoom> page = ordRoomService.findMemberOrderByPage(pageNumber, id, dataNmuber);
		List<MemberOrdersDTO> memberOrders = new ArrayList<>();
		System.out.println(page.getNumber());
		System.out.println(page.getTotalPages());
		if (!page.isEmpty()) {
			for (OrderRoom order : page.getContent()) {
				memberOrders.add(new JsonContainer().setMemberOrders(order));
			}
			return ResponseEntity.ok(memberOrders);
		}
		return ResponseEntity.notFound().build();
	}
	@GetMapping("/members/OrderRooms/totals/{pk}")
	public String findTotal(@PathVariable("pk") Integer id){
		JSONObject obj = new JSONObject();
		try {
			if (id!=null) {
				Long total = ordRoomService.memberFindTotal(id);
				obj.put("total", total);
				obj.put("success", true);
			}else {
				obj.put("message", "Id為null");
				obj.put("success", false);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj.toString();
	}
	@GetMapping("/orderRoom/latest/{name}")
	public String findLatestByName(@PathVariable("name") String name) {
		JSONObject obj = new JSONObject();
		try {
			if (name!=null && name.length()!=0) {
				Integer orderId = ordRoomService.findLatestOrderByName(name);
				if (orderId!=null) {
					obj.put("orderId", orderId);
					obj.put("success", true);
				}
			}else {
				obj.put("success", false);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return obj.toString();
	}
}
