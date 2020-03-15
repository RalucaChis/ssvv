package salariati.repository.mock;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ListIterator;

import salariati.enumeration.DidacticFunction;
import salariati.model.Employee;
import salariati.repository.interfaces.EmployeeRepositoryInterface;
import salariati.validator.EmployeeValidator;

public class EmployeeMock implements EmployeeRepositoryInterface {

	private List<Employee> employeeList;
	private EmployeeValidator employeeValidator;
	
	public EmployeeMock() {
		
		employeeValidator = new EmployeeValidator();
		employeeList = new ArrayList<Employee>();
		
		Employee Ionel   = new Employee("Pacuraru", "1230516890876", DidacticFunction.ASISTENT, 2500);
		Employee Mihai   = new Employee("Dumitrescu", "1731010890876", DidacticFunction.LECTURER, 2500);
		Employee Ionela  = new Employee("Ionescu", "1830412890876", DidacticFunction.LECTURER, 2500);
		Employee Mihaela = new Employee("Pacuraru", "1901212890876", DidacticFunction.ASISTENT, 2500);
		Employee Vasile  = new Employee("Georgescu", "1650607890876", DidacticFunction.TEACHER,  2500);
		Employee Marin   = new Employee("Puscas", "1761203890876", DidacticFunction.TEACHER,  2500);
		
		employeeList.add( Ionel );
		employeeList.add( Mihai );
		employeeList.add( Ionela );
		employeeList.add( Mihaela );
		employeeList.add( Vasile );
		employeeList.add( Marin );
	}
	
	@Override
	public boolean addEmployee(Employee employee) {
		if ( employeeValidator.isValid(employee)) {
			employeeList.add(employee);
			return true;
		}
		return false;
	}
	
	@Override
	public void deleteEmployee(Employee employee) {
		// TODO Auto-generated method stub
	}

	@Override
	public void modifyEmployee(Employee oldEmployee, Employee newEmployee) {
		ListIterator<Employee> it = employeeList.listIterator();
		while (it.hasNext()) {
			if (it.next().equals(oldEmployee)) {
				it.set(newEmployee);
			}
		}
	}

	@Override
	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	@Override
	public List<Employee> getEmployeesByAgeAsc() {
		return getEmployeeList().stream()
				.sorted((e1, e2) -> {
					String cnp1 = e1.getCnp();
					String cnp2 = e2.getCnp();
					LocalDate birthday1 = LocalDate.of(
							Integer.parseInt(cnp1.substring(1, 3)),
							Integer.parseInt(cnp1.substring(3, 5)),
							Integer.parseInt(cnp1.substring(5, 7)));
					LocalDate birthday2 = LocalDate.of(
							Integer.parseInt(cnp2.substring(1, 3)),
							Integer.parseInt(cnp2.substring(3, 5)),
							Integer.parseInt(cnp2.substring(5, 7)));
					return birthday1.compareTo(birthday2);
				})
				.collect(Collectors.toList());

	}
}
