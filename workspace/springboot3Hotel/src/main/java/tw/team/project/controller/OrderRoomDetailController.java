package tw.team.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tw.team.project.model.OrderRoomDetail;
import tw.team.project.service.OrderRoomDetailService;

@RestController
@RequestMapping("/hotel")
public class OrderRoomDetailController {

	@Autowired
	private OrderRoomDetailService orderRoomDetailService;
	
	@GetMapping("/orderRoom/detail/{pk}")
	public ResponseEntity<?> findDetailByOrderId(@RequestParam(value = "p", defaultValue = "1")Integer pageNumber, @PathVariable("pk")Integer orderId){
		Page<OrderRoomDetail> page = orderRoomDetailService.findByOrderId(pageNumber, orderId);
		return ResponseEntity.ok(page.get());
	}
	
}
