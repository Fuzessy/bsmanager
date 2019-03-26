import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FuzSelectComponent } from './fuz-select/fuz-select.component';
import {MatIconModule} from "@angular/material";
import {FuzCommonModule} from "../fuz-common/fuz-common.module";
import { FuzNumberComponent } from './fuz-number/fuz-number.component';

@NgModule({
  imports: [
    CommonModule,
    MatIconModule,
    FuzCommonModule
  ],
  declarations: [FuzSelectComponent, FuzNumberComponent],
  exports: [FuzSelectComponent, FuzNumberComponent]
})
export class FuzComponentsModule { }
