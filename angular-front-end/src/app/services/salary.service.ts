import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class SalaryService {
  private baseUrl = 'http://localhost:8080/salaries';

  constructor(private httpClient: HttpClient) { }

  public getAll() {
    return this.httpClient.get(this.baseUrl);
  }

  public getSalary(id) {
    return this.httpClient.get(this.baseUrl + `/${id}`);
  }

  public getSalaryByCategory(categoryId) {
    return this.httpClient.get(this.baseUrl + `/categories/${categoryId}`);
  }

  public add(salary) {
    return this.httpClient.post(this.baseUrl, salary);
  }

  public update(id, salary) {
    return this.httpClient.put(this.baseUrl + `/${id}`, salary);
  }

  public delete(id) {
    return this.httpClient.delete(this.baseUrl + `/${id}`);
  }
}
