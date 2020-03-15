package salariati.view;

import salariati.controller.EmployeeController;
import salariati.enumeration.DidacticFunction;
import salariati.model.Employee;

import java.util.List;
import java.util.Scanner;

public class UIEmployee {
    private EmployeeController employeeController;

    public UIEmployee(EmployeeController employeeController) {
        this.employeeController = employeeController;
    }


    private void showMenu() {
        System.out.println("-------------------");
        System.out.println("Menu");
        System.out.println("-------------------");
        System.out.println("\t 1. Add a new employee");
        System.out.println("\t 2. Delete an existing employee");
        System.out.println("\t 3. Modify an existing employee");
        System.out.println("\t 4. Show all employees sorted by salary");
        System.out.println("\t 5. Show all employees sorted by age");
    }

    public void runMenu() {
        while (true) {
            showMenu();
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter your option: ");
            int option = scanner.nextInt();
            switch (option) {
                case 1: {
                    System.out.println("Enter the first name of the employee you want to add: ");
                    String firstName = scanner.nextLine();
                    System.out.println("Enter the last name of the employee you want to add: ");
                    String lastName = scanner.nextLine();
                    System.out.println("Enter the CNP of the employee you want to add: ");
                    String cnp = scanner.nextLine();
                    System.out.println("Enter the didact function (ASISTENT, LECTURER, TEACHER) of the employee you want to add: ");
                    String function = scanner.nextLine();
                    DidacticFunction didacticFunction = DidacticFunction.valueOf(function);
                    System.out.println("Enter the salary of the employee you want to add: ");
                    double salary = scanner.nextDouble();

                    Employee employee = new Employee(firstName, lastName, cnp, didacticFunction, salary);

                    this.employeeController.addEmployee(employee);
                    break;
                }
                case (2): {
                    System.out.println("Enter the CNP of the employee you want to delete: ");
                    String cnp = scanner.nextLine();

                    List<Employee> employees = this.employeeController.getEmployeesList();
                    for (Employee e : employees) {
                        if (e.getCnp().equals(cnp))
                            this.employeeController.deleteEmployee(e);
                    }
                    break;
                }
                case (3): {

                    break;
                }
                case (4): {
                    List<Employee> employees = this.employeeController.getEmployeesList();
                    for (Employee e : employees)
                        System.out.println(e.toString());
                    break;
                }
                case (5): {
                    
                    break;
                }
            }
        }
    }
}
