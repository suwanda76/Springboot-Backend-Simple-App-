package net.employees.springboot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import net.employees.springboot.model.Department;
import net.employees.springboot.repository.DepartmentRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback(true)
class SpringbootBackendApplicationTests {

	@Autowired
	private DepartmentRepository departmentRepository;
	
	/*
	 * @Test void testFindDepartmentInitial() { //Department departmentFound = new
	 * Department(); Department foundDepartment =
	 * departmentRepository.findByDepartmentByInit("FI");
	 * assertNotNull(foundDepartment); assertEquals("FI",
	 * foundDepartment.getDepartmentId()); }
	 */

}
