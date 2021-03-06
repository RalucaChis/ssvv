package salariati.repository.mock;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ListIterator;

import main.java.salariati.enumeration.DidacticFunction;
import main.java.salariati.model.Employee;
import salariati.repository.interfaces.EmployeeRepositoryInterface;
import main.java.salariati.validator.EmployeeValidator;

public class EmployeeMock implements EmployeeRepositoryInterface {

    private List<Employee> employeeList;
    private EmployeeValidator employeeValidator;

    public EmployeeMock() {
        employeeList = new ArrayList<>();
        employeeValidator = new EmployeeValidator();

        Employee Ionel = new Employee("Pacuraru", "Marcel", "1230516890876", DidacticFunction.ASISTENT, 2500);
        Employee Mihai = new Employee("Dumitrescu", "Daniel", "1731010890876", DidacticFunction.LECTURER, 2500);
        Employee Ionela = new Employee("Ionescu", "Stefana", "1830412890876", DidacticFunction.LECTURER, 2500);
        Employee Mihaela = new Employee("Pacuraru", "Maria", "1901212890876", DidacticFunction.ASISTENT, 2500);
        Employee Vasile = new Employee("Georgescu", "Ana", "1650607890876", DidacticFunction.TEACHER, 2500);
        Employee Marin = new Employee("Puscas", "Ligia", "1761203890876", DidacticFunction.TEACHER, 2500);

        employeeList.add(Ionel);
        employeeList.add(Mihai);
        employeeList.add(Ionela);
        employeeList.add(Mihaela);
        employeeList.add(Vasile);
        employeeList.add(Marin);
    }

    @Override
    public boolean addEmployee(Employee employee) {
        if (employeeValidator.isValid(employee)) {
            employeeList.add(employee);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteEmployee(String CNP) {
        for(Employee employee : employeeList){
            if (employee.getCnp().equals(CNP)){
                employeeList.remove(employee);
                return true;
            }
        }
         return false;

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

    @Override
    public List<Employee> getEmployeesBySalaryDesc() {
        List<Employee> employeesSortedBySalary = getEmployeeList().stream().sorted((e1, e2) ->
        {
            Double salary1 = e1.getSalary();
            Double salary2 = e2.getSalary();
            return salary1.compareTo(salary2);
        }).collect(Collectors.toList());
        Collections.reverse(employeesSortedBySalary);
        return employeesSortedBySalary;
    }
}
