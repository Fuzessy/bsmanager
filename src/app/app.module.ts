import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {AppRoutingModule } from './app-routing.module';
import {CoreModule} from "./core/core.module";
import {BearerInterceptor} from "./core/http/bearer-interceptor";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {FuzComponentsModule} from "./fuz-components/fuz-components.module";
import {MaterialModule} from "./material.module";

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    MaterialModule,
    AppRoutingModule,
    CoreModule,
    FuzComponentsModule,
    MaterialModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: BearerInterceptor, multi: true }
   ],
  bootstrap: [AppComponent]
})
export class AppModule { }
