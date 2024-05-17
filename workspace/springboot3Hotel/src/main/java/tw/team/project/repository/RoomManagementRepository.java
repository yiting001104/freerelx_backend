package tw.team.project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tw.team.project.dao.RoomManagementDAO;
import tw.team.project.model.RoomManagement;

@Repository
public interface RoomManagementRepository extends JpaRepository<RoomManagement, Integer>, RoomManagementDAO {
	@Query("SELECT COUNT(r) FROM RoomManagement r WHERE r.number = :number")
	long countByNumber(@Param("number") String number);

	@Query("from RoomManagement n where n.number = :number")
	public Optional<RoomManagement> findByNumber(@Param("number")Integer number);

}
