package com.vdb.repository;

import com.vdb.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findByEmpEmailIdAndEmpPassword(String empEmailId, String empPassword);

    Optional<Employee> findByEmpEmailId(String empEmailId);

}
