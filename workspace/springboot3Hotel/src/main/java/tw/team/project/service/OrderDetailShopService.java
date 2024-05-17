package tw.team.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.team.project.model.Order;
import tw.team.project.model.OrderDeatilRepository;
import tw.team.project.model.OrderDetail;

@Service
public class OrderDetailShopService {
	
	@Autowired
	private OrderDeatilRepository orderDeatilRepository;
	
	public List<OrderDetail> findfindByorderId(Order order){
		return orderDeatilRepository.findByorderId(order);
	}
	

}
