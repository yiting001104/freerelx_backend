package tw.team.project.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tw.team.project.model.OrderRoomDetail;
import tw.team.project.model.OrderRoomDetailId;

public interface OrderRoomDetailRepository extends JpaRepository<OrderRoomDetail, OrderRoomDetailId>{

	@Query("from OrderRoomDetail ord where ord.orderRoomDetailId.orderId = :orderId")
	public Page<OrderRoomDetail> findOrderDetail(@Param("orderId") Integer orderId,Pageable pgb);
}
//