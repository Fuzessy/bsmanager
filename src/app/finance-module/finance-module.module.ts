import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AddItemComponent } from './add-item/add-item.component';
import {FuzComponentsModule} from "../fuz-components/fuz-components.module";
import {FormsModule} from "@angular/forms";
import { FinanceHistoryComponent } from './finance-history/finance-history.component';
import {MatIconModule} from "@angular/material";

@NgModule({
  imports: [
    CommonModule
    ,FormsModule
    ,FuzComponentsModule
    ,MatIconModule
  ],
  declarations: [AddItemComponent, FinanceHistoryComponent]
})
export class FinanceModuleModule { }
