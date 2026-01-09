package com.vdb.service;

import com.vdb.entity.Employee;
import com.vdb.exception.RecordNotFoundException;
import com.vdb.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee signUp(Employee employee) {
        return employeeRepository.save(employee);
    }

    public boolean signIn(String empEmailId, String empPassword) {
        boolean flag = false;

        Employee employee = employeeRepository.findByEmpEmailIdAndEmpPassword(empEmailId, empPassword);

        if (employee != null) {
            flag = true;
        }

        return flag;
    }

    public Optional<Employee> findByEmailId(String empEmailId) {
        return Optional.ofNullable(employeeRepository.findByEmpEmailId(empEmailId).orElseThrow(() -> new
                RecordNotFoundException("Employee not found")));
    }

    public Optional<Employee> findById(long empId) {
        return Optional.of(employeeRepository.findById(empId).orElseThrow(() ->
                new RecordNotFoundException("Employee not found")));
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee updateEmployee(long empId, Employee employee) {
        return employeeRepository.save(employee);
    }

    public void deleteById(long empId) {
        employeeRepository.deleteById(empId);
    }

}
