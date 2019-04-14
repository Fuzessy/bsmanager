import { Injectable } from '@angular/core';
import {Path} from "../../fuz-common/service/path";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs/internal/Observable";
import {FinancialItemCategory} from "../../finance-module/model/financial-item-category";
import {ApplicationUser} from "../model/application-user";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpClient : HttpClient, private path:Path) { }

  public getApplicationUser() : Promise<ApplicationUser>{
    return this.httpClient.get<ApplicationUser>(
      this.path.URL("/user/")).toPromise();
  }
}
