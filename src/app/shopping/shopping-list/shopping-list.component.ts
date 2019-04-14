import {Component, OnDestroy, OnInit} from '@angular/core';
import {ShoppingService} from "../service/shopping.service";
import {FuzAlertService} from "../../fuz-components/fuz-alert/fuz-alert.service";
import {MatTableDataSource} from "@angular/material";
import {ShoppingListItem} from "../model/shopping-list-item";
import {ItemCategory} from "../model/item-category";
import {MatSnackBar} from "@angular/material";
import {ShoppingSseService} from "../service/shopping-sse.service";
import {EventSourcePolyfill} from "ng-event-source";
import {ShoppingEvent} from "../model/shopping-event";

@Component({
  selector: 'app-shopping-list',
  templateUrl: './shopping-list.component.html',
  styleUrls: ['./shopping-list.component.scss']
})
export class ShoppingListComponent implements OnInit, OnDestroy {

  shoppingListItems : ShoppingListItem[];
  inProgressCount : number;
  purchasedCount : number;

  displayedColumns: string[] = ['name','quantity','note','category','target','priority', 'operations'];
  displayedColumnsPurchased: string[] = ['name','quantity','note','category','target','priority'];

  dataSourceInProgress = new MatTableDataSource<ShoppingListItem | string>([]);
  dataSourcePurchased = new MatTableDataSource<ShoppingListItem | string>([]);

  sseService : EventSourcePolyfill;
  constructor(private shoppingService : ShoppingService,
              private shoppingSseService: ShoppingSseService,
              private alertService : FuzAlertService,
              private snackBar: MatSnackBar) { }

  ngOnInit() {
    this.getAndFill();
    this.sseService = this.shoppingSseService.getShoppingListEventSource();
    this.sseService.onmessage = (data => {
      console.log(data);
      let eventData : ShoppingEvent = <ShoppingEvent> JSON.parse(data.data);
      this.handleSseEvent(eventData);
    });
  }

  ngOnDestroy(){
    this.sseService.close();
  }

  private getAndFill(){
    this.shoppingService.getShoppingList()
      .then(data => {
        this.shoppingListItems = data;
        this.fillTablesData();
      })
      .catch(err => this.alertService.showHttpErrorMessage(err));
  }

  private fillTablesData() {
    let itemsInProgress : ShoppingListItem[] = this.shoppingListItems.filter(item => item.itemStatus == 'CREATED');
    this.inProgressCount = itemsInProgress.length;
    var categories: ItemCategory[] = this.getCategories(itemsInProgress);
    this.dataSourceInProgress = this.createDatasourceFrom(itemsInProgress,categories);

    let itemsPurchased : ShoppingListItem[] = this.shoppingListItems.filter(item => item.itemStatus == 'PURCHASED');
    this.purchasedCount = itemsPurchased.length;
    var categories: ItemCategory[] = this.getCategories(itemsPurchased);
    this.dataSourcePurchased = this.createDatasourceFrom(itemsPurchased,categories);
  }

  private getCategories(items : ShoppingListItem[]) : ItemCategory[]{
    var categories: ItemCategory[] = []
    items.forEach(item => {
      if (item.category) {
        if (categories.findIndex(i => i.id == item.category.id) < 0) {
          categories.push(item.category);
        }
      }else{
        if (categories.findIndex(i => i.id === null) < 0) {
          categories.push({id: null, name: 'Nincs besorolva', alive: true});
        }
      }
    });
    return categories;
  }

  private createDatasourceFrom(items: ShoppingListItem[], categories: ItemCategory[]) : MatTableDataSource<ShoppingListItem | string> {
    var tableData : (ShoppingListItem | string)[] = [];
    categories.forEach(c => {
      tableData.push(c.name);
      items.forEach(item => {
        if (item.category && item.category.id == c.id) {
          tableData.push(item);
        }else if(c.id === null && item.category === null){
          tableData.push(item);
        }
      });
    });
    return new MatTableDataSource<ShoppingListItem | string>(tableData);
  }

  isGroup(index, item): boolean{
    return (typeof item) == "string";
  }

  buyFinished(item : ShoppingListItem){
    item.itemStatus = 'PURCHASED';
    this.shoppingService.updateShoppingListItem(item)
      .then(() => this.getAndFill())
      .catch(err => this.alertService.showHttpErrorMessage(err));
    this.snackBar.open(item.name + ' megvéve!','soppping volt!',
      {duration: 2000});
  }

  buyCancelled(item : ShoppingListItem){
    item.itemStatus = 'CREATED';
    this.shoppingService.updateShoppingListItem(item)
      .then(() => this.getAndFill())
      .catch(err => this.alertService.showHttpErrorMessage(err));
    this.snackBar.open(item.name + ' kosárból visszatéve!',':(',
      {duration: 2000});
  }

  private handleSseEvent(eventData: ShoppingEvent) {
    this.getAndFill();

    let message : string = eventData.user.nickName;
    if(eventData.eventType === 'CREATE'){
      message = message + ' új terméket rögzített: ';
    }else if(eventData.eventType === 'PURCHASE'){
      message = message + ' megvette: ';
    }else if(eventData.eventType === 'PUT_BACK'){
      message = message + ' visszarakta: ';
    }else if(eventData.eventType === 'DELETE'){
      message = message + ' törölte: ';
    }else if(eventData.eventType === 'RESTORE'){
      message = message + ' visszaállította: ';
    }else if(eventData.eventType === 'DATA_CHANGED'){
      message = message + ' módosította: ';
    }
    message = message + eventData.item.name;
    this.snackBar.open(message,';)',
      {duration: 5000});
  }
}
