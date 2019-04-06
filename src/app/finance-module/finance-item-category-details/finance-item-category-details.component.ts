import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";
import {FinancialItemCategory} from "../model/financial-item-category";
import {FinancialService} from "../services/financial.service";
import {FuzAlertService} from "../../fuz-components/fuz-alert/fuz-alert.service";

@Component({
  selector: 'app-finance-item-category-details',
  templateUrl: './finance-item-category-details.component.html',
  styleUrls: ['./finance-item-category-details.component.scss']
})
export class FinanceItemCategoryDetailsComponent implements OnInit {

  financeItemCategoryStatus : string;
  constructor(public dialogRef: MatDialogRef<FinanceItemCategoryDetailsComponent>,
              @Inject(MAT_DIALOG_DATA) public financeItemCategory: FinancialItemCategory,
              private financeService : FinancialService,
              private fuzAlertService : FuzAlertService) {
    this.financeItemCategoryStatus = this.financeItemCategory.alive ? 'Aktív' : 'Passzív';
  }

  ngOnInit() {
  }

  onSubmitFinItemCategory() {
    this.financeService.updateFinancialItemCategory(this.financeItemCategory)
      .then(data => {
        this.financeItemCategory = data;
        this.dialogRef.close();
      })
      .catch(err => this.fuzAlertService.showHttpErrorMessage(err) );
  }
}
