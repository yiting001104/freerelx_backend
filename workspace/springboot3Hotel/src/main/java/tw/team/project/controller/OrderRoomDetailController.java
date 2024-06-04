package tw.team.project.controller;

import java.net.URI;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tw.team.project.model.OrderRoomDetail;
import tw.team.project.service.OrderRoomDetailService;
import tw.team.project.service.OrderRoomService;

@RestController
@RequestMapping("/hotel")
@CrossOrigin
public class OrderRoomDetailController {
	@Value("${local.serverPort}")
	private String serverUri;
	
	@Autowired
	private OrderRoomDetailService orderRoomDetailService;
	
	@Autowired
	private OrderRoomService orderRoomService;
	
	// 顧客查詢訂單明細
	@GetMapping("/orderRooms/detail/{pk}")
	public ResponseEntity<?> findDetailByOrderId(@RequestParam(value = "p", defaultValue = "1")Integer pageNumber, @PathVariable("pk")Integer orderId){
		Page<OrderRoomDetail> page = orderRoomDetailService.findByOrderId(pageNumber, orderId);
		return ResponseEntity.ok(page.get());
	}
	
	// 顧客新增訂單明細
	@PostMapping("/orderRooms/detail")
	public ResponseEntity<?> insertOrderDetail(@RequestBody OrderRoomDetail ordetail){
		if (ordetail!=null) {
			if (orderRoomService.existById(ordetail.getId().getOrderId())) {
				OrderRoomDetail newOrderDetail = orderRoomDetailService.addRoom(ordetail);
				String uri = serverUri+"/hotel/orderRooms/detail/"+newOrderDetail.getId().getOrderId();
				return ResponseEntity.created(URI.create(uri)).contentType(MediaType.APPLICATION_JSON).body(newOrderDetail);
			}
		}
		return ResponseEntity.notFound().build();
	}
	
	// 後台修改訂單明細
	@PutMapping("/orderRooms/detail/{opk}-{rpk}")
	public ResponseEntity<?> modify(@PathVariable("opk") Integer orderId, @PathVariable("rpk") Integer roomInID, @RequestBody OrderRoomDetail ordetail){
		if (ordetail!=null && ordetail.getId()!=null) {
			if (ordetail.getId().getOrderId()==orderId && ordetail.getId().getRoomInformationId()==roomInID) {
				OrderRoomDetail newOrdetail =  orderRoomDetailService.update(ordetail);
				return ResponseEntity.ok(newOrdetail);
				
			}
		}
		return ResponseEntity.notFound().build();
	}
	@PostMapping("/orderRooms/detailBuild")
	public String isExists(@RequestBody OrderRoomDetail ordetail){
		JSONObject responseJson = new JSONObject();
		if (ordetail!=null) {
			boolean result = orderRoomDetailService.isExists(ordetail);
			if (result) {
				responseJson.put("message", "訂單已建立成功");
				responseJson.put("success", true);
			}else {
				responseJson.put("message", "訂單無法建立");
				responseJson.put("success", false);
			}
		}else {
			responseJson.put("message", "ordetail為null");
			responseJson.put("success", false);
		}
		return responseJson.toString();
	}
	
	
	
}
