package tw.team.project.model;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	
	@Query("select count(*) from Employee emp where emp.email = :email")
	public Long existsEmail(@Param("email") String email);
	
	@Query("from Employee emp where emp.email = :email")
	public Optional<Employee> findByEmail(@Param("email") String email);
}
