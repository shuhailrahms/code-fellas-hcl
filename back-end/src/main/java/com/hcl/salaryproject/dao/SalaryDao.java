package com.hcl.salaryproject.dao;

import com.hcl.salaryproject.entity.Employee;
import com.hcl.salaryproject.entity.Salary;

import java.util.List;
import java.util.Optional;

public interface SalaryDao {
    List<Salary> findAll();

    Optional<Salary> getSalaryByCategory(Integer categoryId);

    Optional<Salary> findById(Integer id);

    Optional<Salary> save(Salary salary);

    Optional<Salary> update(Integer id, Salary salary);

    Boolean deleteById(Integer id);
}
