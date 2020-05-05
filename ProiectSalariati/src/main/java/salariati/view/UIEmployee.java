package salariati.view;

import salariati.controller.EmployeeController;
import main.java.salariati.enumeration.DidacticFunction;
import main.java.salariati.model.Employee;

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
            Scanner key = new Scanner(System.in);
            System.out.println("Enter your option: ");
            int option = key.nextInt();
            Scanner scanner;
            switch (option) {
                case 1: {

                    System.out.println("Enter the first name of the employee you want to add: ");
                    scanner = new Scanner(System.in);
                    String firstName = scanner.nextLine();
                    System.out.println("You entered: "+firstName);
                    System.out.println("Enter the last name of the employee you want to add: ");
                    String lastName = scanner.nextLine();
                    System.out.println("You entered: "+lastName);
                    System.out.println("Enter the CNP of the employee you want to add: ");
                    String cnp = scanner.nextLine();
                    System.out.println("You entered: "+cnp);
                    System.out.println("Enter the didact function (ASISTENT, LECTURER, CONFERENTIAR, TEACHER) of the employee you want to add: ");
                    String function = scanner.nextLine();
                    System.out.println("You entered: "+function);
                    DidacticFunction didacticFunction = DidacticFunction.valueOf(function);
                    System.out.println("Enter the salary of the employee you want to add: ");
                    double salary = scanner.nextDouble();
                    Employee employee = new Employee(lastName, firstName, cnp, didacticFunction, salary);

                    this.employeeController.addEmployee(employee);
                    break;
                }
                case (2): {
                    System.out.println("Enter the CNP of the employee you want to delete: ");
                    scanner = new Scanner(System.in);
                    String cnp = scanner.nextLine();

                    List<Employee> employees = this.employeeController.getEmployeesList();
                    for (Employee e : employees) {
                        if (e.getCnp().equals(cnp))
                            this.employeeController.deleteEmployee(e);
                    }

                    break;
                }
                case (3): {
                    System.out.println("Enter the CNP of the employee you want to update: ");
                    scanner = new Scanner(System.in);
                    String cnp = scanner.nextLine();
                    Employee oldEmployee=new Employee();
                    List<Employee> employees=employeeController.getEmployeesList();
                    for (Employee e :employees){
                        if(e.getCnp().equals(cnp)) {
                            oldEmployee.setLastName(e.getLastName());
                            oldEmployee.setFirstName(e.getFirstName());
                            oldEmployee.setCnp(cnp);
                            oldEmployee.setFunction(e.getFunction());
                            oldEmployee.setSalary(e.getSalary());
                            break;
                        }
                    }
                    System.out.println("Enter the new first name: ");
                    String firstName = scanner.nextLine();
                    System.out.println("Enter the new last name: ");
                    String lastName = scanner.nextLine();
                    System.out.println("Enter the new didactic function (ASISTENT, LECTURER, CONFERENTIAR, TEACHER): ");
                    String function = scanner.nextLine();
                    DidacticFunction didacticFunction = DidacticFunction.valueOf(function);
                    System.out.println("Enter the new salary : ");
                    double salary = scanner.nextDouble();

                    Employee newEmployee=new Employee(lastName, firstName,cnp,didacticFunction,salary);
                    employeeController.modifyEmployee(oldEmployee,newEmployee);
                    break;
                }
                case (4): {
                    List<Employee> employees = this.employeeController.getEmployeesBySalaryDesc();
                    for (Employee e : employees)
                        System.out.println(e.toString());
                    break;
                }
                case (5): {
                    List<Employee> employees = this.employeeController.getEmployeesByAgeAsc();
                    for (Employee e : employees)
                        System.out.println(e.toString());
                    break;
                }
            }
        }
    }
}
