import {ApplicationUser} from "../../core/model/application-user";
import {ItemCategory} from "./item-category";
import {ItemTarget} from "./item-target";

export interface ShoppingListItem{
  id: number;
  name: string;
  quantity: string;
  note: string;
  category: ItemCategory;
  priority: string;
  itemStatus: string;
  target?: ItemTarget;

  recordUser: ApplicationUser;
  recordTime: Date;

  purchaseUser: ApplicationUser;
  purchaseTime: Date;

  deleteUser: ApplicationUser;
  deleteTime: Date;
}
