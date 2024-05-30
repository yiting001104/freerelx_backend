package tw.team.project.model;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRoomRepository extends JpaRepository<OrderRoom, Integer>{

	@Query("from OrderRoom ord where ord.member.memberId is null")
	Page<OrderRoom> findCustomer(org.springframework.data.domain.Pageable pgb);
	
	@Query("from OrderRoom ord where ord.email = :email and ord.reservationStatus != 'Check-Out'")
	public Optional<OrderRoom> findByEmail(String email);
	
	@Query(value = "select * from orderRoom room where room.member_id = :memberId",nativeQuery = true) // "from OrderRoom ord where ord.member.memberId = :memberId"
	Page<OrderRoom> findMemberOrder(org.springframework.data.domain.Pageable pgb, @Param("memberId") Integer id);

	@Query("select count(*) from OrderRoom ord where ord.member.memberId = :memberId") // "from OrderRoom ord where ord.member.memberId = :memberId"
	public Long findMemberOrderTotal(@Param("memberId") Integer id);
	
	@Query(value = "select top(1) order_id from orderRoom ordR where order_person_name = :name order by orderdate desc",nativeQuery = true)
	public Integer findLatestByOrderName(@Param("name") String name);
}
//room.orderdate, room.adult_pax, room.child_pax, room.room_type_amount,  room.arrival_date, room.checkout_date, room.reservation_status, room.reservation_status_date,room.base_price