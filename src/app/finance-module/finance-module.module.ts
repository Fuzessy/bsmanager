import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AddItemComponent } from './add-item/add-item.component';
import {FuzComponentsModule} from "../fuz-components/fuz-components.module";
import {FormsModule} from "@angular/forms";
import { FinanceHistoryComponent } from './finance-history/finance-history.component';
import {MatIconModule} from "@angular/material";
import { FinanceItemCategoriesComponent } from './finance-item-categories/finance-item-categories.component';
import { FinanceItemCategoryDetailsComponent } from './finance-item-category-details/finance-item-category-details.component';

@NgModule({
  imports: [
    CommonModule
    ,FormsModule
    ,FuzComponentsModule
    ,MatIconModule
  ],
  declarations: [AddItemComponent, FinanceHistoryComponent, FinanceItemCategoriesComponent, FinanceItemCategoryDetailsComponent],
  entryComponents: [FinanceItemCategoryDetailsComponent]
})
export class FinanceModuleModule { }
