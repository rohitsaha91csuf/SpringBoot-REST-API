package org.example.service;

import org.example.entity.Employee;
import org.example.exception.EmployeeNotFoundException;
import org.example.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements serviceEmployee {
    @Autowired
    private EmployeeRepository repo;

    @Override
    public List<Employee> findAll() {
        return (List<Employee>)repo.findAll();
    }

    @Override
    public Employee findOne(String id) {
        Optional<Employee> employee= repo.findById(id);
        if(!employee.isPresent())
            throw new EmployeeNotFoundException("The employee "+id+" isNOTFOUND");
        return employee.get();
    }

    @Override
    @Transactional
    public Employee create(Employee emp) {
        Optional<Employee> existing= repo.findById(emp.getId());
        if(!existing.isPresent())
            throw new EmployeeNotFoundException("Employee with email"+emp.getEmail()+"already Exists");

        return repo.save(emp);

    }

    @Override
    @Transactional
    public void delete(String id) {
        Optional<Employee> existing =repo.findById(id);
        if(!existing.isPresent())
            throw new EmployeeNotFoundException("Employee "+id+" NOT FOUND");

        repo.delete(existing.get());
    }

    @Override
    @Transactional
    public Employee update(String id, Employee employee) {
        Optional<Employee> existing =repo.findById(id);
        if(!existing.isPresent())
            throw new EmployeeNotFoundException("Employee "+id+" NOT FOUND");
        return repo.save(employee);
        }
}
