package tw.team.project.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tw.team.project.model.AdditionalCharges;
import tw.team.project.model.AdditionalChargesId;

@Repository
public interface AdditionalChargesRepository extends JpaRepository<AdditionalCharges, AdditionalChargesId> {

//    @Query("SELECT new map(ac.additionalChargesId.minibarId as minibarId, m.price as price, m.item as item, ac.additionalChargesId.housingManagementId as housingManagementId) FROM AdditionalCharges ac JOIN ac.minibar m JOIN ac.housingManagement h WHERE ac.additionalChargesId = :id")
//    public Object findAdditionalChargesDetailsById(AdditionalChargesId id);
	
//	@Query("from Product where productName = :n")
//	Product findByProductName(@Param("n") String name);
	
	

}
