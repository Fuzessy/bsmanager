import {FinancialItemCategory} from './financial-item-category';
import {FinAccount} from "./fin-account";

export interface FinancialItem {
  id : number,
  category : FinancialItemCategory,
  targetAccount : FinAccount,
  amount : number,
  recordTimestamp : Date,
  recordUser : number,
  transactionDate : any,
  orderNumber : number,
  balance : number,
  note : string
}
