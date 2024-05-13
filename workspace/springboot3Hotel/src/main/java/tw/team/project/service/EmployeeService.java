package tw.team.project.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import tw.team.project.model.Employee;
import tw.team.project.model.EmployeeRepository;


@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repositoryEmployeeRepository;
    @Autowired
    private PasswordEncoder pwdEncoder;

    public Employee addEmployee(Employee newEmployee) {
    	String employeePwd = newEmployee.getPassword();
    	String encodedPwd = pwdEncoder.encode(employeePwd);
    	newEmployee.setPassword(encodedPwd);
    	return repositoryEmployeeRepository.save(newEmployee);
    	
    }
    
    public boolean existByEmail(String email) {
    	if (email.length()!=0 && email!=null) {
    		Long result = repositoryEmployeeRepository.existsEmail(email);
	    	if (result!=0) {
	    		return true;
	    	}
    	}
    	return false;	
    }
    
    public boolean existById(Integer id) {
    	if (repositoryEmployeeRepository.existsById(id)) {
    		return true;
    	}
    	return false;
    }
    
    public Page<Employee> pageEmployee(Integer pageNumber){
    	Pageable pgb = PageRequest.of(pageNumber-1, 5,Sort.Direction.ASC,"employeeId");
    	Page<Employee> page = repositoryEmployeeRepository.findAll(pgb);
    	return page;
    	
    }
    
    public Employee checkLogin(String loginEmail, String loginPassword) {
    	if (loginEmail != null && loginPassword != null && loginEmail.length()!=0 && loginPassword.length()!=0) {
    		Optional<Employee> optional = repositoryEmployeeRepository.findByEmail(loginEmail);
	    	if (optional.isPresent()) {
	    		Employee dbEmployee = optional.get();
	    		String dbEncodedPwd = dbEmployee.getPassword();
				boolean result = pwdEncoder.matches(loginPassword, dbEncodedPwd);
				if (result) {
					return dbEmployee;
				}
	    	}
    	}
    	
    	return null;
    }
    // 只修改密碼和名字(for 員工)
    @Transactional
    public Employee update(Employee employee) {
    	if (employee!=null) {
    		Optional<Employee> optional = repositoryEmployeeRepository.findById(employee.getEmployeeId());
    		if (optional.isPresent()) {
    			Employee origin = optional.get();
    			origin.setEmployeeName(employee.getEmployeeName());
    			origin.setPassword(pwdEncoder.encode(employee.getPassword()));
    			return origin;
    		}
    	}
    	return null;
    }
    
    @Transactional
    public Employee manage(Employee employee) {
    	if (employee!=null) {
    		Optional<Employee> optional = repositoryEmployeeRepository.findById(employee.getEmployeeId());
    		if (optional.isPresent()) {
    			Employee origin = optional.get();
    			origin.setPosition(employee.getPosition());
    			origin.setPremission(employee.getPremission());
    			origin.setStatus(employee.getStatus());
    			
    			return origin;
    		}
    	}
    	return null;
    }
    
    public boolean deleteById(Integer id) {
    	if (id!=null) {
    		if (repositoryEmployeeRepository.existsById(id)) {
    			repositoryEmployeeRepository.deleteById(id);
    			return true;
    		}
    	}
    	return false;
    }
}
