import {Injectable} from "@angular/core";
import {HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {AuthenticationService} from "../service/authentication.service";

@Injectable()
export class BearerInterceptor implements HttpInterceptor {

  constructor(private authenticationService : AuthenticationService){

  }

  intercept(req: HttpRequest<any>, next: HttpHandler) {
    if (this.authenticationService.isAuthenticated()){
      const xhr = req.clone({
        headers: req.headers.set(
          this.authenticationService.TOKEN_HEADER_KEY,
          this.authenticationService.TOKEN_PREFIX + this.authenticationService.getToken())
      });
      return next.handle(xhr);
    }else{
      return next.handle(req);
    }
  }
}
