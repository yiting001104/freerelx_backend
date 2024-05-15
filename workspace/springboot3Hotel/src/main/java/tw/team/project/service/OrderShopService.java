package tw.team.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.team.project.model.Member;
import tw.team.project.model.Order;
import tw.team.project.model.OrderRepository;

@Service
public class OrderShopService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	public List<Order> findfindBymemberId(Member member){
		return orderRepository.findBymemberId(member);
	}
	
}
