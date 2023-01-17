package com.example.sampleproject.service;

import com.example.sampleproject.Model.Employee;
import com.example.sampleproject.exception.EmptyListException;
import com.example.sampleproject.exception.InvalidEntryException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ServiceImpl {
    //public List<Employee> employeeList = new ArrayList<>(Arrays.asList(new Employee(1, "Abhi", "Development"),
            //new Employee(2, "Kedar", "Testing")));
    // return employees
    List<Employee> elist = new ArrayList<>();

    public List<Employee> getEmployeeList() {
        // return employeeList;
        if(elist.isEmpty()){
            throw new EmptyListException("No Employee present");}
        return elist;
    }

    //add employees
    public Employee addEmployee(Employee e) {
        // employeeList.add(e);
      String idLen = String.valueOf(e.getEmpId());
        if(idLen.startsWith("-") || idLen.startsWith("0")){
            throw new InvalidEntryException("Invalid Employee Id number ");
        }
        if(!(e.getEmpName().length()>4)&&(e.getEmpName()!=null)) {
            throw new InvalidEntryException("length of Employee Name must be more than 4 character ");
        }
        else
        elist.add(e);


        return e;
    }

    //serach employee by Id
    // public  Employee getEmployee(String id){
    // return employeeList.stream().filter(t->t.getEmpId().equals(id)).findFirst().get();
    public Employee getEmployeeById(int empId) {
        Employee e1 = null;
        //for (Employee empObj : employeeList) {
        for (Employee empObj : elist) {
            if (empObj.getEmpId() == empId) {
                e1 = empObj;
            }
        }
        return e1;

    }

    public String deleteEmployee(int empId) {
        Employee employee = null;
        for (Employee empObj : elist) {
            if (empObj.getEmpId() == empId) {
                employee = empObj;
                elist.remove(empObj);
                break;
            }

        }
        return "Employee Deleted";
    }

    public String deleteEmployeeByDept(String deptNo){
        Employee employee=null;
        for (Employee empObj : elist) {
            if (empObj.getEmpDept().equals(deptNo)) {
                employee=empObj;
                elist.remove(empObj);
                break;
            }}
        if(employee==null){
        throw new InvalidEntryException("Invalid Department or No Employee is present with this Department "+deptNo);}
       // logger.info("Book deleted");
        return "Employee deleted";
    }

    public Employee updateEmployee(int eid, Employee employee) {
        ServiceImpl service = new ServiceImpl();
        for (Employee empObj : elist) {
            if (empObj.getEmpId() == eid) {
                empObj.setEmpId(eid);
                empObj.setEmpName(employee.getEmpName());
                empObj.setEmpDept(employee.getEmpDept());

                service.addEmployee(empObj);
                break;
            }
        }
        return employee;

    }
}



