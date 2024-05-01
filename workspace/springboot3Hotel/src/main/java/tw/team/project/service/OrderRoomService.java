package tw.team.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import tw.team.project.model.OrderRoom;
import tw.team.project.model.OrderRoomRepository;

@Service
public class OrderRoomService {

	@Autowired
	private OrderRoomRepository orderRoomRepo;
	
	public Page<OrderRoom> findOrderByPage(Integer pageNumber){
		Pageable pgb = PageRequest.of(pageNumber-1, 4, Sort.Direction.DESC, "orderDate");
		Page<OrderRoom> page = orderRoomRepo.findAll(pgb);
		return page;
	}
	public Page<OrderRoom> findOrderCusByPage(Integer pageNumber){
		Pageable pgb = PageRequest.of(pageNumber-1, 4, Sort.Direction.DESC, "orderDate");
		Page<OrderRoom> page = orderRoomRepo.findCustomer(pgb);
		return page;
	}
}
