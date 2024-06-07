package tw.team.project.controller;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.team.project.model.Member;
import tw.team.project.model.Order;
import tw.team.project.model.OrderDetail;
import tw.team.project.service.OrderDetailShopService;
import tw.team.project.service.OrderShopService;

@RestController
@RequestMapping("hotel")
@CrossOrigin
public class OrderShopAjaxController {

	@Autowired
	private OrderShopService orderShopService;

	@Autowired
	private OrderDetailShopService orderDetailShopService;

//member
	@GetMapping("/orders/mes/{pk}")
	public String findById(@PathVariable(name = "pk") Member member) throws JSONException {
		JSONObject responseJson = new JSONObject();
		JSONArray array = new JSONArray();
		List<Order> orders = orderShopService.findfindBymemberId(member);
		if (orders != null && !orders.isEmpty()) {
			for (Order order : orders) {
				JSONObject item = new JSONObject().put("AddedTime", order.getAddedTime())
						.put("Orderstatus", order.getOrderstatus()).put("total", order.getTotal())
						.put("orderid", order.getId()).put("usebonus", order.getUsebonus())
						.put("addbonus", order.getAddbonus()).put("arriveddTime", order.getArriveddTime());
				array.put(item);
			}
		}
		responseJson.put("list", array);
		return responseJson.toString();
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////
//id	
	@GetMapping("/orders/order/{pk}")
	public String findId(@PathVariable(name = "pk") Integer id) throws JSONException {
		JSONObject responseJson = new JSONObject();
		JSONArray array = new JSONArray();
		Order order = orderShopService.findByid(id);
		JSONObject item = new JSONObject().put("mes", "hi");
		array.put(item);
		responseJson.put("list", array);
		return responseJson.toString();
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////
	@GetMapping("/orders/orderAll/{pk}")
	public ResponseEntity<?> findById(@PathVariable(name = "pk") Integer id) throws JSONException {
		Order order = orderShopService.findByid(id);
		if (order != null) {
			return ResponseEntity.ok(order);
		}
		return ResponseEntity.notFound().build();
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////
	// member真的成功
	@GetMapping("/orders/all/{pk}")
	public String findtest(@PathVariable(name = "pk") Member member) throws JSONException {
		JSONObject responseJson = new JSONObject();
		JSONArray array = new JSONArray();
		List<Order> orders = orderShopService.findfindBymemberId(member);
		if (orders != null && !orders.isEmpty()) {
			for (Order order : orders) {
				List<OrderDetail> od = orderDetailShopService.findfindByorderId(order);
				if (od != null && !od.isEmpty()) {
					for (OrderDetail ods : od) {
						JSONObject item = new JSONObject();
						item.put("ProductName", ods.getProduct().getProductName());
						array.put(item);
					}
				}
			}
		}
		responseJson.put("list", array);
		return responseJson.toString();
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////
	@GetMapping("/orders/orderAll/narrival")
	public String findByAllnot() throws JSONException {
		JSONObject responseJson = new JSONObject();
		JSONArray array = new JSONArray();
		List<Order> orders = orderShopService.findByAllnotarrival();
		if (orders != null && !orders.isEmpty()) {
			for (Order order : orders) {
				JSONObject item = new JSONObject().put("AddedTime", order.getAddedTime())
						.put("Orderstatus", order.getOrderstatus()).put("orderid", order.getId())
						.put("MemberName", order.getMemberName()).put("ContactAddress", order.getContactAddress())
						.put("PhoneNumber", order.getPhoneNumber());
				array.put(item);
			}
		}
		responseJson.put("list", array);
		return responseJson.toString();
	}

	@GetMapping("/orders/orderAll/isarrival")
	public String findByAllarrive() throws JSONException {
		JSONObject responseJson = new JSONObject();
		JSONArray array = new JSONArray();
		List<Order> orders = orderShopService.findByAllisarrival();
		if (orders != null && !orders.isEmpty()) {
			for (Order order : orders) {
				JSONObject item = new JSONObject().put("AddedTime", order.getAddedTime())
						.put("Orderstatus", order.getOrderstatus()).put("orderid", order.getId())
						.put("MemberName", order.getMemberName()).put("ContactAddress", order.getContactAddress())
						.put("PhoneNumber", order.getPhoneNumber());
				array.put(item);
			}
		}
		responseJson.put("list", array);
		return responseJson.toString();
	}
///////////////////////////////////////////////////////刪單
	@DeleteMapping("/orders/one/{pk}")
    public ResponseEntity<Void> remove(@PathVariable(name = "pk") Integer id) {
    	if(id!=null && id!=0) {
    		boolean exists = orderShopService.existById(id);
    		if(exists) {
    			if(orderShopService.delete(id)) {
    				return ResponseEntity.noContent().build();
    			}
    		}
    	}
 		return ResponseEntity.notFound().build();
    }
}
