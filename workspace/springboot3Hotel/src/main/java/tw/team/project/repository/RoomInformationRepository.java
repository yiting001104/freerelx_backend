package tw.team.project.repository;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import tw.team.project.model.RoomInformation;

public interface RoomInformationRepository extends JpaRepositoryImplementation<RoomInformation, Integer> {

//	@Query("from Member where id = :id")
//	public Optional<RoomInformation> findById(Integer id);
}
