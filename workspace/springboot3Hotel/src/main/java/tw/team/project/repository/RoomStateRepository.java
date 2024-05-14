package tw.team.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tw.team.project.model.RoomState;

@Repository
public interface RoomStateRepository extends JpaRepository<RoomState, Integer>{

}
