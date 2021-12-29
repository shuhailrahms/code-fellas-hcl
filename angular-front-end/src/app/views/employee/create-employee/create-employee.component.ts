import { EmployeeService } from '../../../services/employee.service';
import { Employee } from '../../../entities/employee';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {SalaryService} from "../../../services/salary.service";

@Component({
  selector: 'app-create-employee',
  templateUrl: './create-employee.component.html',
  styleUrls: ['./create-employee.component.css']
})
export class CreateEmployeeComponent implements OnInit {

  employee: Employee = new Employee();
  salaries;
  submitted = false;

  constructor(private employeeService: EmployeeService,
              private salaryService: SalaryService,
              private router: Router) { }

  ngOnInit() {
    this.salaryService
      .getAll().subscribe(data => {
        console.log(data);
        this.salaries = data;
      },
      error => console.log(error));
  }

  newEmployee(): void {
    this.submitted = false;
    this.employee = new Employee();
  }

  save() {
    this.employeeService
    .add(this.employee).subscribe(data => {
      console.log(data);
      this.employee = new Employee();
      this.gotoList();
    },
    error => console.log(error));
  }

  onSubmit() {
    this.submitted = true;
    this.save();
  }

  gotoList() {
    this.router.navigate(['/employees']);
  }
}
