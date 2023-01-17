package com.example.sampleproject.Model;

public class Employee {
    private int empId;
    private String empName;
    private String empDept;

    public Employee() {

    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpDept() {
        return empDept;
    }

    public void setEmpDept(String empDept) {
        this.empDept = empDept;
    }

    public Employee(int empId, String empName, String empDept) {
        this.empId = empId;
        this.empName = empName;
        this.empDept = empDept;
    }
}
