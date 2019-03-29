import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FuzSelectComponent } from './fuz-select/fuz-select.component';
import {FuzCommonModule} from "../fuz-common/fuz-common.module";
import { FuzNumberComponent } from './fuz-number/fuz-number.component';
import { FuzAlertComponent } from './fuz-alert/fuz-alert.component';
import {MaterialModule} from "../material.module";

@NgModule({
  imports: [
    CommonModule,
    FuzCommonModule,
    MaterialModule
  ],
  declarations: [FuzSelectComponent, FuzNumberComponent, FuzAlertComponent],
  entryComponents: [FuzAlertComponent],
  exports: [FuzSelectComponent, FuzNumberComponent, FuzAlertComponent]
})
export class FuzComponentsModule { }
