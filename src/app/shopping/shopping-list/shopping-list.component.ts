import { Component, OnInit } from '@angular/core';
import {ShoppingService} from "../service/shopping.service";
import {FuzAlertService} from "../../fuz-components/fuz-alert/fuz-alert.service";
import {MatTableDataSource} from "@angular/material";
import {ShoppingListItem} from "../model/shopping-list-item";
import {forEach} from "@angular/router/src/utils/collection";
import {ItemCategory} from "../model/item-category";

@Component({
  selector: 'app-shopping-list',
  templateUrl: './shopping-list.component.html',
  styleUrls: ['./shopping-list.component.scss']
})
export class ShoppingListComponent implements OnInit {

  shoppingListItems : ShoppingListItem[];
  data2 : (ShoppingListItem | string)[] = [];
  displayedColumns: string[] = ['check','name','quantity','note','category','target','priority'];
  dataSource = new MatTableDataSource<ShoppingListItem | string>([]);

  constructor(private shoppingService : ShoppingService,
              private alertService : FuzAlertService) { }

  ngOnInit() {
    this.shoppingService.getShoppingList()
      .then(data => {
        this.shoppingListItems = data;
        this.groupByCategory();
      })
      .catch(err => this.alertService.showHttpErrorMessage(err));
  }

  private groupByCategory() {
    var categories : ItemCategory[] = [];
    // kategóriát legyűjtése
    this.shoppingListItems.forEach( item => {
      if(item.category) {
        if(categories.findIndex( i => i.id == item.category.id) < 0){
          categories.push(item.category);
        }
      }
    });

    console.log(categories);

    categories.forEach(c => {
      this.data2.push(c.name);
      this.shoppingListItems.forEach(item => {
        if(item.category && item.category.id == c.id){
          this.data2.push(item);
        }
      });
    });

    this.dataSource = new MatTableDataSource<ShoppingListItem | string>(this.data2);
  }

  isGroup(index, item): boolean{
    return (typeof item) == "string";
  }
}
