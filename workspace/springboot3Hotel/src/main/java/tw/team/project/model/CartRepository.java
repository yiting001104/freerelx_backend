package tw.team.project.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartRepository extends JpaRepository<Cart, CartId>{
	
//	@Query("from Cart where name = :n")
//	Category findByMemberAndProduct(@Param("n") String name);
}
