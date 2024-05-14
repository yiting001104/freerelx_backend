package tw.team.project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tw.team.project.model.RoomType;

@Repository
public interface RoomTypeRepository extends JpaRepositoryImplementation<RoomType, Integer> {
	
	@Query("from RoomType where id = :id")
	public Optional<RoomType> findById(@Param("id") Integer id);

}
