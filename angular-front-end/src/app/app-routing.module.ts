import { EmployeeDetailsComponent } from './views/employee/employee-details/employee-details.component';
import { CreateEmployeeComponent } from './views/employee/create-employee/create-employee.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { EmployeeListComponent } from './views/employee/employee-list/employee-list.component';
import { UpdateEmployeeComponent } from './views/employee/update-employee/update-employee.component';
import {SalaryListComponent} from './views/salary/salary-list/salary-list.component';
import {CreateSalaryComponent} from './views/salary/create-salary/create-salary.component';
import {UpdateSalaryComponent} from './views/salary/update-salary/update-salary.component';
import {SalaryDetailsComponent} from './views/salary/salary-details/salary-details.component';
import {WelcomePageComponent} from './views/welcome-page/welcome-page.component';

const routes: Routes = [
  // { path: '', redirectTo: 'employee', pathMatch: 'full' },
  { path: '', component: WelcomePageComponent },
  { path: 'employees', component: EmployeeListComponent },
  { path: 'employees/add', component: CreateEmployeeComponent },
  { path: 'employees/update/:id', component: UpdateEmployeeComponent },
  { path: 'employees/details/:id', component: EmployeeDetailsComponent },
  { path: 'salaries', component: SalaryListComponent },
  { path: 'salaries/add', component: CreateSalaryComponent },
  { path: 'salaries/update/:id', component: UpdateSalaryComponent },
  { path: 'salaries/details/:id', component: SalaryDetailsComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
