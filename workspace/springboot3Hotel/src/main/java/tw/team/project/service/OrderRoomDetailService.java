package tw.team.project.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import tw.team.project.model.OrderRoom;
import tw.team.project.model.OrderRoomDetail;
import tw.team.project.model.OrderRoomDetailId;
import tw.team.project.model.OrderRoomRepository;
import tw.team.project.repository.OrderRoomDetailRepository;

@Service
public class OrderRoomDetailService {

	@Autowired
	private OrderRoomDetailRepository detailRepository;
	@Autowired
	private OrderRoomRepository orderRoomRepository;
	
	public OrderRoomDetail findById(OrderRoomDetailId detailId) {
		Optional<OrderRoomDetail> optional = detailRepository.findById(detailId);
		if (optional.isEmpty()) {
			return null;
		}else {
			return optional.get();
		}
	}
	
	public Page<OrderRoomDetail> findByOrderId(Integer pageNumber, Integer orderId){
		if (pageNumber!=null && pageNumber>0 && orderId!=null && orderId>0) {
			Pageable pgb = PageRequest.of(pageNumber-1, 5, Sort.Direction.ASC,"orderId");
			Page<OrderRoomDetail> page = detailRepository.findOrderDetail(orderId, pgb);
			return page;
		}
		return null;
	}
	
	// 查看訂單是否存在，不存在就建立
	public boolean isExists(OrderRoomDetail ordetail) {
		OrderRoomDetailId ordetailId = ordetail.getId();
		Optional<OrderRoomDetail> optional = detailRepository.findById(ordetailId);
		if(optional.isEmpty()) {
			detailRepository.save(ordetail);
			return true;
		}
		return false;
	}
	
	@Transactional
	public OrderRoomDetail addRoom(OrderRoomDetail ordetail) {
		OrderRoomDetailId ordetailId = ordetail.getId();
		Optional<OrderRoomDetail> optional = detailRepository.findById(ordetailId);
		Optional<OrderRoom> optional2 = orderRoomRepository.findById(ordetailId.getOrderId());
		OrderRoom originOrder = optional2.get();
		if (optional.isPresent()) {
			OrderRoomDetail originOrdetail = optional.get();
			originOrdetail.setPrice(originOrdetail.getPrice().add(ordetail.getPrice()));
			originOrdetail.setRoomAmount(originOrdetail.getRoomAmount()+ordetail.getRoomAmount());
			originOrder.setRoomAmount(originOrder.getRoomAmount()+ordetail.getRoomAmount());
			return originOrdetail;
		}else {
			originOrder.setRoomAmount(originOrder.getRoomAmount()+ordetail.getRoomAmount());
			originOrder.setBasePrice(originOrder.getBasePrice().add(ordetail.getPrice()));
			return detailRepository.save(ordetail);
		}
	}
	
	@Transactional
	public OrderRoomDetail update(OrderRoomDetail ordetail) {
		if (ordetail!=null) {
			Optional<OrderRoomDetail> optional = detailRepository.findById(ordetail.getId());
			Optional<OrderRoom> optional2 = orderRoomRepository.findById(ordetail.getId().getOrderId());
			if (optional.isPresent()) {
				OrderRoomDetail originOrdetail = optional.get();
				OrderRoom originOrder = optional2.get();
				originOrder.setBasePrice(originOrder.getBasePrice().add(ordetail.getPrice().subtract(originOrdetail.getPrice())));
				originOrder.setRoomAmount(originOrder.getRoomAmount()+(ordetail.getRoomAmount()-originOrdetail.getRoomAmount()));
				return detailRepository.save(ordetail);
			}
			
		}
		return null;
	}
}
