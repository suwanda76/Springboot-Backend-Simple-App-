package net.employees.springboot.controller;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.employees.springboot.exception.ResourceNotFoundException;
import net.employees.springboot.model.Department;
import net.employees.springboot.model.Employee;
import net.employees.springboot.repository.DepartmentRepository;
import net.employees.springboot.repository.EmployeeRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {

	private EmployeeRepository employeeRepository;
	private DepartmentRepository departmentRepository;
	
	@Autowired
	public EmployeeController(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
		this.employeeRepository = employeeRepository;
		this.departmentRepository = departmentRepository;
	}


	@DeleteMapping("/employees/{id}")
	public ResponseEntity <Map <String, Boolean>>deleteEmployee(@PathVariable Long id){
	  Employee employee = employeeRepository.findById(id).orElseThrow( () ->
		new ResourceNotFoundException("Employee ID "+ id+ " is Not found in Database"));
		
	   employeeRepository.delete(employee);
	   Map <String, Boolean> response = new HashMap<>();
	   response.put("deleted",Boolean.TRUE);
	   return ResponseEntity.ok(response);
	}


	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails){
		Employee employee = employeeRepository.findById(id).orElseThrow(()-> 
		new ResourceNotFoundException("Employee ID " + id +" is NOT found in 	Database ..!"));
			  employee.setFirstName(employeeDetails.getFirstName());
			  employee.setLastName(employeeDetails.getLastName());
			  employee.setEmailId(employeeDetails.getEmailId());
			  employee.setDepartmentId(employeeDetails.getDepartmentId());
			  Employee updatedEmployee = employeeRepository.save(employee);
			  return ResponseEntity.ok(updatedEmployee);		 
	}

	@GetMapping("/employees/{id}")
	public ResponseEntity <Employee> findEmployee(@PathVariable Long id){
		Employee employee = employeeRepository.findById(id).orElseThrow (()-> new ResourceNotFoundException("Employee ID "+ id + " is NOT found in Database..!") );
		return ResponseEntity.ok(employee);

	}

	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee){
		return employeeRepository.save(employee);
	}

	@GetMapping("employees")
	public List<Employee> getEmployees(){
		return employeeRepository.findAll();
	}


}
