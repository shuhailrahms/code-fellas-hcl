import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {SalaryService} from '../../../services/salary.service';
import {Salary} from '../../../entities/salary';

@Component({
  selector: 'app-salary-details',
  templateUrl: './salary-details.component.html',
  styleUrls: ['./salary-details.component.css']
})
export class SalaryDetailsComponent implements OnInit {

  id: number;
  salary;

  constructor(private route: ActivatedRoute, private router: Router,
              private salaryService: SalaryService) { }

  ngOnInit() {
    this.salary = new Salary();

    this.id = this.route.snapshot.params.id;

    this.salaryService.getSalary(this.id)
      .subscribe(data => {
        console.log(data);
        this.salary = data;
      }, error => console.log(error));


  }

  list() {
    this.router.navigate(['salaries']);
  }

}
