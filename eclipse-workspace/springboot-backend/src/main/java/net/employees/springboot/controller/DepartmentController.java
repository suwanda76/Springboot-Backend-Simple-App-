package net.employees.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("api/v1/")
public class DepartmentController {
	
	@Autowired
	private DepartmentRepository departmentRepository;

	//getAll Department
	@GetMapping("department")
	public List<Department>getAllDepartment(){
		return departmentRepository.findAll();
	}
	
	//create rest api
	@PostMapping("/department")
	public Department createDepartment(@RequestBody Department department) {
		return departmentRepository.save(department);
	}

	@DeleteMapping("/department/{id}")
	public ResponseEntity <Map <String, Boolean>>deleteDepartment(@PathVariable Long id){
	  Department department = departmentRepository.findById(id).orElseThrow( () ->
		new ResourceNotFoundException("Department ID "+ id+ " is Not found in Database"));
		
	   departmentRepository.delete(department);
	   Map <String, Boolean> response = new HashMap<>();
	   response.put("deleted",Boolean.TRUE);
	   return ResponseEntity.ok(response);
	}


	@PutMapping("/departments/{id}")
	public ResponseEntity<Department> updateDepartment(@PathVariable Long id, @RequestBody Department departmentDetails){
		Department department = departmentRepository.findById(id).orElseThrow(()-> 
		new ResourceNotFoundException("Department ID " + id +" is NOT found in 	Database ..!"));
		department.setDepartmentId(departmentDetails.getDepartmentId());
		department.setDepartmentName(departmentDetails.getDepartmentName());
		Department updatedDepartment = departmentRepository.save(department);
		return ResponseEntity.ok(updatedDepartment);
	}

	@GetMapping("/departments/{id}")
	public ResponseEntity <Department> findEmployee(@PathVariable Long id){
		Department department = departmentRepository.findById(id).orElseThrow (()-> new ResourceNotFoundException("Employee ID "+ id + " is NOT found in Database..!") );
		return ResponseEntity.ok(department);

	}
}
