import {Injectable, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Path} from "../../fuz-common/service/path";
import {Credentials} from "../model/credentials";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService{

  public readonly TOKEN_HEADER_KEY : string = 'Authorization';
  public readonly TOKEN_PREFIX : string = 'Bearer ';

  private readonly TOKEN_LOCAL_STORAGE_KEY : string = 'BEARER_TOKEN_LOCAL_STORAGE';
  private authenticated : boolean = false;
  private token : string = undefined;

  constructor(private httpClient : HttpClient, private path:Path) {
    this.fillLocalFieldsFromLocalStorage();
  }

  login(credentials : Credentials) {
    return this.httpClient.post<string>("/login",
      {
        username : credentials.username,
        password: credentials.password
      },
      {
        // a teljes választ átadja, nem csak a body-t, ha ez az option be van állítva
        observe: 'response'
      }
    ).subscribe(data => {
      // Authorization header-t kell kiolvasni
      let tokenFromResponseHeader : string = data.headers.get(this.TOKEN_HEADER_KEY);
      // ami akkor hel1yes, ha 'Bearer '-el kezdődik
      if(tokenFromResponseHeader && tokenFromResponseHeader.length > 0
          && tokenFromResponseHeader.startsWith(this.TOKEN_PREFIX)){
        // a tokent eltároljuk (most épp prefx nélkül, de ennek nincs jelentősége)
        // ezt az interceptorban használjuk fel
        localStorage.setItem(this.TOKEN_LOCAL_STORAGE_KEY, tokenFromResponseHeader.replace(this.TOKEN_PREFIX,""));
      }
      this.fillLocalFieldsFromLocalStorage();
    });
  }

  isAuthenticated() {
    return this.authenticated;
  }

  getToken() {
    return this.token;
  }

  private fillLocalFieldsFromLocalStorage() {
    let tokenFromLocalStorage : string = localStorage.getItem(this.TOKEN_LOCAL_STORAGE_KEY);
    if(tokenFromLocalStorage && tokenFromLocalStorage.length > 0){
      this.token = tokenFromLocalStorage;
      this.authenticated = true;
    }else{
      this.token = null;
      this.authenticated = false;
    }
  }

  logout() {
    this.token = null;
    localStorage.removeItem(this.TOKEN_LOCAL_STORAGE_KEY);
    this.authenticated = false;
  }
}
