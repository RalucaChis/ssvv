package salariati.test;

import org.junit.Before;
import org.junit.Test;
import main.java.salariati.model.Employee;
import main.java.salariati.enumeration.DidacticFunction;
import salariati.controller.EmployeeController;
import salariati.repository.interfaces.EmployeeRepositoryInterface;
import salariati.repository.mock.EmployeeMock;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ModifyEmployeeTest {
    private EmployeeController controller;

    @Before
    public void setUp() {
        EmployeeRepositoryInterface employeeRepository = new EmployeeMock();
        controller = new EmployeeController(employeeRepository);
        Employee newEmployee = new Employee("Popa","Ion", "1731010890876", DidacticFunction.ASISTENT, 2500);
        controller.addEmployee(newEmployee);
    }

    @Test
    public void TC1_ECP_valid() {
        Employee oldEmployee = new Employee("Popa","Ion", "1731010890876", DidacticFunction.ASISTENT, 2500);
        Employee newEmployee = new Employee("Popa","Ion", "1731010890876", DidacticFunction.ASISTENT, 2800);
        controller.modifyEmployee(oldEmployee, newEmployee);
        assertTrue(newEmployee.equals(controller.getEmployeesList().get(controller.getEmployeesList().size() - 1)));
    }

    @Test
    public void TC2_ECP_invalid() {
        Employee oldEmployee = new Employee("Popa","Ion", "1731010890876", DidacticFunction.ASISTENT, 2500);
        Employee newEmployee = new Employee("Popa","Ion", "1731010890876", DidacticFunction.ASISTENT, 0);
        controller.modifyEmployee(oldEmployee, newEmployee);
        assertTrue(oldEmployee.equals(controller.getEmployeesList().get(controller.getEmployeesList().size() - 1)));
    }

    @Test
    public void TC3_BVA_valid() {
        Employee oldEmployee = new Employee("Popa","Ion", "1731010890876", DidacticFunction.ASISTENT, 2500);
        Employee newEmployee = new Employee("Popa","Ion", "1731010890876", DidacticFunction.ASISTENT, 2800);
        controller.modifyEmployee(oldEmployee, newEmployee);
        assertTrue(newEmployee.equals(controller.getEmployeesList().get(controller.getEmployeesList().size() - 1)));
    }

    @Test
    public void TC5_BVA_invalid() {
        Employee oldEmployee = new Employee("Popa","Ion", "1731010890876", DidacticFunction.ASISTENT, 2500);
        Employee newEmployee = new Employee("Popa","Ion", "MVABBDBBABDBD", DidacticFunction.ASISTENT, 2800);
        controller.modifyEmployee(oldEmployee, newEmployee);
        assertTrue(oldEmployee.equals(controller.getEmployeesList().get(controller.getEmployeesList().size() - 1)));
    }
}