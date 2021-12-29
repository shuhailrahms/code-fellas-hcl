package com.hcl.salaryproject.controller;

import com.hcl.salaryproject.dao.EmployeeDao;
import com.hcl.salaryproject.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    EmployeeDao employeeDao;

    @GetMapping
    public List<Employee> getAll() {
        return employeeDao.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Employee> getAgency(@PathVariable Integer id) {
        Optional<Employee> optionalEmployee = employeeDao.findById(id);
        if (optionalEmployee.isPresent()){
            return optionalEmployee;
        }else{
            return Optional.empty();
        }
    }

    @PostMapping
    public Optional<Employee> add(@RequestBody Employee employee) {
        return employeeDao.save(employee);
    }

    @PutMapping("/{id}")
    public Optional<Employee> update(@RequestBody Employee employee, @PathVariable Integer id){
        return employeeDao.update(id, employee);
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Integer id) {
        return employeeDao.deleteById(id);
    }
}
