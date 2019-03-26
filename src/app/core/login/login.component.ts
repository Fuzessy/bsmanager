import { Component, OnInit } from '@angular/core';
import {Credentials} from "../model/credentials";
import {AuthenticationService} from "../service/authentication.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  credentials : Credentials = {username : null, password:null};

  constructor(private authenticationService : AuthenticationService) { }

  ngOnInit() {
  }

  login(){
    this.authenticationService.login(this.credentials);
  }



}
