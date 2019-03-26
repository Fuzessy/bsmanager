import {Injectable} from "@angular/core";
import {HttpParams} from "@angular/common/http";

@Injectable()
export class Path {

  constructor() { }

  private SERVER = "http:\\\\localhost:8080";

  public URL(urlPart: string) {
    return this.SERVER + urlPart;
  }

  /**
   * HTTP GET esetén query paraméterek előállítása objektumokból
   * annak paraméterei alapján
   * @param filter
   * @returns {HttpParams}
   */
  queryParams(filter: any) : HttpParams {
    if(filter === null || filter === undefined){
      return new HttpParams();
    }else{
      var httpParams = new HttpParams();
      for(var key in filter){
        if(filter[key]) {
          httpParams = httpParams.append(key, filter[key]);
        }
      }
      return httpParams;
    }
  }
}
