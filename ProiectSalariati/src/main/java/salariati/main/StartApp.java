package salariati.main;

import salariati.model.Employee;
import salariati.repository.implementations.EmployeeImpl;
import salariati.repository.interfaces.EmployeeRepositoryInterface;
import salariati.repository.mock.EmployeeMock;
import salariati.validator.EmployeeValidator;
import salariati.controller.EmployeeController;
import salariati.enumeration.DidacticFunction;

//functionalitati
//F01.	 adaugarea unui nou angajat (nume, prenume, CNP, functia didactica, salariul de incadrare);
//F02.	 modificarea functiei didactice (asistent/lector/conferentiar/profesor) a unui angajat;
//F03.	 afisarea salariatilor ordonati descrescator dupa salariu si crescator dupa varsta (CNP).

public class StartApp {
	
	public static void main(String[] args) {
//		testMockRepo();
		testFileRepo();
	}

	private static void testMockRepo() {
		EmployeeRepositoryInterface employeesRepository = new EmployeeMock();
		EmployeeController employeeController = new EmployeeController(employeesRepository);

		for(Employee _employee : employeeController.getEmployeesList())
			System.out.println(_employee.toString());
		System.out.println("-----------------------------------------");

		Employee employee = new Employee("LastName", "1234567894321", DidacticFunction.ASISTENT, "2500");
		employeeController.addEmployee(employee);

		Employee toBeModified = employeeController.getEmployeesList().get(0);
		employeeController.modifyEmployee(toBeModified, new Employee("TESTModified", "1234567894321", DidacticFunction.TEACHER, "2500"));

		for(Employee _employee : employeeController.getEmployeesList())
			System.out.println(_employee.toString());

		EmployeeValidator validator = new EmployeeValidator();
		System.out.println( validator.isValid(new Employee("LastName", "1234567894322", DidacticFunction.TEACHER, "3400")) );
	}

	private static void testFileRepo() {
		EmployeeRepositoryInterface employeesRepository = new EmployeeImpl();
		EmployeeController employeeController = new EmployeeController(employeesRepository);

		for(Employee _employee : employeeController.getEmployeesList())
			System.out.println(_employee.toString());
		System.out.println("-----------------------------------------");

		Employee employee = new Employee("LastName", "1234567894321", DidacticFunction.ASISTENT, "2500");
		employeeController.addEmployee(employee);

		Employee toBeModified = employeeController.getEmployeesList().get(0);
		employeeController.modifyEmployee(toBeModified, new Employee("TESTModified", "1234567894321", DidacticFunction.TEACHER, "2500"));

		for(Employee _employee : employeeController.getEmployeesList())
			System.out.println(_employee.toString());
//
//		EmployeeValidator validator = new EmployeeValidator();
//		System.out.println( validator.isValid(new Employee("LastName", "1234567894322", DidacticFunction.TEACHER, "3400")) );
	}
}
