package salariati.test;
import main.java.salariati.model.Employee;
import salariati.controller.EmployeeController;
import main.java.salariati.enumeration.DidacticFunction;
import salariati.repository.interfaces.EmployeeRepositoryInterface;
import org.junit.Before;
import org.junit.Test;
import salariati.repository.mock.EmployeeMock;

import static org.junit.Assert.*;

public class DeleteEmployeeTest {
    private EmployeeController controller;

    @Before
    public void setUp() {
        EmployeeRepositoryInterface employeeRepository = new EmployeeMock();
        controller = new EmployeeController(employeeRepository);
        Employee newEmployee = new Employee("Crisan","Adela", "2990714145887", DidacticFunction.LECTURER, 3000);
        Employee newEmployee2 = new Employee("Popescu","George", "1960714645457", DidacticFunction.ASISTENT, 2000);
        controller.addEmployee(newEmployee);
        controller.addEmployee(newEmployee2);
    }

    @Test
    public void TC1_ECP_valid() {
        Employee employeeToDelete = new Employee("Popescu","George", "1960714645457", DidacticFunction.ASISTENT, 2000);
        controller.deleteEmployee(employeeToDelete);

        assertFalse(controller.getEmployeesList().contains(employeeToDelete));
    }

    @Test
    public void TC2_ECP_invalid() {
        int startSize = controller.getEmployeesList().size();
        Employee employee = new Employee("Popescu","George", "", DidacticFunction.ASISTENT, 2000);
        controller.deleteEmployee(employee);

        assertEquals(startSize, controller.getEmployeesList().size());
    }

    @Test
    public void TC3_BVA_valid() {
        Employee employee = new Employee("Popescu","George", "1960714645457", DidacticFunction.ASISTENT, 2000);
        controller.deleteEmployee(employee);

        assertFalse(controller.getEmployeesList().contains(employee));
    }

    @Test
    public void TC4_BVA_invalid() {
        int startSize = controller.getEmployeesList().size();
        Employee employee = new Employee("Popescu","George", "LLZZAAMMMNNNF", DidacticFunction.ASISTENT, 2000);
        controller.deleteEmployee(employee);

        assertEquals(startSize, controller.getEmployeesList().size());
    }
}