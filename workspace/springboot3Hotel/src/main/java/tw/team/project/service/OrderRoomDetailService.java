package tw.team.project.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import tw.team.project.model.OrderRoomDetail;
import tw.team.project.model.OrderRoomDetailId;
import tw.team.project.repository.OrderRoomDetailRepository;

@Service
public class OrderRoomDetailService {

	@Autowired
	private OrderRoomDetailRepository detailRepository;
	
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
			Pageable pgb = PageRequest.of(pageNumber-1, 5, Sort.Direction.ASC,"orderRoomDetailId.roInformation.id");
			Page<OrderRoomDetail> page = detailRepository.findOrderDetail(orderId, pgb);
			return page;
		}
		return null;
	}
}
