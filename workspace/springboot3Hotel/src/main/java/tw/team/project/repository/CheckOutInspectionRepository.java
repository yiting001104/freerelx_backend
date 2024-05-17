package tw.team.project.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tw.team.project.model.CheckOutInspection;

public interface CheckOutInspectionRepository extends JpaRepository<CheckOutInspection, Integer> {
	
//	@Query("select count(*) from CheckOutInspection c where c.id = :id")
//	public long countByItem(Integer id);
	
//	@Query("from CheckOutInspection c where c.id = :id")
//	public Optional<CheckOutInspection> findById(Integer id);
	
	 @Query("select coi.id as id, coi.compensation as compensation, coi.fee as fee, coi.photo as photo from CheckOutInspection coi where coi.id = :id")
	    Page<CheckOutInspection> findById(Integer id, org.springframework.data.domain.Pageable pageable);
}

