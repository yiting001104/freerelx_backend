package tw.team.project.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tw.team.project.dao.ProductDAO;

public interface ProductRepository extends JpaRepository<Product, Integer>, ProductDAO{
	
	@Query("from Product where productName = :n")
	Product findByProductName(@Param("n") String name);
	
	@Query("from Product where productSupplierId = :n")
	List<Product> findByowner(@Param("n") Supplier productSupplierId);
	
	@Query("select count(p) from Product p where p.productSupplierId = :n")
	Long countByOwner(@Param("n") Supplier productSupplierId);
}
