package tw.team.project.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartRepository extends JpaRepository<Cart, CartId> {

	@Query("from Cart c where c.cartId.memberId = :memberId")
    List<Cart> findByMemberIdCart(@Param("memberId") Integer memberId);
	
	@Query("from Cart c where c.cartId.memberId = :memberId and c.checkout = true")
	List<Cart> findByMemberIdAndcheckout(@Param("memberId") Integer memberId);
	
	@Query("from Cart c where c.cartId.id = :id")
    List<Cart> findByidCart(@Param("id") Integer id);
}
