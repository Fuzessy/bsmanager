import {AfterViewInit, Component, ElementRef, EventEmitter, Input, Output, ViewChild} from '@angular/core';
import {FinancialItemCategory} from "../model/financial-item-category";
import {FinancialService} from "../services/financial.service";
import {FinancialItem} from "../model/financial-item";
import {FinAccount} from "../model/fin-account";

@Component({
  selector: 'app-add-item',
  templateUrl: './add-item.component.html',
  styleUrls: ['./add-item.component.css']
})
export class AddItemComponent implements AfterViewInit {

  @Input('mode')
  mode : string = 'insert';
  private viewInited: boolean = false;
  private accountIsLloaded: boolean = false;
  private categoriesLoaded: boolean = false;

  @Input('financeItem')
  set financeItem( item : FinancialItem){
    if(item){
      this.itemForUD = item;
      this.selectedCreditOrDebit = item.amount > 0 ? this.creditOrDebit[1] : this.creditOrDebit[0];
      this.amount = Math.abs(item.amount);

      this.checkIfDataLoaded().then( () =>{
        this.selectedAccount = this.accounts.find(a => a.id === item.targetAccount.id);
        this.selectedFinItemCategorie = this.finItemCategories.find(c => c.id == item.category.id);
      });


      var date : Date = new Date()
      date.setFullYear(
      item.transactionDate[0], item.transactionDate[1]-1, item.transactionDate[2]);

      this.transactionDateModel = date.toISOString().substr(0,10);
    }
  }

  get financeItem() : FinancialItem{
    return this.createFinanceItemFromFields();
  }

  /**
   * call when CRUD operation finished
   * @type {EventEmitter<any>}
   */
  @Output() public onOperationFinished: EventEmitter<any> = new EventEmitter();

  @ViewChild('transactionAmountField')
  transactionAmountField : ElementRef;

  itemForUD : FinancialItem; // id for update or delete
  finItemCategories : FinancialItemCategory[];
  selectedFinItemCategorie : FinancialItemCategory;
  accounts : FinAccount[];
  selectedAccount : FinAccount;
  creditOrDebit = [
    {multiplier:-1, name:"terhelés"},
    {multiplier:1, name:"jóváírás"}];
  selectedCreditOrDebit : {multiplier, name} = this.creditOrDebit[0];
  amount : number;
  transactionDateModel : string;
  note: string;

  operationFinished: boolean = false;
  isTransfer: boolean = false;

  constructor(private financialService :FinancialService) {
    this.financialService.getFinancialItemCategories().subscribe(
      value => {
        this.finItemCategories = value
        this.categoriesLoaded = true;
      },
      err => console.error('Hiba történt: ' + err)
    );

    this.financialService.getUserAccounts().subscribe(
      value => {
        this.accounts = value;
        this.accountIsLloaded = true;
      },
      err => console.error('Hiba történt: ' + err)
    );
    this.transactionDateModel = new Date().toISOString().substr(0,10);

  }

  checkIfDataLoaded() : Promise<any> {
    return new Promise((resolve, reject) => {
      const check = () => {
        if (this.accountIsLloaded === true && this.categoriesLoaded === true) {
          resolve(true);
        } else {
          setTimeout(() => check(),100);
        }
      }
      check();
    });
  }

  ngAfterViewInit(): void {
    setTimeout(() =>
      this.transactionAmountField.nativeElement.focus(),1000);
  }

  onSubmitFinancialItem(){
    if(this.mode === 'insert'){
      this.onCreateFinancialItem();
    }
    if(this.mode === 'update'){
      this.onUpdateFinancialItem();
    }
  }

  onCreateFinancialItem(){
    var financialItem : FinancialItem = this.createFinanceItemFromFields();
    this.financialService.createFinancialItem(financialItem)
      .then(_ => {
        this.operationFinished = true;
        this.onOperationFinished.emit();
      })
      .catch( err =>{ console.log('Hiba történt: ' + err);})
  }

  onUpdateFinancialItem(){
    this.itemForUD.amount = this.selectedCreditOrDebit.multiplier * this.amount;
    this.itemForUD.category = this.selectedFinItemCategorie;
    this.itemForUD.targetAccount = this.selectedAccount;
    this.itemForUD.transactionDate = new Date(this.transactionDateModel);

    this.financialService.updateFinancialItem(this.itemForUD)
      .then(_ => {
        this.operationFinished = true;
        this.onOperationFinished.emit();
      })
      .catch( err =>{ console.log('Hiba történt: ' + err);})
  }

  transferToggle(event){
    if(event.target.checked === true){
      this.isTransfer = true;
    }else{
      this.isTransfer = false;
    }

  }

  private createFinanceItemFromFields() {
    return {
      id : null,
      recordUser : null,
      recordTimestamp : null,

      amount : this.selectedCreditOrDebit.multiplier * this.amount,
      category : this.selectedFinItemCategorie,
      targetAccount : this.selectedAccount,
      transactionDate : new Date(this.transactionDateModel),
      note : this.note,
      orderNumber : null,
      balance : null
    };
  }

  initData(){
    this.amount = null;
    this.selectedAccount = null;
    this.selectedFinItemCategorie = null;
    this.selectedCreditOrDebit = null;
    this.operationFinished = false;
  }

}
