import { Injectable } from '@angular/core';
import {Path} from "../../fuz-common/service/path";
import {EventSourcePolyfill} from 'ng-event-source';
import {AuthenticationService} from "../../core/service/authentication.service";

@Injectable({
  providedIn: 'root'
})
export class ShoppingSseService {

  constructor(private path: Path,
              private authenticationService: AuthenticationService) {
  }

  /**
   * fuzessy@fuzessy-Lenovo-G500:~/dev/project/fuz/bs$ /bin/sh ./npm install ng-event-source


   * @returns {EventSource}
   */
  getShoppingListEventSource(): EventSourcePolyfill{

    let es =  new EventSourcePolyfill(
      this.path.URL('/shopping/event/stream')
      , {
        headers: {'Authorization': this.authenticationService.TOKEN_PREFIX + this.authenticationService.getToken()}
      });

    return es;
  }
}
