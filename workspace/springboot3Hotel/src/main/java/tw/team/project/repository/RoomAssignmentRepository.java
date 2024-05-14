package tw.team.project.repository;

import java.sql.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tw.team.project.dao.RoomAssignmentDAO;
import tw.team.project.model.RoomAssignment;

@Repository
public interface RoomAssignmentRepository extends JpaRepository<RoomAssignment, Integer>, RoomAssignmentDAO{
	
	@Query("select count(*) from RoomAssignment where date = :date")
	public long countByDate(@Param("date")Date date);
	
	@Query("from RoomAssignment d where d.date = :date")
	public Optional<RoomAssignment> findByDate(@Param("date")Date date);
	
//	Optional<RoomAssignment> findAllByDate(Date date);
}
