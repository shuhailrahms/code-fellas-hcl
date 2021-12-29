import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  private baseUrl = 'http://localhost:8080/employees';

  constructor(private httpClient: HttpClient) { }

  public getAll() {
    return this.httpClient.get(this.baseUrl);
  }

  public getEmployee(id) {
    return this.httpClient.get(this.baseUrl + `/${id}`);
  }

  public add(employee) {
    return this.httpClient.post(this.baseUrl, employee);
  }

  public update(id, employee) {
    return this.httpClient.put(this.baseUrl + `/${id}`, employee);
  }

  public delete(id) {
    return this.httpClient.delete(this.baseUrl + `/${id}`);
  }
}
