import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {SalaryService} from '../../../services/salary.service';
import {Salary} from '../../../entities/salary';

@Component({
  selector: 'app-update-salary',
  templateUrl: './update-salary.component.html',
  styleUrls: ['./update-salary.component.css']
})
export class UpdateSalaryComponent implements OnInit {

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

  updateSalary() {
    this.salaryService.update(this.id, this.salary)
      .subscribe(data => {
        console.log(data);
        this.salary = new Salary();
        this.gotoList();
      }, error => console.log(error));
  }

  onSubmit() {
    this.updateSalary();
  }

  gotoList() {
    this.router.navigate(['/salaries']);
  }

}
