package tw.team.project.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductphotoRepository extends JpaRepository<Productphoto, Integer> {

	@Query(" from Productphoto where productid = :n ")
	List<Productphoto> findByproductid(@Param("n") Product productid);

}
