package tw.team.project.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentRepository extends JpaRepository<Comment, Integer>{

	@Query("from Comment comm where comm.member.memberId = :id")
	public Page<Comment> findByMemberId(@Param("id")Integer memberId, Pageable pgb);
	
	@Query("from Comment comm where comm.typeInstance = :type")
	public Page<Comment> findByInstance(@Param("type")String instanceName, Pageable pgb);
}
