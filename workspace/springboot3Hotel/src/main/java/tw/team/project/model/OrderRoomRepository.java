package tw.team.project.model;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRoomRepository extends JpaRepository<OrderRoom, Integer>{

	@Query("from OrderRoom ord where ord.member.memberId is null")
	Page<OrderRoom> findCustomer(org.springframework.data.domain.Pageable pgb);
}
