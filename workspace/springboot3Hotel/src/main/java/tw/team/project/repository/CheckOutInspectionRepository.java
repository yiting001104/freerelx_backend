package tw.team.project.repository;

import java.util.List;

import org.springdoc.core.converters.models.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import tw.team.project.model.CheckOutInspection;

public interface CheckOutInspectionRepository extends JpaRepository<CheckOutInspection, Integer> {

	 @Query("select coi.id as id, coi.compensation as compensation, coi.fee as fee, coi.photo as photo from CheckOutInspection coi where coi.id = :id")
	    Page<CheckOutInspection> findById(Integer id, org.springframework.data.domain.Pageable pageable);
	 
	 public interface CheckOutInspection extends JpaRepository<CheckOutInspection, Long>, JpaSpecificationExecutor<CheckOutInspection> {
		    List<CheckOutInspection> findAll(Sort sort);
		}
}


