import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CreateEmployeeComponent } from './views/employee/create-employee/create-employee.component';
import { EmployeeDetailsComponent } from './views/employee/employee-details/employee-details.component';
import { EmployeeListComponent } from './views/employee/employee-list/employee-list.component';
import { HttpClientModule } from '@angular/common/http';
import { UpdateEmployeeComponent } from './views/employee/update-employee/update-employee.component';
import { CreateSalaryComponent } from './views/salary/create-salary/create-salary.component';
import { SalaryDetailsComponent } from './views/salary/salary-details/salary-details.component';
import { SalaryListComponent } from './views/salary/salary-list/salary-list.component';
import { UpdateSalaryComponent } from './views/salary/update-salary/update-salary.component';
import { WelcomePageComponent } from './views/welcome-page/welcome-page.component';
@NgModule({
  declarations: [
    AppComponent,
    CreateEmployeeComponent,
    EmployeeDetailsComponent,
    EmployeeListComponent,
    UpdateEmployeeComponent,
    CreateSalaryComponent,
    SalaryDetailsComponent,
    SalaryListComponent,
    UpdateSalaryComponent,
    WelcomePageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
