package tw.team.project.repository;


import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tw.team.project.dao.RoomAssignmentDAO;
import tw.team.project.model.RoomAssignment;

@Repository
public interface RoomAssignmentRepository extends JpaRepository<RoomAssignment, Integer>, RoomAssignmentDAO{
	
    @Query("select count(*) from RoomAssignment where date = :date")
    public long countByDate(@Param("date") Date date);

    @Query("from RoomAssignment d where d.date = :date")
    public Optional<RoomAssignment> findByDate(@Param("date") Date date);

    @Query("select count(distinct ra.date) from RoomAssignment ra where ra.date between :startDate and :endDate and ra.roomInformation.id = :roomId and ra.left > 0")
    long countAvailableDaysInRange(@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("roomId") Integer roomId);

    @Query("from RoomAssignment ra where ra.date between :startDate and :endDate and ra.roomInformation.id = :roomId and ra.left > 0")
    List<RoomAssignment> findAvailableAssignmentsInRange(@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("roomId") Integer roomId);

    @Query("select min(ra.left) from RoomAssignment ra where ra.date between :startDate and :endDate and ra.roomInformation.id = :roomId and ra.left > 0")
    Integer findMinLeftInRange(@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("roomId") Integer roomId);
	
	@Query("from RoomAssignment d where d.date = :date and d.roomInformation.id = :id")
	public Optional<RoomAssignment> findByDateOrd(@Param("date")Date date, @Param("id")Integer id);
	
//	Optional<RoomAssignment> findAllByDate(Date date);
}
