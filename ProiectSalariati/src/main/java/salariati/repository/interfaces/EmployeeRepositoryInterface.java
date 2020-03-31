package salariati.repository.interfaces;

import main.java.salariati.model.Employee;

import java.util.List;

public interface EmployeeRepositoryInterface {
	
	boolean addEmployee(Employee employee);
	boolean deleteEmployee(String CNP);
	void modifyEmployee(Employee oldEmployee, Employee newEmployee);
	List<Employee> getEmployeeList();
	List<Employee> getEmployeesByAgeAsc();
	List<Employee> getEmployeesBySalaryDesc();

}
