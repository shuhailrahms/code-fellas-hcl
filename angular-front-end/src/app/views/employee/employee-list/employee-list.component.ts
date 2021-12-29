import { EmployeeDetailsComponent } from '../employee-details/employee-details.component';
import { Observable } from 'rxjs';
import { EmployeeService } from '../../../services/employee.service';
import { Employee } from '../../../entities/employee';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent implements OnInit {
  employees;

  constructor(private employeeService: EmployeeService,
              private router: Router) {}

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.employees = this.employeeService.getAll();
  }

  deleteEmployee(id: number) {
    this.employeeService.delete(id)
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
        },
        error => console.log(error));
  }

  employeeDetails(id: number) {
    this.router.navigate(['employees/details', id]);
  }

  updateEmployee(id: number) {
    this.router.navigate(['employees/update', id]);
  }
}
