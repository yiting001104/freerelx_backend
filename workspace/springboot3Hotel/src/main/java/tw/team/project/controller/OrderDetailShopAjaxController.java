package tw.team.project.controller;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.team.project.model.Order;
import tw.team.project.model.OrderDetail;
import tw.team.project.service.OrderDetailShopService;

@RestController
@RequestMapping("hotel")
@CrossOrigin
public class OrderDetailShopAjaxController {
	@Autowired
	private OrderDetailShopService orderDetailShopService;
	
	@GetMapping("/orderdetails/mes/{pk}")
	public String findById(@PathVariable(name = "pk") Order order) throws JSONException {
		JSONObject responseJson = new JSONObject();
		JSONArray array = new JSONArray();
		List<OrderDetail> orderdetails= orderDetailShopService.findfindByorderId(order);
		if (orderdetails != null && !orderdetails.isEmpty()) {
			for (OrderDetail orderdetail : orderdetails) {
				JSONObject item = new JSONObject()
						.put("Quantity",orderdetail.getQuantity())
						.put("price", orderdetail.getProductmultiplequantity())
						.put("ProductName", orderdetail.getProduct().getProductName());
				array.put(item);
			}
		}
		responseJson.put("ist", array);
		return responseJson.toString();
	}

}
