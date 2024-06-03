package tw.team.project.controller;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
		List<Order> orders= orderShopService.findfindBymemberId(member);
		if (orders != null && !orders.isEmpty()) {
			for (Order order : orders) {
				JSONObject item = new JSONObject()
						.put("AddedTime",order.getAddedTime())
						.put("Orderstatus", order.getOrderstatus())
						.put("total", order.getTotal())
						.put("orderid", order.getId())
						.put("usebonus",order.getUsebonus())
						.put("addbonus", order.getAddbonus())
						.put("arriveddTime", order.getArriveddTime());
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
		Order order= orderShopService.findByid(id);
				JSONObject item = new JSONObject()
				.put("mes","hi");	
				array.put(item);
		responseJson.put("list", array);
		return responseJson.toString();
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////
	@GetMapping("/orders/orderAll/{pk}")
	public ResponseEntity<?> findById(@PathVariable(name = "pk") Integer id) throws JSONException {
		Order order= orderShopService.findByid(id);
		if (order!=null) {
			return ResponseEntity.ok(order);
		}
		return ResponseEntity.notFound().build();
	}
}