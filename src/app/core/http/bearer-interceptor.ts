import {Injectable} from "@angular/core";
import {
  HttpErrorResponse,
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
  HttpResponse
} from "@angular/common/http";
import {AuthenticationService} from "../service/authentication.service";
import {map, catchError} from "rxjs/operators";
import {throwError} from "rxjs/internal/observable/throwError";

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
      return next.handle(xhr).pipe(
        catchError((error: HttpErrorResponse) => {
          if(error.status == 403){
            this.authenticationService.logout();
          }
          return throwError(error)
        })
      );
    }else{
      return next.handle(req);
    }
  }


}
