import {AfterViewInit, Component, ElementRef, OnInit, ViewChild, ViewChildren} from '@angular/core';
import {ShoppingService} from "../service/shopping.service";
import {FuzAlertService} from "../../fuz-components/fuz-alert/fuz-alert.service";
import {ItemCategory} from "../model/item-category";
import {ShoppingListItem} from "../model/shopping-list-item";
import {ApplicationUser} from "../../core/model/application-user";
import {ItemTarget} from "../model/item-target";
import {timer} from "rxjs/internal/observable/timer";

@Component({
  selector: 'app-shopping-list-item',
  templateUrl: './shopping-list-item.component.html',
  styleUrls: ['./shopping-list-item.component.scss']
})
export class ShoppingListItemComponent implements OnInit, AfterViewInit {
  categories: ItemCategory[];
  targets : ItemTarget[];
  private priority: any;
  shoppingListItem : ShoppingListItem;

  @ViewChildren('itemName') inputItemName;

  constructor(private shoppingService : ShoppingService,
              private alertService : FuzAlertService) { }

  ngAfterViewInit(){
    // angular esetén first is van..
    setTimeout(() =>  this.inputItemName.first.nativeElement.focus(), 500);
  }

  ngOnInit() {
    this.initShoppingListItem();
    this.shoppingService.getItemCategories(true)
      .then(data => this.categories = data)
      .catch(err => this.alertService.showHttpErrorMessage(err))
    this.shoppingService.getPriorities()
      .then(data => this.priority = data)
      .catch(err => this.alertService.showHttpErrorMessage(err));
    this.shoppingService.getItemTargets()
      .then(data => this.targets = data)
      .catch(err => this.alertService.showHttpErrorMessage(err))
      .then(() => this.shoppingListItem.target = this.targets[0]);
  }

  private initShoppingListItem() {
    this.shoppingListItem = {
      id : null,
      name : null,
      quantity: '1',
      note : null,
      category : null,
      priority : null,
      itemStatus : null,
      recordUser : null,
      recordTime : null,
      purchaseUser : null,
      purchaseTime : null,
      deleteUser : null,
      deleteTime : null,
      target : null,
    }
  }

  onSubmitShoppingListItem() {
    this.shoppingService.createShoppingListItem(this.shoppingListItem)
      .then( data => {
        this.initShoppingListItem();
        this.alertService.showSuccess({message:'Rögzítés sikeres!'});
      })
      .catch(err => this.alertService.showHttpErrorMessage(err));
  }
}
