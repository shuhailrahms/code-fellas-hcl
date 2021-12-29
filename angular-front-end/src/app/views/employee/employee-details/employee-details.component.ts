import { Employee } from '../../../entities/employee';
import { Component, OnInit, Input } from '@angular/core';
import { EmployeeService } from '../../../services/employee.service';
import { EmployeeListComponent } from '../employee-list/employee-list.component';
import { Router, ActivatedRoute } from '@angular/router';
import {SalaryService} from '../../../services/salary.service';

@Component({
  selector: 'app-employee-details',
  templateUrl: './employee-details.component.html',
  styleUrls: ['./employee-details.component.css']
})
export class EmployeeDetailsComponent implements OnInit {

  id: number;
  employee;
  employeeSalDetails;

  // salary calculation details
  monthlyBasic: number;
  monthlyHra: number;
  monthlyFoodAllowance: number;
  monthlySpecialAllowances: number;
  monthlyNetSalary: number;

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


  }

  viewSalaryDetails(designation) {
    this.salaryService.getSalaryByCategory(designation)
      .subscribe(data => {
        console.log(data);
        this.employeeSalDetails = data;
      }, error => console.log(error));
  }

  list() {
    this.router.navigate(['employees']);
  }

  leaveCalculate(basic, noPayLeaves) {
      this.monthlyBasic = (basic / 22) * (22 - noPayLeaves);
      this.monthlyHra = this.monthlyBasic * 0.1;
      this.monthlyFoodAllowance = this.monthlyBasic * 0.18;
      this.monthlySpecialAllowances = this.monthlyBasic * 0.05;
      this.monthlyNetSalary = this.monthlyBasic + this.monthlyHra + this.monthlyFoodAllowance + this.monthlySpecialAllowances;
  }
}
