import { Injectable } from '@angular/core';
import {FinancialItem} from "../model/financial-item";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs/internal/Observable";
import {FinancialItemCategory} from "../model/financial-item-category";
import {Path} from "../../fuz-common/service/path";
import {FinAccount} from "../model/fin-account";


@Injectable({
  providedIn: 'root'
})
export class FinancialService {

  constructor(private httpClient : HttpClient, private path:Path) { }

  public createFinancialItem(finItem : FinancialItem) : Promise<FinancialItem>{
    return this.httpClient.post<FinancialItem>(
      this.path.URL("/finance/finance-item")
        , finItem).toPromise();
  }

  updateFinancialItem(financialItem: FinancialItem) : Promise<FinancialItem> {
    return this.httpClient.put<FinancialItem>(this.path.URL("/finance/finance-item")
      , financialItem).toPromise();
  }

  public getFinancialItemCategories() : Observable<FinancialItemCategory[]>{
    return this.httpClient.get<FinancialItemCategory[]>(
      this.path.URL("/finance/finance-item-categories"));
  }

  public getUserAccounts() : Observable<FinAccount[]>{
    return this.httpClient.get<FinAccount[]>(
      this.path.URL("/finance/user-account"));
  }

  public getFinancialItems(filter: { transactionDateFrom: string; transactionDateTo: string; accountId: number }) : Observable<FinancialItem[]>{
    return this.httpClient.get<FinancialItem[]>(
      this.path.URL("/finance/finance-item"),
        {params: this.path.queryParams(filter)} );
  }

  /**
   *
   * @param {FinancialItem} financeItem
   * @returns {Promise<FinancialItem[]>} kételemű lista, a csere
   * utáni állapot, az aktuális és a hátraküldött
   */
  public moveBackTransaction(financeItem: FinancialItem) : Promise<FinancialItem[]> {
    return this.httpClient.put<FinancialItem[]>(
      this.path.URL("/finance/moveback"),
      financeItem.id).toPromise();
  }

  /**
   *
   * @param {FinancialItem} financeItem
   * @returns {Promise<FinancialItem[]>} kételemű lista, a csere
   * utáni állapot, az aktuális és az előre hozott
   */
  public moveAheadTransaction(financeItem: FinancialItem) : Promise<FinancialItem[]> {
    return this.httpClient.put<FinancialItem[]>(
      this.path.URL("/finance/moveahead"),
      financeItem.id).toPromise();
  }
}
