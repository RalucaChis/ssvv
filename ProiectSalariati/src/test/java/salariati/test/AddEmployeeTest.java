package salariati.test;

import salariati.controller.EmployeeController;
import main.java.salariati.enumeration.DidacticFunction;
import main.java.salariati.model.Employee;
import salariati.repository.interfaces.EmployeeRepositoryInterface;
import salariati.repository.mock.EmployeeMock;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AddEmployeeTest {

	private EmployeeController controller;
	
	@Before
	public void setUp() {
		EmployeeRepositoryInterface employeeRepository = new EmployeeMock();
		controller = new EmployeeController(employeeRepository);
	}
	
	@Test
	public void TC1_ECP_valid() {
		int prevSize = controller.getEmployeesList().size();
		Employee newEmployee = new Employee("Popa","Vasile", "1980404345555", DidacticFunction.LECTURER, 1000);

		controller.addEmployee(newEmployee);

		assertEquals(prevSize + 1, controller.getEmployeesList().size());
		assertTrue(controller.getEmployeesList().contains(newEmployee));
	}

	@Test
	public void TC2_ECP_invalid_firstName() {
		int prevSize = controller.getEmployeesList().size();
		Employee newEmployee = new Employee("","Vasile", "1980404345555", DidacticFunction.LECTURER, 1000);

		controller.addEmployee(newEmployee);

		assertEquals(prevSize, controller.getEmployeesList().size());
		assertFalse(controller.getEmployeesList().contains(newEmployee));
	}

	@Test
	public void TC3_ECP_invalid_salary() {
		int prevSize = controller.getEmployeesList().size();
		Employee newEmployee = new Employee("Popa","Vasile", "1980404345555", DidacticFunction.LECTURER, -1000);

		controller.addEmployee(newEmployee);

		assertEquals(prevSize, controller.getEmployeesList().size());
		assertFalse(controller.getEmployeesList().contains(newEmployee));
	}

	@Test
	public void TC3_BVA_valid() {
		int prevSize = controller.getEmployeesList().size();
		Employee newEmployee = new Employee("Popa","Vasile", "1760909434543", DidacticFunction.LECTURER, 1000.00);

		controller.addEmployee(newEmployee);

		assertEquals(prevSize + 1, controller.getEmployeesList().size());
		assertTrue(controller.getEmployeesList().contains(newEmployee));
	}

	@Test
	public void TC5_BVA_invalid_cnp() {
		int prevSize = controller.getEmployeesList().size();
		Employee newEmployee = new Employee("Popa","Vasile", "2+&432546789", DidacticFunction.LECTURER, 1000.00);

		controller.addEmployee(newEmployee);

		assertEquals(prevSize, controller.getEmployeesList().size());
		assertFalse(controller.getEmployeesList().contains(newEmployee));
	}

	@Test
	public void TC6_BVA_valid() {
		int prevSize = controller.getEmployeesList().size();
		Employee newEmployee = new Employee("Popa","Vasile", "1980404345555", DidacticFunction.LECTURER, 2500.00);

		controller.addEmployee(newEmployee);

		assertEquals(prevSize + 1, controller.getEmployeesList().size());
		assertTrue(controller.getEmployeesList().contains(newEmployee));
	}

	@Test
	public void TC7_BVA_invalid_salary() {
		int prevSize = controller.getEmployeesList().size();
		Employee newEmployee = new Employee("Popa","Vasile", "2+&432546789", DidacticFunction.LECTURER, -100.00);

		controller.addEmployee(newEmployee);

		assertEquals(prevSize, controller.getEmployeesList().size());
		assertFalse(controller.getEmployeesList().contains(newEmployee));
	}
}
