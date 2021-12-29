import { Component, OnInit } from '@angular/core';
import { Employee } from '../../../entities/employee';
import { ActivatedRoute, Router } from '@angular/router';
import { EmployeeService } from '../../../services/employee.service';
import {SalaryService} from '../../../services/salary.service';

@Component({
  selector: 'app-update-employee',
  templateUrl: './update-employee.component.html',
  styleUrls: ['./update-employee.component.css']
})
export class UpdateEmployeeComponent implements OnInit {

  id: number;
  employee;
  salaries;

  constructor(private route: ActivatedRoute, private router: Router,
              private employeeService: EmployeeService, private salaryService: SalaryService) { }

  ngOnInit() {
    this.employee = new Employee();

    this.id = this.route.snapshot.params.id;

    this.employeeService.getEmployee(this.id)
      .subscribe(data => {
        console.log(data);
        this.employee = data;
      }, error => console.log(error));

    this.salaryService
      .getAll().subscribe(data => {
        console.log(data);
        this.salaries = data;
      },
      error => console.log(error));
  }

  updateEmployee() {
    this.employeeService.update(this.id, this.employee)
      .subscribe(data => {
        console.log(data);
        this.employee = new Employee();
        this.gotoList();
      }, error => console.log(error));
  }

  onSubmit() {
    this.updateEmployee();
  }

  gotoList() {
    this.router.navigate(['/employees']);
  }
}
