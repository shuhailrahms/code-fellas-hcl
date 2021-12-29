package com.hcl.salaryproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Salary {
    private Integer id;
    private Float basic;
    private Float hra;
    private Float foodAllowance;
    private Float specialAllowances;
    private String category;
    private Float netSalary;
}
