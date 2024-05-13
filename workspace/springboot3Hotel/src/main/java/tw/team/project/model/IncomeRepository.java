package tw.team.project.model;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Date;


public interface IncomeRepository extends JpaRepository<Income, Integer>{

	
	public List<Income> findByCreateDateBetween(Date begainDate, Date endDate);
}
