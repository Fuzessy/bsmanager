import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Path} from "../../fuz-common/service/path";
import {Credentials} from "../model/credentials";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(private httpClient : HttpClient, private path:Path) { }

  private authenticated : boolean = false;
  private token : string = undefined;

  login(credentials : Credentials) {
    return this.httpClient.post<string>("/login",
      {
        username : credentials.username,
        password: credentials.password
      }
    ).subscribe(data => {
      this.token = data;
      this.authenticated = true;
    });
  }

  isAuthenticated() {
    return this.authenticated;
  }

  getToken() {
    return this.token;
  }
}
