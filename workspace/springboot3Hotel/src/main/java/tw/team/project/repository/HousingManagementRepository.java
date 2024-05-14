package tw.team.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tw.team.project.dao.HousingManagementDAO;
import tw.team.project.model.HousingManagement;

@Repository
public interface HousingManagementRepository extends JpaRepository<HousingManagement, Integer> ,HousingManagementDAO{
//    @Query("SELECT COUNT(r) FROM HousingManagement r WHERE r.number = :number")
//    long countByNumber(@Param("number") Integer number);
	
	@Query("select count(*) from HousingManagement c where c.id = :id")
	public long countById(Integer id);
	
	
}
