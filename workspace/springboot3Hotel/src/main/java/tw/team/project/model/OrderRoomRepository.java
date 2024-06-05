package tw.team.project.model;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tw.team.project.dto.LinePayDTO;


public interface OrderRoomRepository extends JpaRepository<OrderRoom, Integer>{

	@Query("from OrderRoom ord where ord.member.memberId is null")
	Page<OrderRoom> findCustomer(org.springframework.data.domain.Pageable pgb);
	
	@Query("from OrderRoom ord where ord.email = :email and ord.reservationStatus != 'Check-Out' ORDER BY ord.orderDate DESC LIMIT 1")
	public Optional<OrderRoom> findByEmail(String email);
	
	@Query(value = "select * from orderRoom room where room.member_id = :memberId",nativeQuery = true) // "from OrderRoom ord where ord.member.memberId = :memberId"
	Page<OrderRoom> findMemberOrder(org.springframework.data.domain.Pageable pgb, @Param("memberId") Integer id);

	@Query("select count(*) from OrderRoom ord where ord.member.memberId = :memberId") // "from OrderRoom ord where ord.member.memberId = :memberId"
	public Long findMemberOrderTotal(@Param("memberId") Integer id);
	
	@Query(value = "select top(1) order_id from orderRoom ordR where order_person_name = :name order by orderdate desc",nativeQuery = true)
	public Integer findLatestByOrderName(@Param("name") String name);
	
	@Query(value = "select top(1) order_id from orderRoom ordR where email = :email order by orderdate desc",nativeQuery = true)
	public Integer findLatestByOrderEmail(@Param("email") String email);
	
	@Query(value = "select ordR.base_price, ordRD.room_amount, ordR.order_id, roin.bed_type, roIn.room_price, roin.room_type_id, traT.transaction_status, ordR.arrival_date, ordR.checkout_date, roIn.room_photo from orderRoom ordR left join orderRoomDetail ordRD on ordR.order_id = ordRD.order_id left join roomInformation roIn on ordRD.room_information_id = roin.room_information_id left join transactionTable traT on ordR.order_id = traT.order_id where ordR.order_id= :orderId",nativeQuery = true)
	public String findorderInformation(@Param("orderId") Integer id);
}
//room.orderdate, room.adult_pax, room.child_pax, room.room_type_amount,  room.arrival_date, room.checkout_date, room.reservation_status, room.reservation_status_date,room.base_price