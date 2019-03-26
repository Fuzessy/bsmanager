import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {Safe} from './pipe/safe';
import {Path} from "./service/path";

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [Safe],
  providers:[
    Path
  ],
  exports: [
    Safe
  ]
})
export class FuzCommonModule { }
