package main.java.salariati.main;

import main.java.salariati.controller.EmployeeController;
import main.java.salariati.enumeration.DidacticFunction;
import main.java.salariati.model.Employee;
import main.java.salariati.repository.implementations.EmployeeRepository;
import main.java.salariati.repository.interfaces.EmployeeRepositoryInterface;
import main.java.salariati.repository.mock.EmployeeMock;
import main.java.salariati.validator.EmployeeValidator;
import main.java.salariati.view.UIEmployee;

//functionalitati
//F01.	 adaugarea unui nou angajat (nume, prenume, CNP, functia didactica, salariul de incadrare);
//F02.	 modificarea functiei didactice (asistent/lector/conferentiar/profesor) a unui angajat;
//F03.	 afisarea salariatilor ordonati descrescator dupa salariu si crescator dupa varsta (CNP).

public class StartApp {

    public static void main(String[] args) {
        //		testMockRepo();
        //       testFileRepo();
        EmployeeRepositoryInterface employeeRepository = new EmployeeRepository();
        //		EmployeeRepositoryInterface employeeRepository = new EmployeeMock();
        EmployeeController employeeController = new EmployeeController(employeeRepository);

        UIEmployee ui = new UIEmployee(employeeController);
        ui.runMenu();
    }

    private static void testMockRepo() {
        EmployeeRepositoryInterface employeesRepository = new EmployeeMock();
        EmployeeController employeeController = new EmployeeController(employeesRepository);

        for (Employee _employee : employeeController.getEmployeesList())
            System.out.println(_employee.toString());
        System.out.println("-----------------------------------------");

        Employee employee = new Employee("LastName", "FirstName", "1234567894321", DidacticFunction.ASISTENT, 2500);
        employeeController.addEmployee(employee);

        Employee toBeModified = employeeController.getEmployeesList().get(0);
        employeeController.modifyEmployee(toBeModified, new Employee("TESTModified", "TESTModified", "1234567894321", DidacticFunction.TEACHER, 2500));

        for (Employee _employee : employeeController.getEmployeesList())
            System.out.println(_employee.toString());

        EmployeeValidator validator = new EmployeeValidator();
        System.out.println(validator.isValid(new Employee("LastName", "FirstName", "1234567894322", DidacticFunction.TEACHER, 3400)));

    }

    private static void testFileRepo() {
        EmployeeRepositoryInterface employeesRepository = new EmployeeRepository();
        EmployeeController employeeController = new EmployeeController(employeesRepository);

        for (Employee _employee : employeeController.getEmployeesList())
            System.out.println(_employee.toString());
        System.out.println("-----------------------------------------");

        Employee employee = new Employee("LastName", "FirstName", "1234567894321", DidacticFunction.ASISTENT, 2500);
        employeeController.addEmployee(employee);

        Employee toBeModified = employeeController.getEmployeesList().get(0);
        employeeController.modifyEmployee(toBeModified, new Employee("TESTModified", "TESTModified", "1234567894321", DidacticFunction.TEACHER, 2500));

        for (Employee _employee : employeeController.getEmployeesList())
            System.out.println(_employee.toString());
    }
}
