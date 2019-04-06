import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ShoppingListItemComponent } from './shopping-list-item/shopping-list-item.component';
import {FormsModule} from "@angular/forms";
import {MaterialModule} from "../material.module";
import { ShoppingListComponent } from './shopping-list/shopping-list.component';

@NgModule({
  declarations: [ShoppingListItemComponent, ShoppingListComponent],
  imports: [
    CommonModule,
    FormsModule,
    MaterialModule
  ]
})
export class ShoppingModule { }
