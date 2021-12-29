import { Component, OnInit } from '@angular/core';
import {SalaryService} from '../../../services/salary.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-salary-list',
  templateUrl: './salary-list.component.html',
  styleUrls: ['./salary-list.component.css']
})
export class SalaryListComponent implements OnInit {

  salaries;

  constructor(private salaryService: SalaryService,
              private router: Router) {}

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.salaries = this.salaryService.getAll();
  }

  deleteSalary(id: number) {
    this.salaryService.delete(id)
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
        },
        error => console.log(error));
  }

  salaryDetails(id: number) {
    this.router.navigate(['salaries/details', id]);
  }

  updateSalary(id: number) {
    this.router.navigate(['salaries/update', id]);
  }

}
