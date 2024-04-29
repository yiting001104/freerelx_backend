package tw.team.project.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Integer>{
	
	@Query("from Product where productName = :n")
	Product findByProductName(@Param("n") String name);
 
}
