package com.hcl.salaryproject.dao;

import com.hcl.salaryproject.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeDao {
    List<Employee> findAll();

    Optional<Employee> findById(Integer id);

    Optional<Employee> save(Employee employee);

    Optional<Employee> update(Integer id, Employee employee);

    Boolean deleteById(Integer id);
}


