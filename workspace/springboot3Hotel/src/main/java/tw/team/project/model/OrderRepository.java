package tw.team.project.model;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<Order, Integer>{
	
	@Query("from Order as o where o.member = :n  ORDER By o.addedTime Desc")
	List<Order> findBymemberId(@Param("n") Member member);
	
	@Query("from Order as o where o.orderstatus = :n  ")
	List<Order> findByAllnotarrival(@Param("n") String string);
	
	@Query("from Order as o where o.orderstatus = :n  ")
	List<Order> findByAllisarrival(@Param("n") String string);
}