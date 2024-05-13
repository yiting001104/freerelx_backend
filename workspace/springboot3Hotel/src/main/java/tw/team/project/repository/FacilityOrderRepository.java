package tw.team.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.team.project.model.FacilityOrder;

public interface FacilityOrderRepository extends JpaRepository<FacilityOrder, Integer> {

}
