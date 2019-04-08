import { Injectable } from '@angular/core';
import {Path} from "../../fuz-common/service/path";
import {HttpClient} from "@angular/common/http";
import {ItemCategory} from "../model/item-category";
import {ItemTarget} from "../model/item-target";
import {ShoppingListItem} from "../model/shopping-list-item";

@Injectable({
  providedIn: 'root'
})
export class ShoppingService {

  constructor(private httpClient : HttpClient, private path:Path) { }

  public getItemCategories(onlyAlive : boolean) : Promise<ItemCategory[]>{
    return this.httpClient.get<ItemCategory[]>(
      this.path.URL("/shopping/item-category")
      , { params : this.path.queryParams({'alive':onlyAlive})}
    ).toPromise();
  }

  public getPriorities(){
    return this.httpClient.get<ItemCategory[]>(
      this.path.URL("/shopping/priority")).toPromise();
  }

  public getItemTargets(){
    return this.httpClient.get<ItemTarget[]>(
      this.path.URL("/shopping/item-target")).toPromise();
  }

  public createShoppingListItem(shoppingListItem : ShoppingListItem) : Promise<ShoppingListItem>{
    return this.httpClient.post<ShoppingListItem>(
      this.path.URL("/shopping/shopping-list-item"),
      shoppingListItem
    ).toPromise();
  }

  public getShoppingList() : Promise<ShoppingListItem[]>{
    return this.httpClient.get<ShoppingListItem[]>(
      this.path.URL("/shopping/shopping-list-item")
    ).toPromise();
  }

  updateShoppingListItem(shoppingListItem: ShoppingListItem) : Promise<ShoppingListItem>{
    return this.httpClient.put<ShoppingListItem>(
      this.path.URL("/shopping/shopping-list-item"),
      shoppingListItem
    ).toPromise();
  }
}
