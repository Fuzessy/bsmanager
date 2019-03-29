import { Injectable } from '@angular/core';
import {MatDialog} from "@angular/material";
import {FuzAlertComponent} from "./fuz-alert.component";
import {Observable} from "rxjs/internal/Observable";
import {FuzAlertData} from "./fuz-alert-data";
import {ButtonOptions} from "./button-optins";
import {FuzAlertCloseEvents} from "./fuz-alert-close-events";

@Injectable({
  providedIn: 'root'
})
export class FuzAlertService {

  constructor(private dialog : MatDialog) { }

  public showWarning( textMessages : {message :string, title?:string, details?:string},
                    buttonOption? : ButtonOptions,
                    fuzAlertCloseEvents? : FuzAlertCloseEvents) : Observable<any>{
    var fuzAlertData : FuzAlertData = {
      title: textMessages && textMessages.title ? textMessages.title : "Figyelem!",
      message: textMessages && textMessages.message ? textMessages.message : "",
      details: textMessages && textMessages.details ? textMessages.details: null,
      buttonOption : buttonOption ? buttonOption : ButtonOptions.OK_OPTION,
      fuzAlertCloseEvents : fuzAlertCloseEvents
    };

    return this.openDialog(fuzAlertData,'warning_dialog');
  }

  public showError( textMessages : {message :string, title?:string, details?:string},
                      buttonOption? : ButtonOptions,
                      fuzAlertCloseEvents? : FuzAlertCloseEvents) : Observable<any>{
    var fuzAlertData : FuzAlertData = {
      title: textMessages && textMessages.title ? textMessages.title : "Figyelem!",
      message: textMessages && textMessages.message ? textMessages.message : "Hiba történt a művelet végrehajtása során!",
      details: textMessages && textMessages.details ? textMessages.details: null,
      buttonOption : buttonOption ? buttonOption : ButtonOptions.OK_OPTION,
      fuzAlertCloseEvents : fuzAlertCloseEvents
    };

    return this.openDialog(fuzAlertData,'error_dialog');
  }

  public showHttpErrorMessage( error: any) : Observable<any>{
    var fuzAlertData : FuzAlertData = {
      title: "Figyelem!",
      message: error.error && error.error.message ? error.error.message : "Hiba történt a művelet végrehajtása során!",
      details: null,
      buttonOption : ButtonOptions.OK_OPTION,
      fuzAlertCloseEvents : { }
    };

    return this.openDialog(fuzAlertData,'error_dialog');
  }

  public showSuccess( textMessages : {message :string, title?:string, details?:string},
                      buttonOption? : ButtonOptions,
                      fuzAlertCloseEvents? : FuzAlertCloseEvents) : Observable<any>{

    var fuzAlertData : FuzAlertData = {
      title: "Figyelem!",
      message: "A művelet végrehajtása sikeresen megtörtént!!",
      details: textMessages && textMessages.details ? textMessages.details: null,
      buttonOption : buttonOption ? buttonOption : ButtonOptions.OK_OPTION,
      fuzAlertCloseEvents : fuzAlertCloseEvents
    };

    return this.openDialog(fuzAlertData, 'success_dialog');
  }

  private openDialog(fuzAlertData: FuzAlertData, css: string) : Observable<any>{
    const dialogRef = this.dialog.open(FuzAlertComponent, {
      role: 'dialog',
      panelClass: css,
      data: fuzAlertData
    });

    return dialogRef.afterClosed();;
  }


}
