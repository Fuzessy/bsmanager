import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FinanceModuleModule } from "./finance-module/finance-module.module";
import { AddItemComponent } from "./finance-module/add-item/add-item.component";
import {FinanceHistoryComponent} from "./finance-module/finance-history/finance-history.component";
import {FinanceItemCategoriesComponent} from "./finance-module/finance-item-categories/finance-item-categories.component";

const routes: Routes = [
  {path:'addNewFinanceItem', component: AddItemComponent},
  {path:'financeHistory', component: FinanceHistoryComponent},
  {path:'financeItemCategories', component: FinanceItemCategoriesComponent},
];

@NgModule({
  exports: [
    RouterModule
  ],
  imports: [
    FinanceModuleModule,
    RouterModule.forRoot(routes) ],
})

export class AppRoutingModule { }
