import { Component, OnInit } from '@angular/core';
import {ShoppingService} from "../service/shopping.service";
import {FuzAlertService} from "../../fuz-components/fuz-alert/fuz-alert.service";
import {MatTableDataSource} from "@angular/material";
import {ShoppingListItem} from "../model/shopping-list-item";
import {ItemCategory} from "../model/item-category";
import {MatSnackBar} from "@angular/material";

@Component({
  selector: 'app-shopping-list',
  templateUrl: './shopping-list.component.html',
  styleUrls: ['./shopping-list.component.scss']
})
export class ShoppingListComponent implements OnInit {

  shoppingListItems : ShoppingListItem[];
  inProgressCount : number;
  purchasedCount : number;

  displayedColumns: string[] = ['check','name','quantity','note','category','target','priority'];

  dataSourceInProgress = new MatTableDataSource<ShoppingListItem | string>([]);
  dataSourcePurchased = new MatTableDataSource<ShoppingListItem | string>([]);

  constructor(private shoppingService : ShoppingService,
              private alertService : FuzAlertService,
              private snackBar: MatSnackBar) { }

  ngOnInit() {
    this.getAndFill();
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
    var itemsInProgress : ShoppingListItem[] = this.shoppingListItems.filter(item => item.itemStatus == 'CREATED');
    this.inProgressCount = itemsInProgress.length;
    var categories: ItemCategory[] = this.getCategories(itemsInProgress);
    this.dataSourceInProgress = this.createDatasourceFrom(itemsInProgress,categories);

    var itemsPurchased : ShoppingListItem[] = this.shoppingListItems.filter(item => item.itemStatus == 'PURCHASED');
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
}
