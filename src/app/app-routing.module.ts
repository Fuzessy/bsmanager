import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FinanceModuleModule } from "./finance-module/finance-module.module";
import { AddItemComponent } from "./finance-module/add-item/add-item.component";
import {FinanceHistoryComponent} from "./finance-module/finance-history/finance-history.component";
import {FinanceItemCategoriesComponent} from "./finance-module/finance-item-categories/finance-item-categories.component";
import {ShoppingListItemComponent} from "./shopping/shopping-list-item/shopping-list-item.component";
import {ShoppingModule} from "./shopping/shopping.module";
import {ShoppingListComponent} from "./shopping/shopping-list/shopping-list.component";

const routes: Routes = [
  {path:'addNewFinanceItem', component: AddItemComponent},
  {path:'financeHistory', component: FinanceHistoryComponent},
  {path:'financeItemCategories', component: FinanceItemCategoriesComponent},

  {path:'shoppingListItem', component: ShoppingListItemComponent},
  {path:'shoppingList', component: ShoppingListComponent},
];

@NgModule({
  exports: [
    RouterModule
  ],
  imports: [
    FinanceModuleModule,
    ShoppingModule,
    RouterModule.forRoot(routes) ],
})

export class AppRoutingModule { }
