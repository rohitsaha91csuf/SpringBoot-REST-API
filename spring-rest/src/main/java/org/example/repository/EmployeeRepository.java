package org.example.repository;

import org.example.entity.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends CrudRepository<Employee, String> {

    Optional<Employee>findByEmail(String email);
}
