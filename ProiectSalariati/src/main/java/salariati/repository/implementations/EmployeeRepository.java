package main.java.salariati.repository.implementations;

import main.java.salariati.exception.EmployeeException;
import main.java.salariati.model.Employee;
import main.java.salariati.repository.interfaces.EmployeeRepositoryInterface;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository implements EmployeeRepositoryInterface {
    private final String employeeDBFile = "employeeDB/employees.txt";

    @Override
    public boolean addEmployee(Employee employee) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(employeeDBFile, true));
            bw.write(employee.toString());
            bw.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null)
                try {
                    bw.close();
                } catch (IOException e) {
                    System.err.println("Error while closing the bufferWriter: " + e);
                }
        }

        return false;
    }

    @Override
    public void deleteEmployee(Employee employee) {
        // TODO Auto-generated method stub

    }

    @Override
    public void modifyEmployee(Employee oldEmployee, Employee newEmployee) {
        BufferedReader br = null;
        FileOutputStream fileOut = null;
        StringBuffer buffer = new StringBuffer();
        String line;
        try {
            br = new BufferedReader(new FileReader(employeeDBFile));
            int counter = 0;
            while ((line = br.readLine()) != null) {
                Employee employee = new Employee();
                try {
                    employee = Employee.getEmployeeFromString(line, counter);
                } catch (EmployeeException ex) {
                    System.err.println("Error while reading: " + ex.toString());
                }
                if (employee.equals(oldEmployee)) {
                    line = newEmployee.toString();
                }

                buffer.append(line);
                buffer.append("\n");
                counter++;
            }

            fileOut = new FileOutputStream(employeeDBFile);
            fileOut.write(buffer.toString().getBytes());

        } catch (FileNotFoundException e) {
            System.err.println("Error while reading: " + e);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null)
                try {
                    br.close();
                } catch (IOException e) {
                    System.err.println("Error while closing the bufferReader: " + e);
                }
            if (fileOut != null)
                try {
                    fileOut.close();
                } catch (IOException e) {
                    System.err.println("Error while closing the fileWriter: " + e);
                }
        }
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
                try {
                    Employee employee = Employee.getEmployeeFromString(line, counter);
                    employeeList.add(employee);
                } catch (EmployeeException ex) {
                    System.err.println("Error while reading: " + ex.toString());
                }
                counter++;
            }
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
    public List<Employee> getEmployeesByAgeAsc() {
        return null;
    }

    @Override
    public List<Employee> getEmployeesBySalaryDesc() {
        return null;
    }


}
