package com.example.sampleproject;

import com.example.sampleproject.Model.Employee;
import com.example.sampleproject.controller.Controller;
import com.example.sampleproject.exception.EmptyListException;
import com.example.sampleproject.exception.InvalidEntryException;
import com.example.sampleproject.service.ServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
 public class EmployeeTest {

    Employee employee;
    @Autowired
    ServiceImpl service;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    Controller controller;

    @BeforeEach
    public void init() {
        employee = new Employee();
        employee.setEmpId(10);
        employee.setEmpName("Abhishek");
        employee.setEmpDept("Testing");
    }
    @Test
    void getEmployeeEmptyListTest(){

        assertThrows(EmptyListException.class,()->service.getEmployeeList(),"No Employee present");
    }
    @Test
    @DisplayName("Testing add method")
    void addEmployeeTest() {

        Employee actual= service.addEmployee(employee);
        Employee expected=service.getEmployeeById(employee.getEmpId());
        assertEquals(expected,actual);
        assertThrows(InvalidEntryException.class,()->service.addEmployee(employee),"Invalid entry");
    }
    /*void addEmployeeTest() {
        employee.setEmpId(10);
        employee.setEmpName("Abhishek");
        employee.setEmpDept("Testing");
        service.addEmployee(employee);
        assertThrows(InvalidEntryException.class, () -> service.addEmployee(employee), "Invalid data");
    }*/

    @Test
    void getEmployeeByIdTest() {
        employee.setEmpId(30);
        service.addEmployee(employee);
        assertThrows(InvalidEntryException.class,()->service.getEmployeeById(40));
    }
    @Test
    @DisplayName("Testing delete method")
    void deleteStudentTest() {
        service.addEmployee(employee);
        service.deleteEmployee(employee.getEmpId());
        assertThrows(InvalidEntryException.class,()->service.deleteEmployee(employee.getEmpId()),"Invalid Entry");
    }
    @Test
    @DisplayName("Testing update method")
    void updateEmployeeTest() {
        employee.setEmpId(13);
        employee.setEmpName("Kedar");
        employee.setEmpDept("Test");

        service.addEmployee(employee);
        assertThrows(InvalidEntryException.class,()->service.addEmployee(employee)," Invalid Entry");
    }

   /* @Test
    @DisplayName("post mapping ")
    void testControllerAddEmployee() throws Exception {
        ObjectMapper mapper= new ObjectMapper();
        service.addEmployee(employee);
        this.mockMvc.perform(post("/newEmp")
                        .contentType(MediaType.APPLICATION_JSON)
                        .contentType(mapper.writeValueAsString(employee)))
                .andExpect(status().isCreated())
                .andReturn();
    }*/
   @Test
   void addEmployeeTestController() throws Exception {
       ObjectMapper mapper=new ObjectMapper();
       employee.setEmpId(10);
       this.mockMvc.perform(post("/newEmp")
               .contentType(MediaType.APPLICATION_JSON)
               .content(mapper.writeValueAsString(employee)))
               .andReturn();
              // .andExpect(status().isCreated());
   }


    @Test
    @DisplayName("get mapping")
    void testControllerGetEmployee() throws Exception{
        ObjectMapper mapper= new ObjectMapper();
        this.mockMvc.perform(get("/allEmp")
                        .contentType(MediaType.APPLICATION_JSON)
                        .contentType(mapper.writeValueAsString(employee)))
               // .andExpect(status().isCreated());
                .andReturn();

    }

    @Test
    @DisplayName("put mapping")
    void testControllerUpdateEmployee() throws Exception{
        ObjectMapper mapper= new ObjectMapper();
        employee.setEmpId(10);
        employee.setEmpName("Abhishek");
        employee.setEmpDept("Testing");

        service.addEmployee(employee);
        service.updateEmployee(employee.getEmpId(),employee);
        this.mockMvc.perform(put("/student/studentId")
                        .contentType(MediaType.APPLICATION_JSON)
                        .contentType(mapper.writeValueAsString(employee)))
                .andReturn();

    }

    @Test
    @DisplayName("Delete mapping")
    void testControllerDeleteEmployee() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        employee.setEmpId(10);
        employee.setEmpName("Abhishek");
        employee.setEmpDept("Testing");

        service.addEmployee(employee);
        service.deleteEmployee(employee.getEmpId());
        this.mockMvc.perform(delete("/student/studentId")
                        .contentType(MediaType.APPLICATION_JSON)
                        .contentType(mapper.writeValueAsString(employee.getEmpId())))
                .andReturn();
    }
}
