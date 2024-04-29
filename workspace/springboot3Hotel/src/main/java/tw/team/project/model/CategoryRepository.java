package tw.team.project.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
	
	@Query("from Category where name = :n")
	Category findByCategoryName(@Param("n") String name);

}
