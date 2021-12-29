import { Component, OnInit } from '@angular/core';
import {Salary} from "../../../entities/salary";
import {SalaryService} from "../../../services/salary.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-create-salary',
  templateUrl: './create-salary.component.html',
  styleUrls: ['./create-salary.component.css']
})
export class CreateSalaryComponent implements OnInit {

  salary: Salary = new Salary();

  submitted = false;

  constructor(private salaryService: SalaryService,
              private router: Router) { }

  ngOnInit() { }

  newSalary(): void {
    this.submitted = false;
    this.salary = new Salary();
  }

  save() {
    this.salaryService
      .add(this.salary).subscribe(data => {
        console.log(data);
        this.salary = new Salary();
        this.gotoList();
      },
      error => console.log(error));
  }

  onSubmit() {
    this.submitted = true;
    this.save();
  }

  gotoList() {
    this.router.navigate(['/salaries']);
  }

}
