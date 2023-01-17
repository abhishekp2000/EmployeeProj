package com.example.sampleproject.controller;

import com.example.sampleproject.Model.Employee;
import com.example.sampleproject.service.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {
    Logger log= LoggerFactory.getLogger(Controller.class);
    @Autowired
    ServiceImpl service;
    @GetMapping("/allEmp")

    public List<Employee> employeeList(){
        log.info("Getting details created");
        return service.getEmployeeList();

    }

    @PostMapping("/newEmp")
    public Employee addEmployee(@RequestBody Employee em){
        Employee e=service.addEmployee(em);
        log.info("Employee created");
        return e;
    }

    @GetMapping("/employee/{empId}")
   // @ResponseStatus(HttpStatus.OK)
    public Employee getEmployeeId(@PathVariable int empId) {
        log.info("Getting details by Id created");
        return service.getEmployeeById(empId);
    }

    @DeleteMapping("/employee/{empId}")
    //@ResponseStatus(HttpStatus.ACCEPTED)
    public String deleteEmployee(@PathVariable int empId) {
        log.info("delete employee by id created");
        return service.deleteEmployee(empId);
    }

    @DeleteMapping("/employees/{empDept}")
    //@ResponseStatus(HttpStatus.ACCEPTED)
    public String deleteEmployeeByDept(@PathVariable String empDept) {
        log.info("delete emp by dept created");
        return service.deleteEmployeeByDept(empDept);
    }

    @PutMapping("employee/{empId}")
    //@PutMapping("/books/update/{isbnNo}")
    //@ResponseStatus(HttpStatus.CREATED)
    public Employee updateEmp(@PathVariable int empId,@RequestBody Employee employee) {
        log.info("update employee created");
        return service.updateEmployee(empId,employee);
    }
}
