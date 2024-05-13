package tw.team.project.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RefundTypeRepository extends JpaRepository<RefundType, Integer>{

    @Query("select count(*) from RefundType rt where rt.type = :name")
    public Long exitsName(@Param("name") String refundType);
}
