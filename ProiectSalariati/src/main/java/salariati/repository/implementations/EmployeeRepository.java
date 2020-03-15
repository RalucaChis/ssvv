package salariati.repository.implementations;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import salariati.exception.EmployeeException;

import salariati.model.Employee;

import salariati.repository.interfaces.EmployeeRepositoryInterface;
import salariati.validator.EmployeeValidator;

public class EmployeeRepository implements EmployeeRepositoryInterface {

    private final String employeeDBFile = "employeeDB/employees.txt";
    private EmployeeValidator employeeValidator = new EmployeeValidator();

    @Override
    public boolean addEmployee(Employee employee) {
        if (employeeValidator.isValid(employee)) {
            BufferedWriter bw = null;
            try {
                bw = new BufferedWriter(new FileWriter(employeeDBFile, true));
                bw.write(employee.toString());
                bw.newLine();
                bw.close();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public void deleteEmployee(Employee employee) {
        // TODO Auto-generated method stub

        //saving the employees in a list
        List<Employee> employeeList = new ArrayList<Employee>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(employeeDBFile));
            String line;
            int counter = 0;
            while ((line = br.readLine()) != null) {
                Employee empl = new Employee();
                try {
                    empl = (Employee) Employee.getEmployeeFromString(line, counter);
                    employeeList.add(empl);
                } catch (EmployeeException ex) {
                    System.err.println("Error while reading: " + ex.toString());
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error while reading: " + e);
        } catch (IOException e) {
            System.err.println("Error while reading: " + e);
        } finally {
            if (br != null)
                try {
                    br.close();
                } catch (IOException e) {
                    System.err.println("Error while closing the file: " + e);
                }
        }

        //deleting the employee given as a parameter from list
        for (Employee e : employeeList) {
            if (e.equals(employee))
                employeeList.remove(e);
        }

        //writing the new list in file
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(employeeDBFile));
            for (Employee e : employeeList) {
                bw.write(e.toString());
                bw.newLine();
            }
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void modifyEmployee(Employee oldEmployee, Employee newEmployee) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<Employee> getEmployeeList() {
        List<Employee> employeeList = new ArrayList<Employee>();

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(employeeDBFile));
            String line;
            int counter = 0;
            while ((line = br.readLine()) != null) {
                Employee employee = new Employee();
                try {
                    employee = (Employee) Employee.getEmployeeFromString(line, counter);
                    employeeList.add(employee);
                } catch (EmployeeException ex) {
                    System.err.println("Error while reading: " + ex.toString());
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error while reading: " + e);
        } catch (IOException e) {
            System.err.println("Error while reading: " + e);
        } finally {
            if (br != null)
                try {
                    br.close();
                } catch (IOException e) {
                    System.err.println("Error while closing the file: " + e);
                }
        }

        return employeeList;
    }


    @Override
    public List<Employee> getEmployeeByCriteria(String criteria) {
        List<Employee> employeeList = new ArrayList<Employee>();

        return employeeList;
    }

}
