package tw.team.project.controller;

import java.net.URI;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import tw.team.project.model.Employee;
import tw.team.project.service.EmployeeService;
import tw.team.project.util.EmailValidator;

@RestController
@RequestMapping("/hotel")
@CrossOrigin
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	// 員工註冊
	@PostMapping("/employees/register")
	public ResponseEntity<?> registerPost(@RequestBody Employee employee){
		if (employee !=null) {
			if (EmailValidator.isValidEmail(employee.getEmail())) {
				if (!employeeService.existByEmail(employee.getEmail())) {
					Employee newEmployee = employeeService.addEmployee(employee);
					if (newEmployee!=null) {
						 String uri = "http://localhost:8080/hotel/employee/register"+newEmployee.getEmployeeId();
		                    return ResponseEntity.created(URI.create(uri)).contentType(MediaType.APPLICATION_JSON).body(newEmployee);
					}
				}
			}
		}
		return ResponseEntity.notFound().build();
		
	}
	
	@GetMapping("/employees")
	public ResponseEntity<?> findAll(@RequestParam(value = "p", defaultValue = "1") Integer pageNumber){
		Page<Employee> page = employeeService.pageEmployee(pageNumber);
		return ResponseEntity.ok(page.getContent());
		
	}
	
	@PostMapping("/employees/login")
	public String login(@RequestBody String json, HttpSession httpSession) {
		// @RequestParam("email")String email, @RequestParam("password") String pwd
		JSONObject obj = new JSONObject(json);
		String email = obj.isNull("email") ? null : obj.getString("email");
		String pwd = obj.isNull("password") ? null : obj.getString("password");
		
		JSONObject responseJSON = new JSONObject();
		Employee employee = employeeService.checkLogin(email, pwd);
		if (employee!=null) {
			responseJSON.put("message", "登入成功");
			responseJSON.put("Success", true);
            httpSession.setAttribute("loginEmpId", employee.getEmployeeId());
            httpSession.setAttribute("loginEmpName", employee.getEmployeeName());
		}else if (!employeeService.existByEmail(email)) {
			responseJSON.put("message", "使用者不存在");
			responseJSON.put("Success", false);
		}
		else{
			responseJSON.put("message", "帳號或密碼有誤，請重新輸入");
			responseJSON.put("Success", false);
		}
		return responseJSON.toString();
	}
	
	@PutMapping("/employees/{pk}")
	public ResponseEntity<?> modify(@PathVariable("pk") Integer id, @RequestBody Employee employee){
		if (id!=null) {
			if (employeeService.existById(id)) {
				Employee emp = employeeService.update(employee);
				if (emp != null) {
					return ResponseEntity.ok(emp);
				}
			}
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/employees/manager/{pk}")
	public ResponseEntity<?> manage(@PathVariable("pk") Integer id, @RequestBody Employee employee){
		if (id!=null) {
			if (employeeService.existById(id)) {
				Employee emp = employeeService.manage(employee);
				if (emp != null) {
					return ResponseEntity.ok(emp);
				}
			}
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/employees/{pk}")
	public ResponseEntity<Void> remove(@PathVariable("pk") Integer id){
		if (employeeService.deleteById(id)) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
	
}
