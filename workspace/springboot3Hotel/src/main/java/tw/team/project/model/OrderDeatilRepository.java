package tw.team.project.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderDeatilRepository extends JpaRepository<OrderDetail,Integer>{

	@Query("from OrderDetail where order = :n")
	List<OrderDetail> findByorderId(@Param("n")Order order);
}
