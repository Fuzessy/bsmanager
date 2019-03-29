import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";
import {FuzAlertData} from "./fuz-alert-data";
import {ButtonOptions} from "./button-optins";

@Component({
  selector: 'app-fuz-alert',
  templateUrl: './fuz-alert.component.html',
  styleUrls: ['./fuz-alert.component.scss']
})
export class FuzAlertComponent implements OnInit {

  fuzAlertData : FuzAlertData;

  constructor(public dialogRef: MatDialogRef<FuzAlertComponent>,
              @Inject(MAT_DIALOG_DATA) public data: FuzAlertData) {
    this.fuzAlertData = data;
    this.dialogRef.afterClosed().subscribe(data =>
      {
        if(data === undefined) this.escape();
      });
  }

  ngOnInit() {
  }

  isButtonVisible(buttonType : string){
    var o : ButtonOptions = this.fuzAlertData.buttonOption;
    return ( buttonType == 'YES' && (
              o === ButtonOptions.YES_NO_CANCEL_OPTION ||
              o === ButtonOptions.YES_NO_OPTION))

      ||  ( buttonType == 'NO' && (
            o === ButtonOptions.YES_NO_CANCEL_OPTION))

      ||  ( buttonType == 'CANCEL' && (
            o === ButtonOptions.YES_NO_CANCEL_OPTION))

      ||  ( buttonType == 'OK' && (
            o === ButtonOptions.OK_OPTION))
  }

  yes() {
    this.dialogRef.close('yes');
    this.fuzAlertData.fuzAlertCloseEvents && this.fuzAlertData.fuzAlertCloseEvents.yesEvent && this.fuzAlertData.fuzAlertCloseEvents.yesEvent();
  }

  no() {
    this.dialogRef.close('no');
    this.fuzAlertData.fuzAlertCloseEvents && this.fuzAlertData.fuzAlertCloseEvents.noEvent && this.fuzAlertData.fuzAlertCloseEvents.noEvent();
  }

  cancel(){
    this.dialogRef.close('cancel');
    this.fuzAlertData.fuzAlertCloseEvents && this.fuzAlertData.fuzAlertCloseEvents.cancelEvent && this.fuzAlertData.fuzAlertCloseEvents.cancelEvent();
  }

  ok(){
    this.dialogRef.close('ok');
    this.fuzAlertData.fuzAlertCloseEvents && this.fuzAlertData.fuzAlertCloseEvents.okEvent && this.fuzAlertData.fuzAlertCloseEvents.okEvent();
  }

  private escape() {
    this.dialogRef.close('escape');
  }
}
