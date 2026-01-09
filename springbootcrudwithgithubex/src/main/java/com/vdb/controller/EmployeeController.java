package com.vdb.controller;

import com.vdb.entity.Employee;
import com.vdb.exception.RecordNotFoundException;
import com.vdb.service.EmployeeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeeServiceImpl employeeService;

    @PostMapping("/signup")
    public ResponseEntity<Employee> signUp(@RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.signUp(employee));
    }

    @GetMapping("/signin/{empEmailId}/{empPassword}")
    public ResponseEntity<Boolean> signIn(@PathVariable String empEmailId, @PathVariable String empPassword) {
        return ResponseEntity.ok(employeeService.signIn(empEmailId, empPassword));
    }

    @GetMapping("/findbyid/{empId}")
    public ResponseEntity<Optional<Employee>> findById(@PathVariable long empId) {
        return ResponseEntity.ok(employeeService.findById(empId));
    }

    @GetMapping("/findall")
    public ResponseEntity<List<Employee>> findAll() {
        return ResponseEntity.ok(employeeService.findAll());
    }

    @PutMapping("/update/{empId}")
    public ResponseEntity<Employee> update(@PathVariable long empId, @RequestBody Employee employee) {
        Employee employee1 = employeeService.findById(empId).orElseThrow(() -> new RecordNotFoundException("Employee not found"));

        employee1.setEmpName(employee.getEmpName());
        employee1.setEmpEmailId(employee.getEmpEmailId());
        employee1.setEmpPassword(employee.getEmpPassword());
        employee1.setEmpContactNumber(employee.getEmpContactNumber());
        employee1.setEmpSalary(employee.getEmpSalary());

        return ResponseEntity.ok(employeeService.updateEmployee(empId, employee1));
    }

    @DeleteMapping("/delete/{empId}")
    public ResponseEntity<String> deleteById(@PathVariable long empId) {
        employeeService.deleteById(empId);

        return ResponseEntity.ok("employee deleted successfully");
    }

}
