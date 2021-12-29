package com.hcl.salaryproject.controller;

import com.hcl.salaryproject.dao.SalaryDao;
import com.hcl.salaryproject.entity.Salary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/salaries")
public class SalaryController {
    @Autowired
    SalaryDao salaryDao;

    @GetMapping
    public List<Salary> getAll() {
        return salaryDao.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Salary> getSalary(@PathVariable Integer id) {
        Optional<Salary> optionalSalary = salaryDao.findById(id);
        if (optionalSalary.isPresent()){
            return optionalSalary;
        }else{
            return Optional.empty();
        }
    }

    @GetMapping("/categories/{categoryId}")
    public Optional<Salary> getSalaryByCategory(@PathVariable Integer categoryId) {
        Optional<Salary> optionalSalary = salaryDao.getSalaryByCategory(categoryId);
        if (optionalSalary.isPresent()){
            return optionalSalary;
        }else{
            return Optional.empty();
        }
    }

    @PostMapping
    public Optional<Salary> add(@RequestBody Salary salary) {
        return salaryDao.save(salary);
    }

    @PutMapping("/{id}")
    public Optional<Salary> update(@RequestBody Salary salary, @PathVariable Integer id){
        return salaryDao.update(id, salary);
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Integer id) {
        return salaryDao.deleteById(id);
    }
}
