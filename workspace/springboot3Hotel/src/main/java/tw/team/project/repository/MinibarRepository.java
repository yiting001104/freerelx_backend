package tw.team.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tw.team.project.dao.MinibarDAO;
import tw.team.project.model.Minibar;

@Repository
public interface MinibarRepository extends JpaRepository<Minibar, Integer>, MinibarDAO {

	@Query("select count(*) from Minibar m where m.item = :item")
	public long countByItem(@Param("item") String item);
}
