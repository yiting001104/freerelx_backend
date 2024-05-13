package tw.team.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.team.project.model.Facility;

public interface FacilityRepository extends JpaRepository<Facility, Integer> {

}
