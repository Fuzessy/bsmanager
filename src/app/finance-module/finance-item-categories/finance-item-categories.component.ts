import { Component, OnInit } from '@angular/core';
import {FinancialService} from "../services/financial.service";
import {FinancialItemCategory} from "../model/financial-item-category";

@Component({
  selector: 'app-finance-item-categories',
  templateUrl: './finance-item-categories.component.html',
  styleUrls: ['./finance-item-categories.component.scss']
})
export class FinanceItemCategoriesComponent implements OnInit {
  private financeItemCategories: FinancialItemCategory[];

  constructor(private financialService : FinancialService) { }

  ngOnInit() {
    this.financialService.getFinancialItemCategories(false)
      .subscribe( data => this.financeItemCategories = data)
  }

  activate(item : FinancialItemCategory){
    this.financialService.activateFinancialItemCategory(item)
      .then(data => this.changeElementInArray(data))
      .catch(error => alert("Hiba történt"));
  }

  passivate(item : FinancialItemCategory){
    this.financialService.passivateFinancialItemCategory(item)
      .then(data => this.changeElementInArray(data))
      .catch(error => alert("Hiba történt"));;
  }

  rename(item : FinancialItemCategory){

  }

  private changeElementInArray(item: FinancialItemCategory) {
    this.financeItemCategories[this.financeItemCategories.findIndex(c => c.id === item.id)] = item;
  }

}
