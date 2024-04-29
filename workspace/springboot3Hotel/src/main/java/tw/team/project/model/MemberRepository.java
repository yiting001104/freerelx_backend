package tw.team.project.model;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberRepository extends JpaRepository<Member, Integer>{

	@Query("from Member where email = :email")
	public Optional<Member> findByEmail(String email);
}
