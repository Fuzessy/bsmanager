import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {FinancialService} from "../services/financial.service";
import {FinancialItem} from "../model/financial-item";
import {FinAccount} from "../model/fin-account";

declare var $: any;

@Component({
  selector: 'finance-history',
  templateUrl: './finance-history.component.html',
  styleUrls: ['./finance-history.component.css']
})
export class FinanceHistoryComponent implements OnInit {

  selectedAccount? : FinAccount = null;
  accounts : FinAccount[] = [];

  transactionDateFrom : string = null;
  transactionDateTo : string = null;

  model: FinancialItem[] = [];

  selectedFinanceItem : FinancialItem;
  showFinanceItem: boolean = false;
  errorMessage: any;

  constructor(private financialService : FinancialService) { }

  ngOnInit() {
    this.financialService.getUserAccounts().subscribe(
      result => {
        this.accounts = result;
        this.initAndSearch();
      }
    );

    $('#editFinanceItemModal').on('hidden.bs.modal', () => {this.showFinanceItem = false;})
  }

  clearFilters(){
    this.transactionDateFrom = null;
    this.transactionDateTo = null;
    this.selectedAccount = null;
  }

  onSearchByFilter() {
    this.financialService.getFinancialItems({
      transactionDateFrom: this.transactionDateFrom && this.transactionDateFrom,
      transactionDateTo: this.transactionDateTo && this.transactionDateTo,
      accountId: this.selectedAccount && this.selectedAccount.id // undefined és null kezelés
    }).subscribe(result => this.model = result)
  }

  moveAhead(financeItem : FinancialItem){
    this.financialService.moveAheadTransaction(financeItem)
      .catch( err => this.showError(err))
      .then(items => this.setItemsByOrderNumber(items));
  }

  showError(err: any) {
    this.errorMessage = err.error.message;
    $('#alertModal').modal('show');
  }

  moveBack(financeItem : FinancialItem){
    this.financialService.moveBackTransaction(financeItem)
      .catch( err => this.showError(err))
      .then(items => this.setItemsByOrderNumber(items));
  }

  private setItemsByOrderNumber(items: FinancialItem[] | void) {
    if(items instanceof Array){
      items.forEach( item => {
        for (var i = 0; i < this.model.length; i++) {
          if (this.model[i].orderNumber === item.orderNumber) {
            this.model[i] = item
          }
        }
      });
    }
  }

  edit(financeItem : FinancialItem){
    this.showFinanceItem = true;
    this.selectedFinanceItem = financeItem;
    $('#editFinanceItemModal').modal('show');
  }

  onUpdateFinished() {
    this.onSearchByFilter();
    $('#editFinanceItemModal').modal('hide');
  }

  private initAndSearch() {
    if(this.accounts && this.accounts.length > 0){
      this.selectedAccount = this.accounts[0];
      this.onSearchByFilter();
    }
  }
}
