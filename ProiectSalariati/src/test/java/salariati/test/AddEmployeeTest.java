package salariati.test;

import static org.junit.Assert.*;
import main.java.salariati.model.Employee;

import org.junit.Before;
import org.junit.Test;

//import salariati.repository.interfaces.EmployeeRepositoryInterface;
//import salariati.repository.mock.EmployeeMock;
//import salariati.validator.EmployeeValidator;
//import salariati.controller.EmployeeController;
import main.java.salariati.enumeration.DidacticFunction;
import main.java.salariati.repository.interfaces.EmployeeRepositoryInterface;
import main.java.salariati.controller.EmployeeController;
import main.java.salariati.validator.EmployeeValidator;
import main.java.salariati.repository.mock.EmployeeMock;

import static org.junit.Assert.assertEquals;

public class AddEmployeeTest {

	private EmployeeController controller;
	
	@Before
	public void setUp() {
		EmployeeRepositoryInterface employeeRepository = new EmployeeMock();
		controller = new EmployeeController(employeeRepository);
	}
	
	@Test
	public void TC1_ECP_valid() {
		Employee newEmployee = new Employee("ValidLastName","ValidFirstName", "1910509055057", DidacticFunction.ASISTENT, 3000);
		controller.addEmployee(newEmployee);
		assertEquals(7, controller.getEmployeesList().size());
		assertTrue(newEmployee.equals(controller.getEmployeesList().get(controller.getEmployeesList().size() - 1)));
	}

}
