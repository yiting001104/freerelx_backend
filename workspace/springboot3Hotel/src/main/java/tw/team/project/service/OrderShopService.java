package tw.team.project.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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
	
	public Order findByid(Integer id) {
		Order order=orderRepository.findById(id).get();
		order.setArriveddTime(new Date());
		order.setOrderstatus("訂單已抵達");
		return orderRepository.save(order);
		
	}
	
	public List<Order> findByAllnotarrival(){
		return orderRepository.findByAllnotarrival("訂單成立");
	}
	
	public List<Order> findByAllisarrival(){
		return orderRepository.findByAllisarrival("訂單已抵達");
	}
	// 6/5刪除單
	public boolean existById(Integer id) {
		if(id!=null) {
			return orderRepository.existsById(id);
		}
		return false;
	}
	public boolean delete(Integer id) {
		if(id!=null) {
			Optional<Order> optional = orderRepository.findById(id);
			if(optional.isPresent()) {
				orderRepository.deleteById(id);
				return true;
			}
		}
		return false;
	}
}